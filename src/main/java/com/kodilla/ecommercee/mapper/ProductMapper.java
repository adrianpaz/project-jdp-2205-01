package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.GroupDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductMapper {
    private final CartItemMapper cartItemMapper;
    private final OrderItemMapper orderItemMapper;
    private final GroupDBService groupDBService;
    public Product mapToProduct(final ProductDto productDto) throws GroupNotFoundException {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupDBService.getGroupById(productDto.getGroupId()),
                cartItemMapper.mapToCartItemList(productDto.getCartItems()),
                orderItemMapper.mapToOrderItemList(productDto.getOrderItems())
        );
    }

    public ProductDto mapToProductDto(final Product product) throws GroupNotFoundException {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup().getId(),
                cartItemMapper.mapToCartItemDtoList(product.getCartItems()),
                orderItemMapper.mapToOrderItemDtoList(product.getOrderItems())
        );
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        List<ProductDto> collect = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = null;
            try {
                productDto = mapToProductDto(product);
            } catch (GroupNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(productDto);
        }
        return collect;
    }

    public List<Product> mapToProductList(List<ProductDto> productDtoList) {
        List<Product> collect = new ArrayList<>();
        for (ProductDto productDto : productDtoList) {
            Product product = null;
            try {
                product = mapToProduct(productDto);
            } catch (GroupNotFoundException e) {
                e.printStackTrace();
            }
            collect.add(product);
        }
        return collect;
    }
}