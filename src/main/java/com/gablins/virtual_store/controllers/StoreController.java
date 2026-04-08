package com.gablins.virtual_store.controllers;

import com.gablins.VO.ProductVO;
import com.gablins.virtual_store.entities.Product;
import com.gablins.virtual_store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/store")
public class StoreController
{
    @Autowired
    private ProductService productService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductVO>> findAll()
    {
        return ResponseEntity.status(200).body(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductVO> addProduct(@RequestBody Product product)
    {
       return ResponseEntity.status(201).body(productService.create(product));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ProductVO> getProduct(@PathVariable Long id)
    {

        return ResponseEntity.ok(productService.findById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ProductVO> updateProduct(@RequestBody Product product)
    {
        return ResponseEntity.ok(productService.update(product));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Value("id") Long id)
    {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
