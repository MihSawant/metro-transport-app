package tech.foxlo.bookingservice.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "from_stn")
    private String from;

    @Column(name = "to_stn")
    private String to;

    private LocalDateTime dateTime;

    private String journey;

    private int tickets;

    private long bill;

    public Booking(){};

    private UUID paymentId;

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public Booking(String from, String to, LocalDateTime localDateTime, long bill,
                   int tickets,
                   String journey){
        this.from = from;
        this.to = to;
        this.dateTime = localDateTime;
        this.bill = bill;
        this.journey = journey;
        this.tickets = tickets;
    };

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getBill() {
        return bill;
    }

    public void setBill(long bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", dateTime=" + dateTime +
                ", journey='" + journey + '\'' +
                ", tickets=" + tickets +
                ", bill=" + bill +
                ", paymentId=" + paymentId +
                '}';
    }
}

