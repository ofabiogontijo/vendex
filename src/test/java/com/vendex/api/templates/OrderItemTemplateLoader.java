package com.vendex.api.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.vendex.api.order.OrderItem;
import com.vendex.api.product.Product;

import java.math.BigDecimal;
import java.util.UUID;

import static com.vendex.api.support.FixtureTemplates.VALID;
import static javax.swing.UIManager.get;

public class OrderItemTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(OrderItem.class).addTemplate(VALID.name(), new Rule() {
			{
				add("id", UUID.randomUUID());
				add("quantity", random(Integer.class, range(1, 5)));
				add("unitPrice", random(BigDecimal.valueOf(10.0), BigDecimal.valueOf(50.0), BigDecimal.valueOf(100.0)));
				add("subtotal", random(BigDecimal.valueOf(10.0), BigDecimal.valueOf(50.0), BigDecimal.valueOf(100.0)));
				add("order", null);
				add("product", one(Product.class, VALID.name()));
			}
		});
	}

}
