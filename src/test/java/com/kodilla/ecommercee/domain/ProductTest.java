package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void shouldCreateProduct(){

        String name = "CreateProductTest";
        String description = "Create Test";
        BigDecimal price = new BigDecimal("24.99");
        Group group = new Group("ProductTestGroup");
        Group groupEntity = groupRepository.save(group);

        Product productEntity = productRepository.save(new Product(name,description,price,group));
        Optional<Product> test = productRepository.findById(productEntity.getId());

        Assert.assertTrue(test.isPresent());
    }

    @Test
    public void shouldReadProduct(){

        String name = "CreateProductTest";
        String description = "Create Test";
        BigDecimal price = new BigDecimal("24.99");
        Group group = new Group("ProductTestGroup");
        Group groupEntity = groupRepository.save(group);

        Product productEntity = productRepository.save(new Product(name,description,price,group));
        Optional<Product> test = productRepository.findById(productEntity.getId());

        Assert.assertTrue(test.isPresent());
        Assert.assertEquals(test.get().getName(),"CreateProductTest");
        Assert.assertEquals(test.get().getDescription(),"Create Test");
        Assert.assertEquals(test.get().getPrice(),new BigDecimal("24.99"));
        Assert.assertEquals(test.get().getGroup().getName(),"ProductTestGroup");
    }

    @Test
    public void shouldDeleteProduct(){

        String name = "CreateProductTest";
        String description = "Create Test";
        BigDecimal price = new BigDecimal("24.99");
        Group group = new Group("ProductTestGroup");
        Group groupEntity = groupRepository.save(group);

        Product productEntity = productRepository.save(new Product(name,description,price,group));
        productRepository.deleteById(productEntity.getId());




        Assert.assertTrue(productRepository.existsById(productEntity.getId()));
    }

}
