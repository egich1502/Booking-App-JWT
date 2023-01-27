package ru.murashov.serverjwt.service;

import io.jsonwebtoken.Claims;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.murashov.serverjwt.domain.JwtAuthentication;
import ru.murashov.serverjwt.domain.JwtRequest;
import ru.murashov.serverjwt.domain.JwtResponse;
import ru.murashov.serverjwt.domain.User;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserService userService;
  private final Map<String, String> refreshStorage = new HashMap<>();
  private final JwtProvider jwtProvider;
  private final PasswordEncoder encoder;

  public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
    final User user = userService.getByLogin(authRequest.getLogin())
        .orElseThrow(() -> new AuthException("Пользователь не найден"));
    if (encoder.matches(authRequest.getPassword(), user.getPassword())) {
      final String accessToken = jwtProvider.generateAccessToken(user);
      final String refreshToken = jwtProvider.generateRefreshToken(user);
      refreshStorage.put(user.getLogin(), refreshToken);
      return new JwtResponse(accessToken, refreshToken);
    } else {
      throw new AuthException("Неправильный пароль");
    }
  }

  public JwtResponse registration(@NonNull User user) throws AuthException {
    try {
      User addedUser = userService.addUser(user);
      final String accessToken = jwtProvider.generateAccessToken(addedUser);
      final String refreshToken = jwtProvider.generateRefreshToken(addedUser);
      refreshStorage.put(addedUser.getLogin(), refreshToken);
      return new JwtResponse(accessToken, refreshToken);
    } catch (Exception e) {
      throw new AuthException("пользователь существует");
    }
  }

  public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
    if (jwtProvider.validateRefreshToken(refreshToken)) {
      final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
      final String login = claims.getSubject();
      final String saveRefreshToken = refreshStorage.get(login);
      if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
        final User user = userService.getByLogin(login)
            .orElseThrow(() -> new AuthException("Пользователь не найден"));
        final String accessToken = jwtProvider.generateAccessToken(user);
        return new JwtResponse(accessToken, null);
      }
    }
    return new JwtResponse(null, null);
  }

  public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
    if (jwtProvider.validateRefreshToken(refreshToken)) {
      final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
      final String login = claims.getSubject();
      final String saveRefreshToken = refreshStorage.get(login);
      if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
        final User user = userService.getByLogin(login)
            .orElseThrow(() -> new AuthException("Пользователь не найден"));
        final String accessToken = jwtProvider.generateAccessToken(user);
        final String newRefreshToken = jwtProvider.generateRefreshToken(user);
        refreshStorage.put(user.getLogin(), newRefreshToken);
        return new JwtResponse(accessToken, newRefreshToken);
      }
    }
    throw new AuthException("Невалидный JWT токен");
  }

  public JwtAuthentication getAuthInfo() {
    return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
  }
}
