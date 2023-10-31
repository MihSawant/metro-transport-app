package tech.foxlo.ticketservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import tech.foxlo.ticketservice.dto.Booking;
import tech.foxlo.ticketservice.entity.Ticket;
import tech.foxlo.ticketservice.repository.TicketRepository;

import java.util.logging.Logger;

@Service
@EnableKafka
public class TicketConsumerService {



    private static final Logger LOGGER = Logger.getLogger(TicketConsumerService.class.getName());

    @Autowired
    private TicketRepository ticketRepository;
    @KafkaListener(topics = "ticket-topic", groupId = "metro_ticket")
    public void subscribeTicket(Message<Booking> bookingMessage){

        var booking = bookingMessage.getPayload();

        Ticket ticket = new Ticket();
        ticket.setBookingId(booking.bookingId());
        ticket.setOrigin(booking.from());
        ticket.setDestination(booking.to());
        ticket.setPrice(booking.bill());
        ticket.setPaymentId(booking.paymentId());

        ticketRepository.save(ticket);

        LOGGER.info(String.format("----------> Message %s is Consumed <----------", booking));
    }


    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }
}
