package tech.foxlo.bookingservice.dto;

import tech.foxlo.bookingservice.entity.Booking;

import java.time.LocalDateTime;

public record BookingDto(String from, String to, String journey, int people) {

}
