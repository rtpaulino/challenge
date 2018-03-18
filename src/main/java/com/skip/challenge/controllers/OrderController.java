package com.skip.challenge.controllers;


import com.skip.challenge.model.Order;
import com.skip.challenge.services.OrderService;
import com.skip.challenge.vo.OrderCancellationVO;
import com.skip.challenge.vo.OrderStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    @Autowired
    OrderService service;

    @RequestMapping(method = RequestMethod.POST)
    public Order create(@RequestBody @Valid Order order) {
        return service.create(order);
    }

    @RequestMapping(path = "/{id}/cancel", method = RequestMethod.POST)
    public void cancel(@PathVariable("id") Long id, @RequestBody @Valid OrderCancellationVO cancellationVO) {
        cancellationVO.setId(id);
        service.cancel(cancellationVO);
    }

    @RequestMapping(path = "/{id}/status", method = RequestMethod.GET)
    public OrderStatusVO status(@PathVariable("id") Long id) {
        return service.getStatus(id);
    }

}
