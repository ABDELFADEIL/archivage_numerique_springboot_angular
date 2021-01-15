package com.archive.service;

import com.archive.entity.RoleEntity;

public interface IRoleService {


    public RoleEntity findByName(String rolename);


    RoleEntity saveRole(RoleEntity role);
}
