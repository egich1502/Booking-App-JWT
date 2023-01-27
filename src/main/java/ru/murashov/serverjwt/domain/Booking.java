package ru.murashov.serverjwt.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "eatingTable"})
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Integer id;

  private String title;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
  @Column(unique = true)
  Date bookingDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnoreProperties("bookings")
  private User user;

  @ManyToOne
  @JoinColumn(name = "eatingTable")
  @JsonIgnoreProperties("bookings")
  private EatingTable eatingTable;

}
