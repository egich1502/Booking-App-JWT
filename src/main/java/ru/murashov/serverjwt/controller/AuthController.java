package ru.murashov.serverjwt.controller;

import javax.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.murashov.serverjwt.domain.JwtRequest;
import ru.murashov.serverjwt.domain.JwtResponse;
import ru.murashov.serverjwt.domain.RefreshJwtRequest;
import ru.murashov.serverjwt.domain.User;
import ru.murashov.serverjwt.service.AuthService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("login")
  public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest)
      throws AuthException {
    final JwtResponse token = authService.login(authRequest);
    return ResponseEntity.ok(token);
  }

  @PostMapping("register")
  public ResponseEntity<JwtResponse> register(@RequestBody User user) throws AuthException {
    final JwtResponse token = authService.registration(user);
    return ResponseEntity.ok(token);
  }

  @PostMapping("token")
  public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request)
      throws AuthException {
    final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
    return ResponseEntity.ok(token);
  }

  @PostMapping("refresh")
  public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request)
      throws AuthException {
    final JwtResponse token = authService.refresh(request.getRefreshToken());
    return ResponseEntity.ok(token);
  }
}
