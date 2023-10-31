package tech.foxlo.bookingservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.foxlo.bookingservice.dto.BookingDto;
import tech.foxlo.bookingservice.dto.BookingPaymentDto;
import tech.foxlo.bookingservice.entity.Booking;
import tech.foxlo.bookingservice.service.BookingService;
import tech.foxlo.bookingservice.service.StationService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BookingController {

    private final StationService stationService;

    private final BookingService bookingService;

    public BookingController(StationService stationService, BookingService bookingService) {
        this.stationService = stationService;
        this.bookingService = bookingService;
    }

    @PostMapping("/api/booking/new-ticket")
    public ResponseEntity<?> bookTicket(@RequestBody BookingDto bookingDto){
        int finalBill = stationService.getCostBetween(bookingDto);
        Booking booking = new Booking(bookingDto.from(), bookingDto.to(), LocalDateTime.now(), finalBill,
                bookingDto.people(), bookingDto.journey());
        bookingService.addNewBooking(booking);
        return ResponseEntity.ok(booking);

    }


    @GetMapping("/api/booking/check/{uuid}")
    public ResponseEntity<?> checkBooking(@PathVariable("uuid") UUID uuid){
        if(bookingService.verifyBooking(uuid)){
            return ResponseEntity.ok("booking id verified success !");
        } else{
             return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/api/booking/update/payment")
    public ResponseEntity<?> updatePaymentDetails(@RequestBody BookingPaymentDto bookingPaymentDto){
        try{
            Booking booking = bookingService.updateBookingPayment(bookingPaymentDto.bookingId(),
                    bookingPaymentDto.paymentId());
            return ResponseEntity.of(Optional.of(booking));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().header("exceptionMessage", e.getMessage()).build();
        }


    }


    @PostMapping("/api/booking/find")
    public ResponseEntity<?> getBookingById(@RequestBody UUID bookingId){
        try{
            Booking booking = bookingService.findBookingById(bookingId);
            return ResponseEntity.ok(booking);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }
}
