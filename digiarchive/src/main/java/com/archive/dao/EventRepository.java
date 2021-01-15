package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archive.entity.DigitalDocumentEntity;
import com.archive.entity.EventEntity;
import com.archive.entity.UserEntity;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer>{

}
