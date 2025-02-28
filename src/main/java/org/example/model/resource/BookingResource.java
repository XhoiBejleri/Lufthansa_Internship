package org.example.model.resource;


import org.example.model.enums.BookingStatus;

import java.time.LocalDateTime;

public class BookingResource {

    private Long id;
    private LocalDateTime bookingDate;
    private BookingStatus bookingStatus;
    private Long userId;
    private Long flightId;

    public BookingResource() {
    }

    public BookingResource(Long id, LocalDateTime bookingDate, BookingStatus bookingStatus, Long userId, Long flightId) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
        this.userId = userId;
        this.flightId = flightId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "BookingResource{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
                ", bookingStatus=" + bookingStatus +
                ", userId=" + userId +
                ", flightId=" + flightId +
                '}';
    }
}