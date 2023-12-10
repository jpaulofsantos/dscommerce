package com.jp.dscommerce.controllers;

import com.jp.dscommerce.entities.Product;
import com.jp.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController //possibilita resposta da classe na web
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductRepository repository; //injetando a dependência de ProductRepository
    @GetMapping
    public String testeAPI() {
        Optional<Product> product = repository.findById(1L);
        return product.get().getName();
    }
}
