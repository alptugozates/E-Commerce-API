package com.workintech.eCommerceBackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public class CardNotFoundException extends RuntimeException {
    private String message;
    private HttpStatus status;

}
