package com.workintech.eCommerceBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
     String email;
     String password;
     String fullName;
}
