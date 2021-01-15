package com.archive.service;

import com.archive.dao.RoleRepository;
import com.archive.entity.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService{

    private final transient RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;

    }

    @Override
    public RoleEntity findByName(String rolename) {
        return roleRepository.findByName(rolename);
    }
    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return roleRepository.save(role);
    }


}
