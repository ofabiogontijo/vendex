package com.vendex.api.order.web;

import com.vendex.api.order.Order;
import com.vendex.api.order.OrderCommand;
import com.vendex.api.order.OrderQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
class OrderRestService {

    private final OrderCommand command;

    private final OrderQuery query;

    @PostMapping("/users/{userId}")
    @ResponseStatus(CREATED)
    Order create(@PathVariable UUID userId, @Valid @RequestBody Order order) {
        return command.create(userId, order);
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)
    Order findById(@PathVariable UUID id) {
        return query.findById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable UUID id, @RequestBody Order orderRequest) {
        return command.updateOrder(id, orderRequest);
    }

}