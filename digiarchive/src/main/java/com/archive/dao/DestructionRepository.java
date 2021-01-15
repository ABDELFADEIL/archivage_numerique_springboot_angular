package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archive.entity.DestructionListEntity;
import com.archive.entity.UserEntity;

@Repository
public interface DestructionRepository extends JpaRepository<DestructionListEntity, Integer>{

}
