package com.zstyle.backend.controller;

import com.zstyle.backend.entity.Product;
import com.zstyle.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;



    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("image") MultipartFile file
    ) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setImage(file.getBytes());


        return ResponseEntity.ok(service.save(product));
    }


    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id) {

        Product product = service.getById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(product.getImage());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id, @RequestParam("name") String name,
                                          @RequestParam("price") double price,
                                          @RequestParam("category") String category,
                                          @RequestParam("image") MultipartFile file) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setImage(file.getBytes());
        return ResponseEntity.ok(service.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Product deleted");
    }
}
