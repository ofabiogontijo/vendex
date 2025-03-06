package com.vendex.api.product;

import com.vendex.api.core.VendexMessageSource;
import com.vendex.api.core.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductQuery {

	private final ProductRepository repository;

	private final VendexMessageSource messageSource;

	public Product findById(UUID id) {
		return repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(messageSource.getMessage("product.not.found", id), true));
	}

}