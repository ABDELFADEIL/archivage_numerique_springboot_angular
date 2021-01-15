package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.archive.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

    @Query("select u from UserEntity u where u.email=:email or u.UID=:email")
    public UserEntity searchUserByEmailOrUID(@Param("email") String s);
    public UserEntity findByEmail(String emai);
    public UserEntity findByUID(String UID);
}
