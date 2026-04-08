package com.gablins.virtual_store.services;

import com.gablins.VO.ProductVO;
import com.gablins.exceptions.ProductNotFoundException;
import com.gablins.virtual_store.entities.Product;
import com.gablins.virtual_store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{

    @Autowired
    private ProductRepository productRepository;

    public List<ProductVO> findAll()
    {
       return ProductVO.fromEntities(productRepository.findAll());
    }
    public ProductVO findById(Long id)
    {
       if(!productRepository.existsById(id))
       {
           throw new ProductNotFoundException("Produto com id:" +" " + id + " não encontrado.");
       }
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado."));
       return ProductVO.fromEntity(product);
    }
    public void delete(Long id)
    {
        Product product = Product.convertToProduct(findById(id));
       productRepository.delete(product);
    }
    public ProductVO create(Product product)
    {
        Product prod =  productRepository.save(product);
        return ProductVO.fromEntity(prod);
    }

    public ProductVO update(Product product)
    {
        Product product1 = productRepository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException("Produto com id: " + product.getId() + " não encontrado." ));
        product1.setName(product.getName());
        product1.setQuantity(product.getQuantity());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        Product prod =  productRepository.save(product1);
        return ProductVO.fromEntity(prod);
    }








}
