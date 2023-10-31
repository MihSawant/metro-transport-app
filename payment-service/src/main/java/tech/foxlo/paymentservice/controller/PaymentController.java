package tech.foxlo.paymentservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tech.foxlo.bookingservice.dto.BookingPaymentDto;
import tech.foxlo.bookingservice.entity.Booking;
import tech.foxlo.paymentservice.dto.PaymentDto;
import tech.foxlo.paymentservice.entity.Payment;
import tech.foxlo.paymentservice.kafka.TicketProducerService;
import tech.foxlo.paymentservice.proxy.PaymentProxy;
import tech.foxlo.paymentservice.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PaymentController {



    @Autowired
    private PaymentProxy paymentProxy;

    @Autowired
    private TicketProducerService ticketProducerService;

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/api/payment")
    public ResponseEntity<?> newPayment(@RequestBody PaymentDto paymentDto){

        ResponseEntity<String> resp = paymentProxy.checkMail(paymentDto.bookingId());

        if(resp.getStatusCode().is4xxClientError()){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                    "booking id invalid !")).build();
        } else{

            try{
                RestTemplate rt = new RestTemplate();
                // adding a delay by purpose to mock that real payment is done ...
                Thread.sleep(2500);

               Optional <Booking> bookingResp = paymentProxy.findBookingById(paymentDto.bookingId());
               if (bookingResp.isPresent()){
                   var booking = bookingResp.get();
                   if(booking.getPaymentId() != null){
                       return ResponseEntity
                               .status(422)
                               .body(ProblemDetail
                                       .forStatusAndDetail(HttpStatus.valueOf(422),
                                               "payment already done !"));

                   }
                   var payment = paymentRepository.save(new Payment(LocalDateTime.now(), booking.getBill(),
                           "PAID"));
                   ResponseEntity<?> updateResp = paymentProxy.updatePayment(new
                           BookingPaymentDto(booking.getId(), payment.getId()));

                   if(updateResp.getStatusCode().is2xxSuccessful()){

                       var updatedBookingOptional = paymentProxy.findBookingById(booking.getId());
                       Booking ticketBooking = updatedBookingOptional.get();

                       System.out.println(ticketBooking);

                       var ticket = new tech.foxlo.paymentservice.dto.Booking(ticketBooking.getId(),
                               ticketBooking.getBill(), ticketBooking.getDateTime(),
                               ticketBooking.getFrom(), ticketBooking.getJourney(),
                               ticketBooking.getPaymentId(), ticketBooking.getTo());

                        ticketProducerService.publishTicket(ticket);
//                        rt.postForEntity("http://localhost:8084/api/ticket/book", ticket, Object.class);
                   }

                   return ResponseEntity.ok(updateResp.getBody());

               } else{
                   return ResponseEntity.notFound().build();
               }


            }catch (Exception ex){
               return ResponseEntity.ofNullable(ex.getMessage());
            }
        }



    }
}
