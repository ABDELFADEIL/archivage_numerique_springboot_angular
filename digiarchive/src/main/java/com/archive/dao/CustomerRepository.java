package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.archive.entity.CustomerEntity;
import com.archive.entity.UserEntity;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{

    @Query("select client from CustomerEntity client where client.client_number=:client_number")
    public CustomerEntity findByClientNumber(@Param("client_number") String client_number);

    @Query("select client from CustomerEntity client where client.client_number like %:client_number%")
    public Set<CustomerEntity> findByClientNumberContains(@Param("client_number") String client_number);
    @Query("select client from CustomerEntity client where client.client_name like %:client_name% or client.client_first_name like %:client_name%")
    public Set<CustomerEntity> findByClientNameContains( @Param("client_name") String client_name);

    @Query("select customer from CustomerEntity customer where  customer.client_number like %:client_number% or (customer.client_name like %:client_name% or customer.client_first_name like %:client_name%)")
    public Set<CustomerEntity> findByClientNameOrClientNumberContains( @Param("client_name") String client_name, @Param("client_number") String client_number);

    @Query(value="select MAX(c.client_number) from customer c group by c.client_number order by c.client_number desc limit 1", nativeQuery=true)
    String getMaxClientNumber();
}
