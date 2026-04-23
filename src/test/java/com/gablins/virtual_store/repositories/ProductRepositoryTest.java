package com.gablins.virtual_store.repositories;

import com.gablins.VO.ProductVO;
import com.gablins.virtual_store.entities.Product;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest
{
    @Autowired
    EntityManager entityManager;

    @Test
    public void create()
    {
        ProductVO product = new ProductVO(1L, "chips", "salty chips", 4.50, 5);

        System.out.println(product.getId());
        Product prod = Product.convertToProduct(product);
        System.out.println(prod.getId());

        entityManager.persist(prod);
        assertNotNull(prod.getId());
        Assertions.assertEquals(product.getId(), prod.getId());


    }
    @Test
    public void update()
    {
        Product prod = new Product();

    }


}