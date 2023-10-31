package tech.foxlo.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.foxlo.ticketservice.entity.Ticket;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByBookingId(UUID bookingId);
}
