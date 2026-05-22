package com.zstyle.backend.service;

import com.zstyle.backend.entity.Product_metadata;
import com.zstyle.backend.repository.ProductMetadataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMetadataService {

    private final ProductMetadataRepository repo;

    public ProductMetadataService(ProductMetadataRepository repo) {
        this.repo = repo;
    }

    public Product_metadata save(Product_metadata data) {
        return repo.save(data);

    }

    public List<Product_metadata> getAll() {
        return repo.findAll();
    }

    public Optional<Product_metadata> getById(int id) {
        return repo.findById(id);
    }

    public Product_metadata update(int id, Product_metadata newData) {
        return repo.findById(id).map(d -> {
            d.setColor(newData.getColor());
            d.setMaterial(newData.getMaterial());
            d.setSize(newData.getSize());
            d.setGender(newData.getGender());
            d.setOccasion(newData.getOccasion());
            d.setSeason(newData.getSeason());
            d.setStyle(newData.getStyle());
            d.setFit(newData.getFit());
            return repo.save(d);
        }).orElseThrow(() -> new RuntimeException("Metadata not found"));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
