package com.clinic.repository.dictionary;

import com.clinic.entity.dictionary.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
