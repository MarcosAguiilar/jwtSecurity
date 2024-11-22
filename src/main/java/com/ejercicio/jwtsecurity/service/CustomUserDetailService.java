package com.ejercicio.jwtsecurity.service;

import com.ejercicio.jwtsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userservice;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userservice.findUserByName(username);
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),new ArrayList<>());
    }
}
