package com.skip.challenge.events;

import com.skip.challenge.model.Order;
import org.springframework.context.ApplicationEvent;

public class OrderCreationEvent extends ApplicationEvent {

    private Order order;

    public OrderCreationEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
