package tech.foxlo.paymentservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record Booking(UUID bookingId, long bill, LocalDateTime dateTime,
                      String from, String journey, UUID paymentId,String to) {
}
