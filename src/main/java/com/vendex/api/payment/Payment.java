package com.vendex.api.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethodEnum paymentMethod;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public void approve() {
        this.status = PaymentStatusEnum.COMPLETED;
    }

    public void fail() {
        this.status = PaymentStatusEnum.FAILED;
    }

    public static Payment of(LocalDateTime paymentDate, BigDecimal amount, PaymentStatusEnum status, PaymentMethodEnum paymentMethod, Order order) {
        return new Payment(null, paymentDate, amount, status, paymentMethod, order);
    }

}