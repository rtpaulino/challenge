package com.skip.challenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skip.challenge.model.OrderStatus;

public class OrderStatusUpdateVO {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "status")
    private OrderStatus status;

    public OrderStatusUpdateVO() {}

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

}
