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

import static com.vendex.api.core.VendexBeanUtils.copyNonNullProperties;

@Service
@AllArgsConstructor
@Slf4j
public class OrderCommand {

    private final OrderRepository repository;

    private final UserQuery userQuery;

    private final ProductQuery productQuery;

    private final OrderQuery query;

    public Order create(UUID userId, Order order) {
        User user = userQuery.findById(userId);
        List<OrderItem> orderItems = createOrderItems(order);
        return repository.save(Order.of(user, orderItems, order.getStatus()));
    }

    private List<OrderItem> createOrderItems(Order orderRequest) {
        return orderRequest.getItems().stream()
                .map(item -> {
                    Product product = productQuery.findById(item.getProduct().getId());
                    return OrderItem.of(item.getQuantity(), product);
                })
                .collect(Collectors.toList());
    }

    public Order updateOrder(UUID id, Order orderRequest) {
        Order orderPersisted = query.findById(id);
        copyNonNullProperties(orderRequest, orderPersisted, "id", "user", "items");
        orderPersisted.calculateTotalAmount();
        return repository.save(orderPersisted);
    }

    public void markOrderAsPaid(Order order) {
        order.markAsPaid();
        repository.save(order);
    }

}