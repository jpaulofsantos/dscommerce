package com.jp.dscommerce.services;

import com.jp.dscommerce.dto.ProductDTO;
import com.jp.dscommerce.entities.Product;
import com.jp.dscommerce.repositories.ProductRepository;
import com.jp.dscommerce.services.exceptions.DataBaseException;
import com.jp.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ProductService { //service devolve um DTO para o Controller e trata as exceções

    @Autowired
    private ProductRepository productRepository; //injeta o productRepository

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product result = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID não encontrado")); //busca no banco de dados o objeto passando o ID e lança exceção se necessário
        return new ProductDTO(result); //cria um novo productDTO passando o product recuperado do banco
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> resultList = productRepository.findAll(pageable); //busca no banco de dados a lista com todos os registros
        return resultList.map(x-> new ProductDTO(x)); //converte a lista em um ProductDTO
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){ //recebe o JSON e instancia em um product DTO
        Product product = new Product();
        copyDtoToEntity(product, dto);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product product = productRepository.getReferenceById(id); //instancia um produto com a referência do id passado (não faz a busca no banco de dados)
            copyDtoToEntity(product, dto);
            product = productRepository.save(product);
            return new ProductDTO(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("ID não encontrado");
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

    public void copyDtoToEntity(Product product, ProductDTO dto){
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }
}
