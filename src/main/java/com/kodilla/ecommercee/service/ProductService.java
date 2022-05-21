package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.CartItem;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(final Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public void addCartItem(final CartItem cartItem){
        productRepository.findById(cartItem.getProduct().getId()).get().getCartItems().add(cartItem);
    }

    public void removeCartItem(final Long productId, final Long cartItemId){
        productRepository.findById(productId).get().getCartItems()
                .removeIf(cartItem -> cartItem.getId().longValue() == cartItemId.longValue());
    }

    public void removeCartItems(List <CartItem> cartItems){
        for(CartItem cartItem: cartItems){
            productRepository.findById(cartItem.getProduct().getId()).get().getCartItems()
                    .removeIf(Item -> Item.getId().longValue() == cartItem.getId().longValue());
        }
    }
}

