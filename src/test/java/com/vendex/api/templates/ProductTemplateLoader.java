package com.vendex.api.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.vendex.api.product.Product;

import java.math.BigDecimal;
import java.util.UUID;

import static com.vendex.api.support.FixtureTemplates.VALID;

public class ProductTemplateLoader implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Product.class).addTemplate(VALID.name(), new Rule() {
			{
				add("id", UUID.randomUUID());
				add("name", random("Laptop", "Smartphone", "Tablet", "Headphones"));
				add("description", random("High-performance laptop", "Latest model smartphone", "Compact tablet",
						"Noise-canceling headphones"));
				add("price", random(BigDecimal.valueOf(100.0), BigDecimal.valueOf(500.0), BigDecimal.valueOf(1000.0)));
				add("stockQuantity", random(Integer.class, range(10, 100)));
			}
		});
	}

}
