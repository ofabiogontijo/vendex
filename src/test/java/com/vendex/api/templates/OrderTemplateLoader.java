package com.vendex.api.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.vendex.api.order.Order;
import com.vendex.api.order.OrderItem;
import com.vendex.api.order.OrderStatusEnum;
import com.vendex.api.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.vendex.api.support.FixtureTemplates.VALID;

public class OrderTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Order.class).addTemplate(VALID.name(), new Rule() {
			{
				add("id", UUID.randomUUID());
				add("orderDate", LocalDateTime.now());
				add("status", random(OrderStatusEnum.PENDING, OrderStatusEnum.PAID, OrderStatusEnum.SHIPPED));
				add("totalAmount",
						random(BigDecimal.valueOf(50.0), BigDecimal.valueOf(150.0), BigDecimal.valueOf(500.0)));
				add("user", one(User.class, VALID.name()));
				add("items", has(2).of(OrderItem.class, VALID.name()));
				add("payment", null);
			}
		});
	}

}
