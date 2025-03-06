package com.vendex.api.payment.web;

import com.vendex.api.payment.PaymentCommand;
import com.vendex.api.payment.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
class PaymentRestService {

	private final PaymentCommand paymentCommand;

	@PostMapping("/{orderId}")
	@ResponseStatus(CREATED)
	public PaymentDTO payOrder(@PathVariable UUID orderId, @RequestBody PaymentMethodEnum paymentMethod) {
		return paymentCommand.processPayment(orderId, paymentMethod);
	}

}
