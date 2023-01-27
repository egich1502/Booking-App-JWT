package ru.murashov.serverjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.murashov.serverjwt.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
