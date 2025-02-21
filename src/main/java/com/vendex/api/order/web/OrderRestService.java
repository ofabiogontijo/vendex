package com.vendex.api.order.web;

import com.vendex.api.order.Order;
import com.vendex.api.order.OrderCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
class OrderRestService {

    private final OrderCommand command;

    @PostMapping("/users/{userId}")
    @ResponseStatus(CREATED)
    Order create(@PathVariable UUID userId, @Valid @RequestBody Order order) {
        return command.create(userId, order);
    }

}