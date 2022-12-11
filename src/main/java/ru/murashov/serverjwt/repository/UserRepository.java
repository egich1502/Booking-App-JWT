package ru.murashov.serverjwt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.murashov.serverjwt.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByLogin(String login);
}
