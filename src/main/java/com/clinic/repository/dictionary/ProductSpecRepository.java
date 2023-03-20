package com.clinic.repository.dictionary;

import com.clinic.entity.dictionary.ProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecRepository extends JpaRepository<ProductSpec, String> {
}
