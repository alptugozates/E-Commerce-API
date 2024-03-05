package com.workintech.eCommerceBackend.repository;

import com.workintech.eCommerceBackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
