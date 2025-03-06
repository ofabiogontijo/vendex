package com.vendex.api.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vendex.api.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@EqualsAndHashCode
@Table(name = "order_item")
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue
	private UUID id;

	private Integer quantity;

	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	private BigDecimal subtotal;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	public static OrderItem of(Integer quantity, Product product) {
		BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
		return new OrderItem(null, quantity, product.getPrice(), subtotal, null, product);
	}

	public void associateWithOrder(Order order) {
		this.order = order;
	}

}