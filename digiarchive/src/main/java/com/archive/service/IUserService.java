package com.archive.service;

import com.archive.dto.in.UserDtoIn;
import com.archive.entity.UserEntity;

public interface IUserService {

    UserEntity CreateUser(UserDtoIn user);
    UserEntity updateUser(UserEntity user);
    UserEntity findUserByEmail(String email);
    UserEntity findUserByUID(String UID);
    UserEntity findByUserEmailOrUID(String emailOrUID);
    UserEntity getAuthentificatedUser();
}
