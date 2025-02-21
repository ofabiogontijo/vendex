package com.vendex.api.order;

import com.vendex.api.product.Product;
import com.vendex.api.product.ProductQuery;
import com.vendex.api.user.User;
import com.vendex.api.user.UserQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderCommand {

    private final OrderRepository repository;

    private final UserQuery userQuery;

    private final ProductQuery productQuery;

    public Order create(UUID userId, Order orderRequest) {
        User user = userQuery.findById(userId);
        List<OrderItem> orderItems = createOrderItems(orderRequest);
        Order newOrder = Order.of(user, orderItems);
        return repository.save(newOrder);
    }

    private List<OrderItem> createOrderItems(Order orderRequest) {
        return orderRequest.getItems().stream()
                .map(item -> {
                    Product product = productQuery.findById(item.getProduct().getId());
                    return OrderItem.of(item.getQuantity(), product);
                })
                .collect(Collectors.toList());
    }

}