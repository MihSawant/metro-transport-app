package tech.foxlo.bookingservice.service;

import org.springframework.stereotype.Service;
import tech.foxlo.bookingservice.entity.Booking;
import tech.foxlo.bookingservice.repository.BookingRepository;

import java.awt.print.Book;
import java.util.Optional;
import java.util.UUID;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking addNewBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public boolean verifyBooking(UUID id){
        Optional<Booking> byId = bookingRepository.findById(id);
        return byId.isPresent();
    }

    public Booking updateBookingPayment(UUID bookingId, UUID paymentId){
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if(bookingOptional.isPresent()){
            var booking = bookingOptional.get();
            booking.setPaymentId(paymentId);
            return bookingRepository.save(booking);
        } else{
            throw new RuntimeException("Booking not Found !");
        }
    }



    public Booking findBookingById(UUID uuid){
        Optional<Booking> booking = bookingRepository.findById(uuid);
        return booking.orElseThrow();
    }
}
