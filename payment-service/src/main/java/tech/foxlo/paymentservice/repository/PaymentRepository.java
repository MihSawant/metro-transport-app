package tech.foxlo.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.foxlo.paymentservice.entity.Payment;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
