package com.vendex.api.order;

import com.vendex.api.core.VendexMessageSource;
import com.vendex.api.core.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderQuery {

    private final OrderRepository repository;

    private final VendexMessageSource messageSource;

    public Order findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("order.not.found", id), true));
    }

}