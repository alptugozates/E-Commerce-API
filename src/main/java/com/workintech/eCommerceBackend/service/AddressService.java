package com.workintech.eCommerceBackend.service;

import com.workintech.eCommerceBackend.dto.AddressDto;
import com.workintech.eCommerceBackend.entity.Address;
import com.workintech.eCommerceBackend.exception.AddressException;
import com.workintech.eCommerceBackend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AddressDto getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new AddressException("Address not found with given ID: " + id, HttpStatus.NOT_FOUND));
    }

    public AddressDto createAddress(Address address) {
        if (address != null && isAddressDataValid(address)) {
            Address savedAddress = addressRepository.save(address);
            return convertToDto(savedAddress);
        } else {
            throw new AddressException("Address data is not valid or empty", HttpStatus.BAD_REQUEST);
        }
    }

    public AddressDto updateAddress(Long id, Address address) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address existingAddress = addressOptional.get();
            existingAddress.setAddressTitle(address.getAddressTitle());
            existingAddress.setFullName(address.getFullName());
            existingAddress.setPhone(address.getPhone());
            existingAddress.setCity(address.getCity());
            existingAddress.setDistrict(address.getDistrict());
            existingAddress.setNeighborhood(address.getNeighborhood());
            existingAddress.setAddressDetails(address.getAddressDetails());
            existingAddress.setUser(address.getUser());
            Address updatedAddress = addressRepository.save(existingAddress);
            return convertToDto(updatedAddress);
        } else {
            throw new AddressException("Address not found with given ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    public AddressDto deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressException("Address not found with given ID: " + id, HttpStatus.NOT_FOUND));
        addressRepository.deleteById(id);
        return convertToDto(address);
    }

    private AddressDto convertToDto(Address address) {
        return new AddressDto(
                address.getFullName(),
                address.getPhone(),
                address.getCity(),
                address.getDistrict()
        );
    }
    private boolean isAddressDataValid(Address address) {
        return address.getFullName() != null && !address.getFullName().isEmpty() &&
                address.getPhone() != null && !address.getPhone().isEmpty() &&
                address.getCity() != null && !address.getCity().isEmpty() &&
                address.getDistrict() != null && !address.getDistrict().isEmpty() &&
                address.getNeighborhood() != null && !address.getNeighborhood().isEmpty();
    }
    }
