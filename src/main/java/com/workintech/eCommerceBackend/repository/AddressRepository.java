package com.workintech.eCommerceBackend.repository;

import com.workintech.eCommerceBackend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
