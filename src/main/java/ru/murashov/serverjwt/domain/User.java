package ru.murashov.serverjwt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "bookings")
public class User {

  @Id
  @GeneratedValue
  private Integer id;

  private String login;

  @JsonIgnore
  private String password;

  private String username;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  private Set<Role> roles;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  @JsonIgnoreProperties("user")
  private Set<Booking> bookings = new HashSet<>();
}
