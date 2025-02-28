package org.example.service;

import org.example.mapper.BookingMapper;
import org.example.model.entity.Booking;
import org.example.repository.BookingRepository;
import org.example.model.resource.BookingResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }


    public List<BookingResource> getAllBookingsOrdered() {
        List<Booking> bookings = bookingRepository.findAllByOrderByBookingDateDesc();
        return bookings.stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }


    public BookingResource getBookingForUser(Long bookingId, Long userId) {
        Optional<Booking> bookingOpt = bookingRepository.findByIdAndUser_Id(bookingId, userId);
        return bookingOpt.map(bookingMapper::toDto).orElse(null);
    }


    public List<BookingResource> getBookingsByUser(Long userId) {
        List<Booking> bookings = bookingRepository.findByUser_Id(userId);
        return bookings.stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<BookingResource> getBookingsByFlight(Long flightId) {
        List<Booking> bookings = bookingRepository.findByFlight_Id(flightId);
        return bookings.stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }


    public BookingResource saveBooking(BookingResource bookingResource) {
        Booking booking = bookingMapper.toEntity(bookingResource);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDto(booking);
    }


    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
