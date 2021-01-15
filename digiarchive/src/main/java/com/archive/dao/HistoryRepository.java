package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archive.entity.HistoryEntity;
import com.archive.entity.UserEntity;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer>{

}
