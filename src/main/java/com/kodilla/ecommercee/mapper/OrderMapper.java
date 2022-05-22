package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                null,
                null
        );
    }

    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getId(),
                null,
                null
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public List<Order> mapToOrderList(final List<OrderDto> orderDtoList) {
        return orderDtoList.stream()
                .map(this::mapToOrder)
                .collect(Collectors.toList());
    }

}
