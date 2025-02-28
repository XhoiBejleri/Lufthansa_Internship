package org.example.mapper;

import org.example.model.entity.Booking;
import org.example.model.resource.BookingResource;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking toEntity(BookingResource resource) {
        if (resource == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(resource.getId());
        booking.setBookingDate(resource.getBookingDate());
        booking.setBookingStatus(resource.getBookingStatus());
        return booking;
    }

    public BookingResource toDto(Booking booking) {
        if (booking == null) {
            return null;
        }
        return new BookingResource(
                booking.getId(),
                booking.getBookingDate(),
                booking.getBookingStatus(),
                booking.getUser() != null ? booking.getUser().getId() : null,
                booking.getFlight() != null ? booking.getFlight().getId() : null
        );
    }
}
