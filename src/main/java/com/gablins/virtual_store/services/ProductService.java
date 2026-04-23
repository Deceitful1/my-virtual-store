package com.gablins.virtual_store.services;

import com.gablins.VO.ProductVO;
import com.gablins.exceptions.ProductNotFoundException;
import com.gablins.virtual_store.controllers.StoreController;
import com.gablins.virtual_store.entities.Product;
import com.gablins.virtual_store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService
{

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<ProductVO> findAll()
    {

        var result = ProductVO.fromEntities(productRepository.findAll());
        result.forEach(r -> addHateoasLinks(r));
        return result;

    }

    public ProductVO findById(Long id)
    {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Produto com id:" + " " + id + " não encontrado.");
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado."));
        var result = ProductVO.fromEntity(product);
        addHateoasLinks(result);
        return result;
    }

    public void delete(Long id)
    {
        Product product = Product.convertToProduct(findById(id));
        productRepository.delete(product);

    }

    public ProductVO create(Product product)
    {
        Product prod = productRepository.save(product);
        var entity = ProductVO.fromEntity(prod);
        addHateoasLinks(entity);
        return entity;

    }

    public ProductVO update(Product product)
    {
        Product product1 = productRepository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException("Produto com id: " + product.getId() + " não encontrado."));
        product1.setName(product.getName());
        product1.setQuantity(product.getQuantity());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        Product prod = productRepository.save(product1);

        var entity = ProductVO.fromEntity(prod);
        addHateoasLinks(entity);
        return entity;
    }


    public static void addHateoasLinks(ProductVO product)
    {
        product.add(linkTo(methodOn(StoreController.class).findById(product.getId())).withRel("findById").withType("GET"));
        product.add(linkTo(methodOn(StoreController.class).findAll()).withRel("findAll").withType("GET"));
        product.add(linkTo(methodOn(StoreController.class).addProduct(Product.convertToProduct(product))).withRel("addProduct").withType("POST"));
        product.add(linkTo(methodOn(StoreController.class).updateProduct(Product.convertToProduct(product))).withRel("updateProduct").withType("PUT"));
        product.add(linkTo(methodOn(StoreController.class).deleteProduct(product.getId())).withRel("deleteProduct").withType("DELETE"));
    }


}
