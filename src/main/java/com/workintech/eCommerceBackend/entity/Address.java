package com.workintech.eCommerceBackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address title cannot be blank")
    @Column(name = "address_title")
    private String addressTitle;

    @NotBlank(message = "Full name cannot be blank")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "District cannot be blank")
    private String district;

    @NotBlank(message = "Neighborhood cannot be blank")
    @Column(name = "neighborhood")
    private String neighborhood;

    @NotBlank(message = "Address details cannot be blank")
    @Column(name = "address_details")
    private String addressDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
