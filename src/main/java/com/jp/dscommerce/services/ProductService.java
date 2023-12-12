package com.jp.dscommerce.services;

import com.jp.dscommerce.dto.ProductDTO;
import com.jp.dscommerce.entities.Product;
import com.jp.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> resultList = productRepository.findAll(pageable); //busca no banco de dados a lista com todos os registros
        return resultList.map(x-> new ProductDTO(x)); //converte a lista em um ProductDTO
    }
}
