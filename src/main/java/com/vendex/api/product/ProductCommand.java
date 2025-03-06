package com.vendex.api.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCommand {

	private final ProductRepository repository;

	public Product create(Product product) {
		return repository.save(Product.of(product));
	}

}
