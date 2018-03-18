package com.skip.challenge.listener;

import com.skip.challenge.events.OrderCancellationEvent;
import com.skip.challenge.events.OrderCreationEvent;
import com.skip.challenge.services.OrderService;
import com.skip.challenge.vo.OrderStatusUpdateVO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {

    @Autowired
    private OrderService service;

    @Autowired
    private AmqpTemplate template;

    @EventListener
    public void onOrderCreated(OrderCreationEvent event) {
        template.convertAndSend("new_order_queue", event.getOrder());
    }

    @EventListener
    public void onOrderCancelled(OrderCancellationEvent event) {
        template.convertAndSend("cancel_order_queue", event.getOrder());
    }

    @RabbitListener(queues = "update_status_queue")
    public void onOrderUpdateReceived(OrderStatusUpdateVO statusUpdateVO) {
        // we might need to make some decisions here about
        // what will we do when errors happen?
        // should we throw an error and let the broker fire retries
        // or route to an error queue to future evaluation?
        // or there are any possible errors that should be send
        // to specific queues?
        service.updateStatus(statusUpdateVO);
    }
}
