package com.workintech.eCommerceBackend.service;

import com.workintech.eCommerceBackend.dto.ProductDto;
import com.workintech.eCommerceBackend.entity.Product;
import com.workintech.eCommerceBackend.exception.ProductException;
import com.workintech.eCommerceBackend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public ProductDto createProduct(ProductDto productDto) {
        if (productDto.getName() == null || productDto.getName().isEmpty()) {
            throw new ProductException("Product name cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (productDto.getPrice() == null || productDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductException("Product price must be greater than zero.", HttpStatus.BAD_REQUEST);
        }
        if (productDto.getDescription() == null || productDto.getDescription().isEmpty()) {
            throw new ProductException("Product description cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        productRepository.save(product);
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> new ProductDto(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }


    public ProductDto getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            return new ProductDto(product.getName(), product.getDescription(), product.getPrice());
        } else {
            throw new ProductException("Product not found with given ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());

            productRepository.save(product);

            return productDto;
        } else {
            throw new ProductException("Product not found with given ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
@Transactional
    public Product deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.deleteById(id);
            return product;
        } else {
            throw new ProductException("Product not found with given ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
