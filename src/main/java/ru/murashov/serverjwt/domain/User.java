package ru.murashov.serverjwt.domain;

import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

  @Id
  private String login;
  private String password;
  private String username;
  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  private Set<Role> roles;
}
