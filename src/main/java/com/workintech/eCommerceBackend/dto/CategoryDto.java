package com.workintech.eCommerceBackend.dto;

import com.workintech.eCommerceBackend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    private String title;
    private String image;
    private Gender gender;
}
