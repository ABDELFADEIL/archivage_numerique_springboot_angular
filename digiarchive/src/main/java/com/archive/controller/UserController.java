package com.archive.controller;

import com.archive.dto.in.UserDtoIn;
import com.archive.entity.UserEntity;
import com.archive.service.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("**")
public class UserController {


    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create-user")
    public UserEntity createUser(@RequestBody(required = true) UserDtoIn userDtoIn) {
        return userService.CreateUser(userDtoIn);
    }

    @PutMapping("/update-user")
    public UserEntity updateUser(@RequestBody UserEntity user) {
        return userService.updateUser(user);
    }


    @GetMapping("/user-info")
    public UserEntity getAuthentificatedUser(){
        return userService.getAuthentificatedUser();
    }


}
