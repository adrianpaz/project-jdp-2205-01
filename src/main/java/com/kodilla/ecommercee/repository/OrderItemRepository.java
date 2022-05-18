package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

    @Override
    List<OrderItem> findAll();
}