package com.crm.low_crm.repository;

import com.crm.low_crm.model.enity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findTopByAddressFrom1C(String address);
}
