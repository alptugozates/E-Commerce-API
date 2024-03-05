package com.workintech.eCommerceBackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public class ProductException extends RuntimeException{
        private final String message;
        private final HttpStatus status;

}
