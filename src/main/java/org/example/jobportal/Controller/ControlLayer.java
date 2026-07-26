package org.example.jobportal.Controller;

import jakarta.mail.MessagingException;
import org.example.jobportal.Entity.Applications;
import org.example.jobportal.Entity.Jobs;
import org.example.jobportal.Entity.User;
import org.example.jobportal.Jwt;
import org.example.jobportal.Services.ApplicationService;
import org.example.jobportal.Services.JobsService;
import org.example.jobportal.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControlLayer {
    private final UserService us;
    private final JobsService js;
    private final ApplicationService as;
    private final Jwt jwt;
    public ControlLayer(ApplicationService as, UserService us, JobsService js,Jwt jwt) {
        this.as = as;
        this.us = us;
        this.js = js;
        this.jwt=jwt;
    }
    @PostMapping("/login")
    public String login(Authentication authentication) {
        return jwt.generateToken(authentication.getName());
    }
    @PostMapping("/addUser")
    public String addUser(@RequestBody User u){
        return us.addUser(u);
    }
    @GetMapping("/getJobs")
    public List<Jobs> jobs(){
        return js.getJobs();
    }
    @PostMapping("/apply/{id}")
    public String apply(@PathVariable int id, Authentication a) throws MessagingException {
        String email=a.getName();
        return as.apply(id,email);
    }
    @PostMapping("/addJobs")
    public String addJobs(@RequestBody Jobs j){
        js.addJobs(j);
        return "Jobs Posted";
    }
    @GetMapping("/getApplications")
    public List<Applications> getApplications(){
        return as.getApplicationss();
    }
    @GetMapping("/myApplications")
    public List<Applications> getMyApplications(Authentication a){
        String email=a.getName();
        return as.getUserApplications(email);
    }
    @PutMapping("/shortlist/{id}")
    public ResponseEntity<?> shortlisting(@PathVariable int id) throws MessagingException {
         return as.shortlist(id);
    }
    @PutMapping("/closeApplications")
    public String close(@RequestBody int id){
        return js.statusChange(id);
    }
}
