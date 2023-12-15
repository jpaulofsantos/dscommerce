package com.jp.dscommerce.controllers;

import com.jp.dscommerce.dto.ProductDTO;
import com.jp.dscommerce.entities.Product;
import com.jp.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController //possibilita resposta da classe na web
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) { //@PathVariable id é o mesmo id da rota do @GetMapping {id}
        ProductDTO dto = productService.findById(id);
        return ResponseEntity.ok(dto); //customizando o response
    }

    @GetMapping //http://localhost:8080/products?size=12&page=1&sort=name,desc (passando um query param de 12 resultados por pagina e filtrando pela pagina 2 e ordenando pelo name em order descrescente)
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){ //retorno com paginação. Caso queira retornar todos os dados basta remover o parametro do metodo e alterar para List ao inves de Page
        Page<ProductDTO> dtoPage = productService.findAll(pageable);
        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) { //o corpo da requisição que chega, entra nesse parametro e instancia um novo dto. @Valid verifica
        ProductDTO productDTO = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(productDTO); //passando o cod HTTP 201 de created
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto){
        dto = productService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build(); //returns 204 ok (no body)
    }
}
