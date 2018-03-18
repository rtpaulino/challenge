package com.skip.challenge.repositories;

import com.skip.challenge.model.Order;
import com.skip.challenge.vo.OrderStatusVO;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Optional<Order> findByIdForUpdate(Long id);

    @Query("SELECT new com.skip.challenge.vo.OrderStatusVO(o.status, o.cancelationDate, o.cancelationReason) FROM Order o WHERE o.id = ?1")
    Optional<OrderStatusVO> getStatus(Long id);
}
