package com.zstyle.backend.service;
import com.zstyle.backend.entity.Product;
import com.zstyle.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product save(Product product) {
        return repo.save(product);
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Optional<Product> getById(int id) {
        return repo.findById(id);
    }

    public Product update(int id, Product newProduct) {
        return repo.findById(id).map(p -> {
            p.setName(newProduct.getName());
            p.setPrice(newProduct.getPrice());
            p.setCategory(newProduct.getCategory());
            p.setImage(newProduct.getImage());
            return repo.save(p);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
