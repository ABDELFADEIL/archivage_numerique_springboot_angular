package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archive.entity.CustomerEntity;
import com.archive.entity.UserEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{

}
