package com.jp.dscommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //possibilita resposta da classe na web
@RequestMapping(value = "/products")
public class ProductController {
    @GetMapping
    public String testeAPI() {
        return "Teste";
    }
}
