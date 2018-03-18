package com.skip.challenge.services;

import com.skip.challenge.exception.BadRequestException;
import com.skip.challenge.exception.ResourceNotFoundException;
import com.skip.challenge.model.Order;
import com.skip.challenge.model.OrderStatus;
import com.skip.challenge.model.Product;
import com.skip.challenge.repositories.OrderRepository;
import com.skip.challenge.security.IAuthenticationFacade;
import com.skip.challenge.vo.OrderCancellationVO;
import com.skip.challenge.vo.OrderStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    ProductService productService;

    @Autowired
    IAuthenticationFacade authenticationFacade;

    private Long getUserId() {
        return authenticationFacade.getUserId()
                .orElseThrow(() -> new RuntimeException("Cannot acquire user id"));
    }

    private Product getProduct(Long productId) {
        return productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product of id %d not found", productId)));
    }

    private void updateOrderDetails(Order order) {
        order.getItems().stream().forEach(item -> {
            item.setProduct(getProduct(item.getProductId()));
            item.setPrice(item.getProduct().getPrice());
            item.setTotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        });

        order.setTotal(
                order.getItems()
                        .stream()
                        .map(item -> item.getTotal())
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public Order create(Order order) {
        OffsetDateTime now = OffsetDateTime.now();
        order.setStatus(OrderStatus.CREATED);
        order.setUserId(getUserId());
        order.setDate(now);
        order.setLastUpdate(now);
        updateOrderDetails(order);

        return repository.save(order);
    }

    @Transactional
    public void cancel(OrderCancellationVO cancellationVO) {
        Order order = repository.findByIdForUpdate(cancellationVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order of id %d not found", cancellationVO.getId())));

        if (!order.getUserId().equals(getUserId())) {
            throw new BadRequestException("You can't cancel an order not requested by you.");
        }
        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new BadRequestException("Cannot cancel order because it has already been delivered.");
        }
        if (order.getStatus() == OrderStatus.CANCELED) {
            throw new BadRequestException("Cannot cancel order because it has already been canceled.");
        }

        OffsetDateTime now = OffsetDateTime.now();
        order.setLastUpdate(now);
        order.setCancelationDate(now);
        order.setCancelationReason(cancellationVO.getReason());
        repository.save(order);
    }

    public OrderStatusVO getStatus(Long id) {
        return repository.getStatus(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order of id %d not found", id)));
    }
}
