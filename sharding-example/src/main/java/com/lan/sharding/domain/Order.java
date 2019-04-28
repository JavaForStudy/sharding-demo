package com.lan.sharding.domain;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Order {
    private Long orderId;
    private String orderName;
    private Long userId;
}
