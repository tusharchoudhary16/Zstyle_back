package com.zstyle.backend.repository;

import com.zstyle.backend.entity.Product_metadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMetadataRepository extends JpaRepository<Product_metadata, Integer> {

}
