package org.example.jobportal;

import org.example.jobportal.Entity.User;
import org.example.jobportal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JobPortalApplication {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository ur;
    public JobPortalApplication(PasswordEncoder passwordEncoder,UserRepository ur) {
        this.passwordEncoder = passwordEncoder;
        this.ur=ur;
    }

    public static void main(String[] args) {
        SpringApplication.run(JobPortalApplication.class, args);
    }


}
