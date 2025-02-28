package org.example.controller;

import org.example.model.resource.BookingResource;
import org.example.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/ordered")
    public ResponseEntity<List<BookingResource>> getAllBookingsOrdered() {
        List<BookingResource> bookings = bookingService.getAllBookingsOrdered();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{bookingId}/user/{userId}")
    public ResponseEntity<BookingResource> getBookingForUser(@PathVariable Long bookingId,
                                                             @PathVariable Long userId) {
        BookingResource booking = bookingService.getBookingForUser(bookingId, userId);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResource>> getBookingsByUser(@PathVariable Long userId) {
        List<BookingResource> bookings = bookingService.getBookingsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<BookingResource>> getBookingsByFlight(@PathVariable Long flightId) {
        List<BookingResource> bookings = bookingService.getBookingsByFlight(flightId);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping
    public ResponseEntity<BookingResource> createOrUpdateBooking(@RequestBody BookingResource bookingResource) {
        BookingResource savedBooking = bookingService.saveBooking(bookingResource);
        return ResponseEntity.ok(savedBooking);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}
