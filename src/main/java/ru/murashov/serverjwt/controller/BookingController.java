package ru.murashov.serverjwt.controller;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.murashov.serverjwt.domain.Booking;
import ru.murashov.serverjwt.service.BookingService;
import ru.murashov.serverjwt.service.EatingTableService;

@RestController
@RequestMapping("api/tables/{tableId}/booking")
@RequiredArgsConstructor
public class BookingController {

  private final EatingTableService eatingTableService;
  private final BookingService bookingService;

  @GetMapping(path = "", produces = "application/json")
  public Set<Booking> getAllBookingsForTable(@PathVariable("tableId") int id) {
    return eatingTableService.getTableById(id).getBookings();
  }

  @PreAuthorize("hasAuthority('USER')")
  @PostMapping(path = "new", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Booking> saveNewBooking(@PathVariable("tableId") int tableId,
      @RequestBody Booking booking) {
    Booking newBooking = bookingService.saveNewBooking(tableId, booking);
    if (newBooking != null) {
      return ResponseEntity.ok(newBooking);
    } else {
      return new ResponseEntity<>(booking, HttpStatus.BAD_REQUEST);
    }
  }
}
