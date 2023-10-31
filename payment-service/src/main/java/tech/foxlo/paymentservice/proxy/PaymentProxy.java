package tech.foxlo.paymentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.foxlo.bookingservice.dto.BookingPaymentDto;
import tech.foxlo.bookingservice.entity.Booking;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "paymentProxy", url = "http://localhost:8082")
public interface PaymentProxy {

    @GetMapping("/api/booking/check/{uuid}")
    ResponseEntity<String> checkMail(@PathVariable UUID uuid);

    @PostMapping("/api/booking/update/payment")
    ResponseEntity<?> updatePayment(@RequestBody BookingPaymentDto bookingPaymentDto);

    @PostMapping("/api/booking/find")
    Optional<Booking> findBookingById(@RequestBody  UUID bookingId);
}
