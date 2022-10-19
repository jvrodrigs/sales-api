package com.app.salesapi.service;

import com.app.salesapi.model.Product;
import com.app.salesapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Page<Product> find(Product filter, Pageable pgRequest){
        Example<Product> example = Example.of(filter,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example, pgRequest);
    }

    public Product save(Product p){
        return this.repository.save(p);
    }

    public Optional<Product> findById(Integer id){
        return this.repository.findById(id);
    }

    public Product updateEntity(Integer id, Product product){
        return this.repository.findById(id)
                .map(p -> {
                   product.setId(p.getId());
                   product.setCreateAt(p.getCreateAt());
                   return repository.save(product);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
    }
}
