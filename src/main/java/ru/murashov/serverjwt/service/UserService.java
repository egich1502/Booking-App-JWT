package ru.murashov.serverjwt.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.serverjwt.domain.User;
import ru.murashov.serverjwt.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getByLogin(String login) {
    return userRepository.findByLogin(login);
  }
}
