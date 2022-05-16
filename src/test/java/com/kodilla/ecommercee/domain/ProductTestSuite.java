package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldCreateProduct(){
        //Given
        String name = "CreateProductTest";
        String description = "Create Test";
        BigDecimal price = new BigDecimal("24.99");

        //When
        Product productEntity = productRepository.save(new Product(name,description,price,null));
        Long savedProductId = productEntity.getId();

        //Then
        assertTrue(productRepository.existsById(savedProductId));

        //ClenUp
        productRepository.deleteById(productEntity.getId());
    }

    @Test
    public void shouldReadProduct(){
        //Given
        String name = "ReadProductTest";
        String description = "Read Test";
        BigDecimal price = new BigDecimal("24.99");

        //When
        Product productEntity = productRepository.save(new Product(name,description,price,null));
        Long savedProductId = productEntity.getId();

        //Then
        assertTrue(productRepository.findById(savedProductId).isPresent());
        assertEquals("ReadProductTest",productRepository.findById(savedProductId).get().getName());
        assertEquals("Read Test",productRepository.findById(savedProductId).get().getDescription());
        assertEquals(new BigDecimal("24.99"),productRepository.findById(savedProductId).get().getPrice());

        //ClenUp
        productRepository.deleteById(productEntity.getId());
    }

    @Test
    public void shouldDeleteProduct(){
        //Given
        String name = "DeleteProductTest";
        String description = "Delete Test";
        BigDecimal price = new BigDecimal("24.99");

        //When
        Product productEntity = productRepository.save(new Product(name,description,price,null));
        productRepository.deleteById(productEntity.getId());

        //Then
        assertFalse(productRepository.existsById(productEntity.getId()));
    }
}
