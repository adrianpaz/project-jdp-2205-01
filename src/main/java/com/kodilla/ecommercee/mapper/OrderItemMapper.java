package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.dto.OrderItemDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemMapper {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderItem mapToOrderItem(OrderItemDto orderItemDto) throws OrderNotFoundException, ProductNotFoundException {
        return new OrderItem(
                orderItemDto.getId(),
                orderService.getOrder(orderItemDto.getOrderId()),
                productService.getProduct(orderItemDto.getProductId()),
                orderItemDto.getQuantity()
        );
    }

    public OrderItemDto mapToOrderItemDto(OrderItem orderItem) throws OrderNotFoundException, ProductNotFoundException {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getOrder().getId(),
                orderItem.getProduct().getId(),
                orderItem.getQuantity()
        );
    }

    public List<OrderItem> mapToOrderItemList(List<OrderItemDto> orderItemDtoList) {
        List<OrderItem> collect = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtoList) {
            OrderItem orderItem = null;
            try {
                orderItem = mapToOrderItem(orderItemDto);
            } catch (OrderNotFoundException | ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(orderItem);
        }
        return collect;
    }

    public List<OrderItemDto> mapToOrderItemDtoList(List<OrderItem> orderItemList) {
        List<OrderItemDto> collect = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            OrderItemDto orderItemDto = null;
            try {
                orderItemDto = mapToOrderItemDto(orderItem);
            } catch (OrderNotFoundException | ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(orderItemDto);
        }
        return collect;
    }
}