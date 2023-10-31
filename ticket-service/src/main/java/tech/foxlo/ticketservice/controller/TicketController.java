package tech.foxlo.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.foxlo.ticketservice.dto.BookingTicketDto;
import tech.foxlo.ticketservice.entity.Ticket;
import tech.foxlo.ticketservice.kafka.TicketConsumerService;
import tech.foxlo.ticketservice.repository.TicketRepository;

import java.util.Optional;
import java.util.UUID;


@RestController
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketConsumerService ticketConsumerService;



    @GetMapping("/api/ticket/get/booking")
    public ResponseEntity<?> getTicketByBooking(@RequestBody BookingTicketDto dto){
        var ticket = ticketRepository.findByBookingId(dto.bookingId());
        if(ticket.isPresent()){
            return ResponseEntity.ok(ticket.get());
        } else{
            return ResponseEntity.notFound().build();
        }



    }

    @PostMapping("/api/ticket/book")
    public void addTicket(@RequestBody Ticket ticket){
        ticketConsumerService.addTicket(ticket);
    }

}
