package com.vendex.api.payment.web;

import com.vendex.api.payment.Payment;
import com.vendex.api.payment.PaymentMethodEnum;
import com.vendex.api.payment.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentDTO(UUID id, LocalDateTime paymentDate, BigDecimal amount, PaymentStatusEnum status,
		PaymentMethodEnum paymentMethod, UUID orderId) {
	public static PaymentDTO from(Payment payment) {
		return new PaymentDTO(payment.getId(), payment.getPaymentDate(), payment.getAmount(), payment.getStatus(),
				payment.getPaymentMethod(), payment.getOrder().getId());
	}

}