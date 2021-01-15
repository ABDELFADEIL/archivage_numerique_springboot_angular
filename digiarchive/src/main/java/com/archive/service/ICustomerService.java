package com.archive.service;

import com.archive.entity.CustomerEntity;

public interface ICustomerService {

    CustomerEntity findById(Integer customerId);
}
