package org.example.jobportal.Services;

import jakarta.validation.Valid;
import org.example.jobportal.Entity.User;
import org.example.jobportal.Repositories.ApplicationRepository;
import org.example.jobportal.Repositories.JobsRepository;
import org.example.jobportal.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository ur;
    private final JobsRepository jr;
    private final ApplicationRepository ar;
    private final Logger l= LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;

    public UserService(ApplicationRepository ar, UserRepository ur, JobsRepository jr, PasswordEncoder passwordEncoder) {
        this.ar = ar;
        this.ur = ur;
        this.jr = jr;
        this.passwordEncoder = passwordEncoder;
    }
    public String addUser(@Valid  User u){
        if(ur.existsByEmail(u.getEmail())){
            return "User Already Exixts with emailId Try USing Another Eamil"+u.getEmail();
        }
        l.info("Got request to add user..."+u.getName());
        u.setRole("CANDIDATE");
        System.out.println("Before: " + u.getPassword());
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        System.out.println("After encoding: " + u.getPassword());
        ur.save(u);
        l.info("User saved successfully with name"+u.getName());
        return "Account Creation Successfull";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u=ur.findByEmail(email);
        if(u==null){
            throw new UsernameNotFoundException("User with entered email is not found");
        }
        return org.springframework.security.core.userdetails.User.withUsername(u.getEmail()).password(u.getPassword())
                .roles(u.getRole())
                .build();
    }
}
