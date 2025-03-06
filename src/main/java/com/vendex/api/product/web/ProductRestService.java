package com.vendex.api.product.web;

import com.vendex.api.product.Product;
import com.vendex.api.product.ProductCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
class ProductRestService {

	private final ProductCommand command;

	@PostMapping
	@ResponseStatus(CREATED)
	Product create(@Valid @RequestBody Product product) {
		return command.create(product);
	}

}
