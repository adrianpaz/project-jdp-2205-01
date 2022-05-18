package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void start() {
        groupRepository.deleteAll();
    }

    private Group clothes;
    private Group footwear;
    private Product product1;
    private Product product2;

    @Test
    public void testCreateGroup() {
        //Given
        clothes = new Group("clothes");
        product1 = new Product("shirt",
                "nice shirt", new BigDecimal("10.99"), clothes);
        product2 = new Product("trousers",
                "lovely pair of trousers", new BigDecimal("99.99"),clothes);

        //When
        Group testedGroup = groupRepository.save(clothes);
        testedGroup.getProducts().add(product1);
        testedGroup.getProducts().add(product2);
        Long testedGroupId = testedGroup.getId();

        List<Product> products = productRepository.findAll();
        Long testedProduct1Id = products.get(products.indexOf(product1)).getId();
        Long testedProduct2Id = products.get(products.indexOf(product2)).getId();

        //Then
        assertTrue(groupRepository.existsById(testedGroupId));
        assertEquals("shirt", products.get(products.indexOf(product1)).getName());
        assertEquals("lovely pair of trousers", products.get(products.indexOf(product2)).getDescription());
        assertEquals(new BigDecimal("99.99"),products.get(products.indexOf(product2)).getPrice());
        assertEquals(2, testedGroup.getProducts().size());

        //Cleanup
        groupRepository.deleteById(testedGroupId);
        productRepository.deleteById(testedProduct1Id);
        productRepository.deleteById(testedProduct2Id);
    }

    @Test
    public void testFindGroupById() {
        //Given
        footwear = new Group("footwear");

        //When
        Group testedGroup = groupRepository.save(footwear);
        Long testedGroupId = testedGroup.getId();
        Group newGroup = groupRepository.findById(footwear.getId()).get();
        Long newGroupId = newGroup.getId();

        //Then
        assertNotNull(newGroup);
        assertEquals(testedGroupId, newGroupId);

        //Cleanup
        groupRepository.deleteAll();
    }

    @Test
    public void testUpdateGroup() {
        //Given
        footwear = new Group("footwear");
        Group testedGroup = groupRepository.save(footwear);
        String updatedName = "footwear updated";
        Group updatedGroup = groupRepository.findById(testedGroup.getId()).get();

        //When
        updatedGroup.setName(updatedName);
        updatedGroup = groupRepository.findById(testedGroup.getId()).get();

        //Then
        assertNotNull(updatedGroup);
        assertEquals(updatedName, updatedGroup.getName());

        //Cleanup
        groupRepository.deleteAll();
    }

    @Test
    public void testDeleteGroup() {
        //Given
        footwear = new Group("footwear");
        clothes = new Group("clothes");
        Group testedGroup1 = groupRepository.save(footwear);
        Group testedGroup2 = groupRepository.save(clothes);
        List<Group> groups2 = groupRepository.findAll();

        //When
        groupRepository.deleteById(testedGroup2.getId());
        List<Group> groups1 = groupRepository.findAll();

        //Then
        assertEquals(2, groups2.size());
        assertEquals(1, groups1.size());

        //Cleanup
        groupRepository.deleteAll();
    }
}