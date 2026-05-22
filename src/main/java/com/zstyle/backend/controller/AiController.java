package com.zstyle.backend.controller;

import com.zstyle.backend.entity.Product;
import com.zstyle.backend.repository.ProductRepository;
import com.zstyle.backend.service.AiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "*")

public class AiController {

    @Autowired
    private AiService aiService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/suggest")
    public String suggestOutfit(@RequestBody Map<String, Object> data) {


        List<Integer> ids = (List<Integer>) data.get("productIds");

        String prompt = (String) data.get("prompt");

        List<String> metaList = new ArrayList<>();

        for (Integer id : ids) {

            Product product = productRepository
                    .findById(id)
                    .orElse(null);


            if (product != null) {

                String meta = """
                        Product: %s
                        Category: %s
                        Price: %s
                        """
                        .formatted(
                                product.getName(),
                                product.getCategory(),
                                product.getPrice()
                        );

                metaList.add(meta);
            }
        }


        return aiService.generateOutfitSuggestion(
                prompt,
                metaList
        );
    }
}