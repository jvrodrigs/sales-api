package com.app.salesapi.controller;

import com.app.salesapi.model.Product;
import com.app.salesapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public Page<Product> getAllProducts(Product filter, Pageable pgRequest){
        Page<Product> result = service.find(filter, pgRequest);

        List<Product> transfListProduct = result.getContent().stream().collect(Collectors.toList());
        return new PageImpl<>(transfListProduct, pgRequest, result.getTotalElements());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product p){
        Product productSaved = service.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getFindById(@PathVariable("id") Integer id){
        Product resultFindProduct = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.status(HttpStatus.OK).body(resultFindProduct);
    }

    @GetMapping("/{id}/update")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product productUpdate){
        Product updatedProduct = service.updateEntity(id, productUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }
}
