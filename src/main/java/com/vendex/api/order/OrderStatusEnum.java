package com.vendex.api.order;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    PENDING, PAID, SHIPPED, DELIVERED, CANCELED

}