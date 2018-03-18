package com.skip.challenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skip.challenge.model.OrderStatus;

import java.time.OffsetDateTime;

public class OrderStatusVO {

    @JsonProperty(value = "status")
    private OrderStatus status;

    @JsonProperty(value = "cancelationDate")
    private OffsetDateTime cancellationDate;

    @JsonProperty(value = "cancelationReason")
    private String cancellationReason;

    public OrderStatusVO(OrderStatus status, OffsetDateTime cancellationDate, String cancellationReason) {
        this.status = status;
        this.cancellationDate = cancellationDate;
        this.cancellationReason = cancellationReason;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OffsetDateTime getCancellationDate() {
        return cancellationDate;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }
}
