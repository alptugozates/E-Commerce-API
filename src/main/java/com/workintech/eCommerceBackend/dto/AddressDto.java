package com.workintech.eCommerceBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    private String fullName;
    private String phone;
    private String city;
    private String district;
}
