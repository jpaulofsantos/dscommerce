package com.jp.dscommerce.controllers;

import com.jp.dscommerce.dto.ProductDTO;
import com.jp.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController //possibilita resposta da classe na web
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) { //@PathVariable id é o mesmo id da rota do @GetMapping {id}
        return productService.findById(id);
    }

    @GetMapping //http://localhost:8080/products?size=12&page=1&sort=name,desc (passando um query param de 12 resultados por pagina e filtrando pela pagina 2 e ordenando pelo name em order descrescente)
    public Page<ProductDTO> findAll(Pageable pageable){ //retorno com paginação. Caso queira retornar todos os dados basta remover o parametro do metodo e alterar para List ao inves de Page
        return productService.findAll(pageable);
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto) { //o corpo da requisição que chega, entra nesse parametro e instancia um novo dto
        return productService.insert(dto);
    }
}
