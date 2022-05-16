package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Transactional
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
        Assert.assertEquals("CreateProductTest",test.get().getName());
        Assert.assertEquals("Create Test",test.get().getDescription());
        Assert.assertEquals(new BigDecimal("24.99"),test.get().getPrice());
        Assert.assertEquals("ProductTestGroup",test.get().getGroup().getName());
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




        Assert.assertFalse(productRepository.existsById(productEntity.getId()));
    }

    @Test
    public void shouldUpdateProduct(){

        String name = "ProductTest";
        String description = "Test";
        BigDecimal price = new BigDecimal("24.99");
        Group group = new Group("ProductTestGroup");
        Group groupEntity = groupRepository.save(group);

        String updatedName = "UpdatedProductTest";
        String updatedDescription = "Updated Test";
        BigDecimal updatedPrice = new BigDecimal("23.99");
        Group updatedGroup = new Group("UpdatedProductTestGroup");

        groupEntity = groupRepository.save(updatedGroup);


        Product productEntity = productRepository.save(new Product(name,description,price,group));
        productRepository.deleteById(productEntity.getId());

        productEntity = productRepository.save(new Product(updatedName,updatedDescription,updatedPrice,updatedGroup));
        Optional<Product> test = productRepository.findById(productEntity.getId());

        Assert.assertTrue(test.isPresent());
        Assert.assertEquals("UpdatedProductTest",test.get().getName());
        Assert.assertEquals("Updated Test",test.get().getDescription());
        Assert.assertEquals(new BigDecimal("23.99"),test.get().getPrice());
        Assert.assertEquals("UpdatedProductTestGroup",test.get().getGroup().getName());
    }

}
