package com.workintech.eCommerceBackend.service;

import com.workintech.eCommerceBackend.dto.CategoryDto;
import com.workintech.eCommerceBackend.entity.Category;
import com.workintech.eCommerceBackend.exception.CategoryException;
import com.workintech.eCommerceBackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        try {
            Category category = new Category();
            category.setTitle(categoryDto.getTitle());
            category.setImage(categoryDto.getImage());
            category.setGender(categoryDto.getGender());

            categoryRepository.save(category);

            return categoryDto;
        } catch (Exception e) {
            throw new CategoryException("Error occurred while creating category.", HttpStatus.BAD_REQUEST);
        }
    }

    public List<CategoryDto> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream()
                    .map(category -> new CategoryDto(
                            category.getTitle(),
                            category.getImage(),
                            category.getGender()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CategoryException("Error occurred while fetching categories.", HttpStatus.BAD_REQUEST);
        }
    }
    public CategoryDto getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return new CategoryDto(
                    category.getTitle(),
                    category.getImage(),
                    category.getGender());
        } else {
            throw new CategoryException("Error occurred while fetching category by ID.", HttpStatus.BAD_REQUEST);
        }
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setTitle(categoryDto.getTitle());
            category.setImage(categoryDto.getImage());
            category.setGender(categoryDto.getGender());

            categoryRepository.save(category);

            return categoryDto;
        } else {
            throw new CategoryException("Category not found with given ID: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public CategoryDto deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
            Category category = optionalCategory.get();
            return new CategoryDto(
                    category.getTitle(),
                    category.getImage(),
                    category.getGender());
        } else {
            throw new CategoryException("Category can not be delete. Given ID is not exist: " + id, HttpStatus.BAD_REQUEST);
        }
    }

}
