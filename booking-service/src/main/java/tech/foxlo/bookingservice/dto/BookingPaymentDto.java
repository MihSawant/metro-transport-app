package tech.foxlo.bookingservice.dto;

import java.util.UUID;

public record BookingPaymentDto(UUID bookingId, UUID paymentId) {
}
