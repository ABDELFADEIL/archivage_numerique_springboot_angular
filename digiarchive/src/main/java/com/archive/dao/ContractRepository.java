package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archive.entity.ContractEntity;
import com.archive.entity.UserEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Integer>{

}
