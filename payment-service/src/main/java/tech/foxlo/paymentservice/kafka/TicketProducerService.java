package tech.foxlo.paymentservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import tech.foxlo.paymentservice.dto.Booking;
import tech.foxlo.paymentservice.proxy.PaymentProxy;

import java.util.UUID;

@Service
public class TicketProducerService {

    @Autowired
    private KafkaTemplate<String, Booking> kafkaTemplate;


    public void publishTicket(Booking booking){
        Message<Booking> bookingMessage = MessageBuilder.withPayload(booking)
                .setHeader(KafkaHeaders.TOPIC, "ticket-topic")
                .build();

        kafkaTemplate.send(bookingMessage);
    }


}
