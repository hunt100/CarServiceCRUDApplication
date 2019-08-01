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
import java.util.UUID;

@Service
public class MyUserDetailService {
    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    public String regUser(MyUser myUser) {
        MyUser userFromDb = myUserRepository.findByUsername(myUser.getUsername());

        if (userFromDb != null) {
            System.err.println("user already exist");
            return "user with this login already exist";
        }

        myUser.setActive(false);
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        myUser.setRoles(Collections.singleton(Role.USER));
        myUser.setActivationCode(UUID.randomUUID().toString());

        myUserRepository.save(myUser);

        if (!StringUtils.isEmpty(myUser.getEmail())) {
            String message = String.format(
                    "Hello, %s \n" +
                            "Welcome To App. Please, visit next link: http://localhost:8080/activate/%s",
                    myUser.getUsername(), myUser.getActivationCode()
            );
            mailSender.send(myUser.getEmail(), "Activation code", message);
        }
        return null;
    }


    public boolean activateUser(String code) {
        MyUser myUser = myUserRepository.findByActivationCode(code);

        if (myUser == null) {
            return false;
        }

        myUser.setActivationCode(null);
        myUser.setActive(true);
        myUserRepository.save(myUser);
        return true;
    }
}


