package com.jp.dscommerce.controllers;

import com.jp.dscommerce.dto.ProductDTO;
import com.jp.dscommerce.entities.Product;
import com.jp.dscommerce.repositories.ProductRepository;
import com.jp.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController //possibilita resposta da classe na web
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping(value = "/{id}")
    public ProductDTO findBtId(@PathVariable Long id) { //@PathVariable id é o mesmo id da rota do @GetMapping {id}
        return productService.findById(id);
    }
}
