package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archive.entity.ContextEntity;
import com.archive.entity.UserEntity;

@Repository
public interface ContextRepository extends JpaRepository<ContextEntity, Integer>{

}
