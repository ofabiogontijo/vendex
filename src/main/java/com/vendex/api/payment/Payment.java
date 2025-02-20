package com.vendex.api.payment;

import com.vendex.api.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class Payment {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    private BigDecimal amount;

    private String status;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public static Payment of(LocalDateTime paymentDate, BigDecimal amount, String status, String paymentMethod, Order order) {
        return new Payment(null, paymentDate, amount, status, paymentMethod, order);
    }


}
