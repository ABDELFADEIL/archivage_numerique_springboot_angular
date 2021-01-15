package com.archive.dao;

import com.archive.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archive.entity.UserEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

}
