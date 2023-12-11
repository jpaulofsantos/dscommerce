package com.jp.dscommerce.services;

import com.jp.dscommerce.dto.ProductDTO;
import com.jp.dscommerce.entities.Product;
import com.jp.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService { //service devolve um DTO para o Controller
    @Autowired
    private ProductRepository productRepository; //injeta o productRepository
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = productRepository.findById(id); //busca no banco de dados o objeto passando o ID
        Product product = result.get(); //get no objeto
        return new ProductDTO(product); //cria um novo productDTO passando o product recuperado do banco
    }
}
