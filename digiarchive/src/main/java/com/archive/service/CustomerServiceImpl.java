package com.archive.service;


import com.archive.dao.CustomerRepository;
import com.archive.entity.CustomerEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerEntity findById(Integer customerId) {
        return customerRepository.findById(customerId).get();
    }
}
