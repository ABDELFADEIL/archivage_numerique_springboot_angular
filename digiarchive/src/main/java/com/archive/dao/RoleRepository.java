package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.archive.entity.RoleEntity;
import com.archive.entity.UserEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{

    @Query("select role from RoleEntity role where role.name=:rolename")
    public RoleEntity findByName(@Param("rolename") String rolename);
}
