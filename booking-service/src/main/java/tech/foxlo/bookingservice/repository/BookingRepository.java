package tech.foxlo.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.foxlo.bookingservice.entity.Booking;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
