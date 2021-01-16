package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.archive.entity.ContractEntity;
import com.archive.entity.UserEntity;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Integer>{

    @Query(value="select MAX(c.contract_number) from contract c group by c.contract_number order by c.contract_number desc limit 1", nativeQuery=true)
    public String findMaxContractNumber();

    @Query("select contract from ContractEntity contract where contract.contract_number=:contract_number")
    ContractEntity findByContract_number(@Param("contract_number") String contract_number);

    @Query("select contract from ContractEntity contract where contract.contract_number like %:contract_number%")
    Set<ContractEntity> findByContract_numberContains(@Param("contract_number") String contract_number);

    @Query("select contract from ContractEntity contract where contract.customer.client_name like %:client_name% or contract.customer.client_first_name like %:client_name%")
    Set<ContractEntity> getContractsByClientNameContains(@Param("client_name") String client_name);

    //findAccountByStatusAndEventDateAfterAndEvenDateBefor
    @Query(value="select * from  contract where contract.event in (select id_event from  event where event_type=:status and event_date between :dateAfter and :dateBefor)", nativeQuery=true)
    Set<ContractEntity> findContractsByEventStatusAndEventDateAfterAndDateBefor(@Param("status") String status, @Param("dateAfter") LocalDate dateAfter, @Param("dateBefor") LocalDate dateBefor);

     @Query("select contract from ContractEntity contract where contract.customer.id like %:customer_id%")
     ContractEntity findByCustomer(@Param("customer_id")String customer_id);

    @Query("select contract from ContractEntity contract where  contract.contract_number like %:contract_number% or (contract.customer.client_name like %:client_name% or contract.customer.client_first_name like %:client_name%)")
    Set<ContractEntity> getContractsByClientNameAndContractNumberContains(@Param("client_name") String client_name, @Param("contract_number") String contract_number);


}
