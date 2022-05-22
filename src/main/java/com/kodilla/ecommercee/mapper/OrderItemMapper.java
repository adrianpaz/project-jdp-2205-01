package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.OrderItem;
import com.kodilla.ecommercee.dto.OrderItemDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemMapper {

    private OrderService orderService;
    private ProductService productService;

    public OrderItem mapToOrderItem(OrderItemDto orderItemDto) throws OrderNotFoundException, ProductNotFoundException {
        return new OrderItem(
                orderItemDto.getId(),
                orderService.getOrder(orderItemDto.getOrder().getId()),
                productService.getProduct(orderItemDto.getProduct().getId()),
                orderItemDto.getQuantity()
        );
    }

    public OrderItemDto mapToOrderItemDto(OrderItem orderItem) throws OrderNotFoundException, ProductNotFoundException {
        return new OrderItemDto(
                orderItem.getId(),
                orderService.getOrder(orderItem.getOrder().getId()),
                productService.getProduct(orderItem.getProduct().getId()),
                orderItem.getQuantity()
        );
    }

    public List<OrderItem> mapToOrderItemList(List<OrderItemDto> orderItemDtoList) {
        List<OrderItem> collect = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderItemDtoList) {
            OrderItem orderItem = null;
            try {
                orderItem = mapToOrderItem(orderItemDto);
            } catch (OrderNotFoundException e) {
                e.printStackTrace();
            } catch (ProductNotFoundException e) {
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
            } catch (OrderNotFoundException e) {
                e.printStackTrace();
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(orderItemDto);
        }
        return collect;
    }
}