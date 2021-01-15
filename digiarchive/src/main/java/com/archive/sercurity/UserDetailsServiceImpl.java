package com.archive.sercurity;

import com.archive.dao.UserRepository;
import com.archive.entity.UserEntity;
import com.archive.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity u =null;
        System.out.println("email: "+email);
        //u=userRepository.findByEmailOrUID(email);
        u=userService.findByUserEmailOrUID(email);
        System.out.println("email: "+u.getEmail()+ " password: "+u.getPassword());
        if(u==null) throw new UsernameNotFoundException(email+ "est null .....");
        final UserEntity user = u;

        Collection<GrantedAuthority> authorities=new ArrayList<>();
        u.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        });
        System.out.println("email: "+user.getEmail()+ " password: "+user.getPassword());
        System.out.println("succès ....//////");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
