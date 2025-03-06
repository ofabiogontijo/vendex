package com.vendex.api.shipment;

import com.vendex.api.order.Order;
import com.vendex.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class Shipment {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(name = "tracking_number")
	private String trackingNumber;

	private String carrier;

	@Column(name = "estimated_delivery_date")
	private LocalDate estimatedDeliveryDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "shipping_status")
	private ShippingStatusEnum shippingStatus;

	@OneToOne
	@JoinColumn(name = "order_id", nullable = false, unique = true)
	private Order order;

	public static Shipment of(Shipment shipment, Order order) {
		return new Shipment(null, shipment.getTrackingNumber(), shipment.getCarrier(),
				shipment.getEstimatedDeliveryDate(), shipment.getShippingStatus(), order);
	}

}
