package org.example.repository;

import org.example.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByOrderByBookingDateDesc();

    Optional<Booking> findByIdAndUser_Id(Long bookingId, Long userId);

    List<Booking> findByUser_Id(Long userId);

    List<Booking> findByFlight_Id(Long flightId);
}
