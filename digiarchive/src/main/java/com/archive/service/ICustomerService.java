package com.archive.service;

import com.archive.dto.CustomerDto;
import com.archive.entity.CustomerEntity;

import java.util.Set;

public interface ICustomerService {

    CustomerEntity findById(Integer customerId);
    CustomerEntity add(CustomerDto customerDto);
    CustomerEntity update(CustomerDto customerDto);
    void delete(Integer customerId);
    CustomerEntity findOnByClientNumber(String clientNumber);
     Set<CustomerEntity> getClientsByNameContains(String name);
     Set<CustomerEntity> getClientByClientNumberContains(String clientNumber);
     String createNewClientNumber();
     Set<CustomerEntity> findByCustomerNameOrCustomerNumberContains(String customer_name, String customer_number);

}
