package com.vendex.api.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vendex.api.payment.Payment;
import com.vendex.api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PUBLIC;

@Entity
@Getter
@EqualsAndHashCode
@Table(name = "orders")
@NoArgsConstructor(access = PUBLIC)
@AllArgsConstructor(access = PUBLIC)
public class Order {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(name = "order_date")
	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderStatusEnum status;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items;

	@OneToOne(mappedBy = "order")
	private Payment payment;

	public void markAsPaid() {
		this.status = OrderStatusEnum.PAID;
	}

	public boolean hasPayment() {
		return this.payment != null;
	}

	public static Order of(User user, List<OrderItem> orderItems, OrderStatusEnum status) {
		Order order = new Order(null, LocalDateTime.now(), status, BigDecimal.ZERO, user, orderItems, null);
		orderItems.forEach(item -> item.associateWithOrder(order));
		order.calculateTotalAmount();
		return order;
	}

	void calculateTotalAmount() {
		this.totalAmount = items.stream().map(OrderItem::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}