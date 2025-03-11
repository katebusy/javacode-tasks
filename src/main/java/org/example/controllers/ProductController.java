package org.example.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.example.entities.Product;
import org.example.models.ProductDTO;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private ObjectMapper objectMapper;
    private static final String ID = "/{id}";
    @Autowired
    public ProductController(ProductService service,
                             ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<Product> products = service.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .toList();
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping(ID)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) {
        Product product = service.getProductById(id);
        return ResponseEntity.ok(objectMapper.convertValue(product, ProductDTO.class));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = objectMapper.convertValue(productDTO, Product.class);
        Product createdProduct = service.createProduct(product);
        ProductDTO createdProductDTO = objectMapper.convertValue(createdProduct, ProductDTO.class);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdProductDTO.getId())
                        .toUri()
        ).body(createdProductDTO);
    }

    @PutMapping(ID)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductDTO productDTO) {
        Product updatedProduct = objectMapper.convertValue(productDTO, Product.class);
        Product savedProduct = service.updateProduct(id, updatedProduct);
        return ResponseEntity.ok(objectMapper.convertValue(savedProduct, ProductDTO.class));
    }

    @DeleteMapping(ID)
    public void deleteProduct(@PathVariable UUID id) {
        service.deleteProduct(id);
    }

}
