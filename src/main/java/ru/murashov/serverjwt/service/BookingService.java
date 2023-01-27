package ru.murashov.serverjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.serverjwt.domain.Booking;
import ru.murashov.serverjwt.domain.EatingTable;
import ru.murashov.serverjwt.domain.User;
import ru.murashov.serverjwt.repository.BookingRepository;

@Service
public class BookingService {

  private final BookingRepository bookingRepository;
  private final EatingTableService eatingTableService;
  private final UserService userService;

  @Autowired
  public BookingService(BookingRepository bookingRepository,
      EatingTableService eatingTableService, UserService userService) {
    this.bookingRepository = bookingRepository;
    this.eatingTableService = eatingTableService;
    this.userService = userService;
  }

  public Booking saveNewBooking(int tableId, Booking booking) {
    User user = userService.getByLogin(booking.getUser().getLogin()).orElseThrow();
    EatingTable eatingTable = eatingTableService.getTableById(tableId);
    Booking newBooking = Booking.builder()
        .title(booking.getTitle())
        .bookingDate(booking.getBookingDate())
        .user(user)
        .eatingTable(eatingTable)
        .build();
    try {
      return bookingRepository.save(newBooking);
    } catch (Exception e) {
      return null;
    }
  }
}
