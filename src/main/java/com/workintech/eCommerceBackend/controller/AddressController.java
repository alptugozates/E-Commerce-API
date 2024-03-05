package com.workintech.eCommerceBackend.controller;

import com.workintech.eCommerceBackend.dto.AddressDto;
import com.workintech.eCommerceBackend.entity.Address;
import com.workintech.eCommerceBackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);

    }
//@GetMapping("/{id}")
//public Address getAddressById(@PathVariable Long id) {
//    return addressService.getAddressById(id);
//}

    @PostMapping
    public AddressDto createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @PutMapping("/{id}")
    public AddressDto updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public AddressDto deleteAddress(@PathVariable Long id) {
        return addressService.deleteAddress(id);
    }
}
