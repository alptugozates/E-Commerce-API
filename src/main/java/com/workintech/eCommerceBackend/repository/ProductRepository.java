package com.workintech.eCommerceBackend.repository;

import com.workintech.eCommerceBackend.entity.Category;
import com.workintech.eCommerceBackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
