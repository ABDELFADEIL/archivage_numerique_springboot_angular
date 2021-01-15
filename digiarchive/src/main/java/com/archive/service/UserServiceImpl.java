package com.archive.service;

import com.archive.dao.UserRepository;
import com.archive.dto.in.UserDtoIn;
import com.archive.entity.RoleEntity;
import com.archive.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final transient UserRepository userRepository;
    private final transient IRoleService roleService;

    public UserServiceImpl(UserRepository userRepository, IRoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserEntity CreateUser(UserDtoIn userDtoIn) {

        if((findUserByEmail(userDtoIn.getEmail()) != null)) throw new RuntimeException(userDtoIn.getEmail()+ " existe déjà !");
        if((findUserByUID(userDtoIn.getUID()) != null)) throw new RuntimeException(userDtoIn.getUID()+ " existe déjà !");
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity r = null;
         r =roleService.findByName(userDtoIn.getRolename());
        if (r==null){
            r = roleService.saveRole(new RoleEntity(userDtoIn.getRolename()));
        }
        //System.out.println(r);
        roles.add(r);
        String newPasswprd = bCryptPasswordEncoder.encode(userDtoIn.getPassword());
        UserEntity userEntity = new UserEntity(null, userDtoIn.getLastName(), userDtoIn.getFirstName(), userDtoIn.getUID(), newPasswprd, userDtoIn.getEmail(), roles);
        try {
            userEntity = userRepository.save(userEntity);
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("error creating user " + e);
        }
        return userEntity;
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        String password = user.getPassword();
        String oldPassword = userRepository.findByEmail(user.getEmail()).getPassword();
        if (!password.equals(oldPassword)){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findUserByUID(String UID) {
        return userRepository.findByUID(UID);
    }

    @Override
    public UserEntity findByUserEmailOrUID(String emailOrUID) {
        return userRepository.searchUserByEmailOrUID(emailOrUID);
    }


    @Override
    public UserEntity getAuthentificatedUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String email = ((UserDetails)principal).getUsername();
                return userRepository.findByEmail(email);
            } else {
                String email = principal.toString();
                return userRepository.findByEmail(email);

            }
        }catch (Exception e){

        }
        return null;
    }

}
