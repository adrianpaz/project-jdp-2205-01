package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;
    private final UserService userService;
    public Order mapToOrder(final OrderDto orderDto) throws UserNotFoundException {
        return new Order(
                orderDto.getId(),
                userService.getUser(orderDto.getUserId()),
                orderItemMapper.mapToOrderItemList(orderDto.getOrderItems())
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getId(),
                order.getUser().getId(),
                orderItemMapper.mapToOrderItemDtoList(order.getOrderItems())
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

    public List<Order> mapToOrderList(final List<OrderDto> orderDtoList) {
        List<Order> collect = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            Order order = null;
            try{
                order = mapToOrder(orderDto);
            }catch(UserNotFoundException e){
                e.printStackTrace();
            }
            collect.add(order);
        }
        return collect;
    }
}