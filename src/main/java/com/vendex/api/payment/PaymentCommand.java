package com.vendex.api.payment;

import com.vendex.api.order.Order;
import com.vendex.api.order.OrderCommand;
import com.vendex.api.order.OrderQuery;
import com.vendex.api.payment.web.PaymentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PaymentCommand {

	private final PaymentRepository repository;

	private final OrderQuery orderQuery;

	private final OrderCommand orderCommand;

	public PaymentDTO processPayment(UUID orderId, PaymentMethodEnum method) {
		Order order = orderQuery.findById(orderId);
		validatePayment(order);
		Payment payment = createPayment(order, method);
		repository.save(payment);
		return PaymentDTO.from(finalizePayment(payment, order));
	}

	private void validatePayment(Order order) {
		if (order.hasPayment()) {
			throw new IllegalStateException("Order already has a payment.");
		}
		if (order.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalStateException("Order total amount must be greater than zero.");
		}
	}

	private Payment createPayment(Order order, PaymentMethodEnum method) {
		return Payment.of(LocalDateTime.now(), order.getTotalAmount(), PaymentStatusEnum.PENDING, method, order);
	}

	private Payment finalizePayment(Payment payment, Order order) {
		if (approvePayment()) {
			approveAndMarkOrderAsPaid(payment, order);
		}
		else {
			rejectPayment(payment);
		}
		return repository.save(payment);
	}

	private boolean approvePayment() {
		return Math.random() > 0.2;
	}

	private void approveAndMarkOrderAsPaid(Payment payment, Order order) {
		payment.approve();
		orderCommand.markOrderAsPaid(order);
	}

	private void rejectPayment(Payment payment) {
		payment.fail();
	}

}