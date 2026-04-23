package com.gablins.virtual_store.repositories;

import com.gablins.VO.ProductVO;
import com.gablins.virtual_store.entities.Product;
import com.gablins.virtual_store.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest
{
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void findById()
    {
        Product product = new Product("Mouse Gamer", "8000 DPI", 120.50, 5);
        product.setId(1L);


        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));


        var entity = productService.findById(1L);

        ProductService.addHateoasLinks(entity);

        System.out.println(entity.getLinks());

        Assertions.assertNotNull(entity);

        Assertions.assertTrue(entity.hasLinks());
        assertEquals(entity.getId(), product.getId());
        assertEquals(entity.getName(), product.getName());
        assertEquals(entity.getPrice(), product.getPrice());
        assertEquals(entity.getQuantity(), product.getQuantity());
        assertEquals(entity.getDescription(), product.getDescription());


        System.out.println(entity.getId());


    }

    @Test
    void findAll()
    {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Mouse Gamer", "8000 DPI", 120.50, 10));
        products.get(0).setId(1L);

        products.add(new Product("RTX 4090", "Placa de vídeo", 2000, 10));
        products.get(1).setId(2L);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductVO> entity = productService.findAll();
        entity.forEach(p -> ProductService.addHateoasLinks(p));


        Assertions.assertNotNull(entity);
        entity.forEach(p -> Assertions.assertTrue(p.hasLinks()));

        Assertions.assertTrue(entity.stream().anyMatch(p -> p.getId() == products.get(0).getId()));
        Assertions.assertTrue(entity.stream().anyMatch(p -> p.getId() == products.get(1).getId()));

        for (int i = 0; i < entity.size(); i++) {


            assertEquals(entity.get(i).getId(), products.get(i).getId());
            assertEquals(entity.get(i).getName(), products.get(i).getName());
            assertEquals(entity.get(i).getPrice(), products.get(i).getPrice());
            assertEquals(entity.get(i).getQuantity(), products.get(i).getQuantity());
            assertEquals(entity.get(i).getDescription(), products.get(i).getDescription());
        }

    }

    @Test
    void update()
    {
        Product product = new Product("Mouse Gamer", "8000 DPI", 120.50, 5);
        Product updatedProduct = new Product("Mouse Gamer", "8000 DPI", 150.50, 6);
        product.setId(1L);
        updatedProduct.setId(1L);

        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(updatedProduct);

        var entity = productService.findById(1L);

        var result = productService.update(product);
        ProductService.addHateoasLinks(result);

        Assertions.assertEquals(product.getId(), result.getId());
        Assertions.assertNotEquals(product.getPrice(), result.getPrice());
        Assertions.assertNotEquals(product.getQuantity(), result.getQuantity());
        Assertions.assertTrue(result.hasLinks());


    }

    @Test
    void create()
    {
        Product product = new Product("Mouse Gamer", "8000 DPI", 120.50, 5);
        when(productRepository.save(product)).thenReturn(product);
        var entity = productService.create(product);
        ProductService.addHateoasLinks(entity);

        Assertions.assertNotNull(entity);
        Assertions.assertTrue(entity.hasLinks());
        Assertions.assertTrue(entity.getId() == product.getId());
        Assertions.assertTrue(entity.getName().equals(product.getName()));
        Assertions.assertTrue(entity.getPrice() == product.getPrice());
        Assertions.assertTrue(entity.getQuantity() == product.getQuantity());
        Assertions.assertTrue(entity.getDescription().equals(product.getDescription()));

    }


}

