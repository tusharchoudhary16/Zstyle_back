package com.zstyle.backend.controller;


import com.zstyle.backend.entity.Product_metadata;
import com.zstyle.backend.service.ProductMetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metadata")
public class ProductMetadataController {
    private final ProductMetadataService service;

    public ProductMetadataController(ProductMetadataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product_metadata> create(@RequestBody Product_metadata data) {
        return ResponseEntity.ok(service.save(data));
    }

    @GetMapping
    public ResponseEntity<List<Product_metadata>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product_metadata> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product_metadata> update(@PathVariable int id, @RequestBody Product_metadata data) {
        return ResponseEntity.ok(service.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Metadata deleted");
    }
}
