package ru.murashov.serverjwt.service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.murashov.serverjwt.domain.Role;
import ru.murashov.serverjwt.domain.User;
import ru.murashov.serverjwt.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder encoder;

  public Optional<User> getByLogin(String login) {
    return userRepository.findByLogin(login);
  }

  public User addUser(User user) throws Exception{
    try {
      userRepository.findByLogin(user.getLogin()).orElseThrow();
      throw new Exception("user exist");
    } catch (NoSuchElementException e) {
      user.setRoles(Collections.singleton(Role.USER));
      user.setPassword(encoder.encode(user.getPassword()));
      return userRepository.save(user);
    }
  }
}
