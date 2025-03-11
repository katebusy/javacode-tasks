package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.entities.Product;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no product with this id: %s".formatted(id)));
    }
    @Transactional
    public Product createProduct(Product product) {
        return repository.save(product);
    }
    @Transactional
    public Product updateProduct(UUID id, Product product) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("There is no product with id: " + id);
        }
        product.setId(id);
        return repository.saveAndFlush(product);
    }
    @Transactional
    public void deleteProduct(UUID id) {
        if (repository.existsById(id)) {
            throw new EntityNotFoundException("There is no product with id: " + id);
        }
        repository.deleteById(id);
    }
}
