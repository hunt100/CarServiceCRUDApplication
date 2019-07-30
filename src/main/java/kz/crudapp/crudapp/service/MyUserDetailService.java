package kz.crudapp.crudapp.service;


import kz.crudapp.crudapp.entity.MyUser;
import kz.crudapp.crudapp.entity.Role;
import kz.crudapp.crudapp.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;

@Service
public class MyUserDetailService {
    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void regUser(MyUser myUser) {
        MyUser userFromDb = myUserRepository.findByUsername(myUser.getUsername());

        if (userFromDb != null) {
            System.err.println("user already exist");
            return;
        }

        myUser.setActive(true);
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        myUser.setRoles(Collections.singleton(Role.USER));
        myUserRepository.save(myUser);
    }


}


