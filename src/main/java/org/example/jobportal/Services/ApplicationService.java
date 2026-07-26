package org.example.jobportal.Services;

import jakarta.mail.MessagingException;
import org.apache.coyote.Response;
import org.example.jobportal.EmailServices.EmailService;
import org.example.jobportal.Entity.Applications;
import org.example.jobportal.Entity.Jobs;
import org.example.jobportal.Entity.User;
import org.example.jobportal.Repositories.ApplicationRepository;
import org.example.jobportal.Repositories.JobsRepository;
import org.example.jobportal.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final UserRepository ur;
    private final ApplicationRepository ars;
    private final JobsRepository jrs;
    private final EmailService ers;

    public ApplicationService(ApplicationRepository ars, UserRepository ur, JobsRepository jrs, EmailService ers) {
        this.ars = ars;
        this.ur = ur;
        this.jrs = jrs;
        this.ers = ers;
    }
    public List<Applications> getApplicationss(){
        return ars.getApps();
    }
    public List<Applications> getUserApplications(String email){
        return ars.findApplicationsByUserEmail(email);
    }
    public String apply(int jobId, String email) throws MessagingException {
        Jobs j=jrs.findById(jobId).orElseThrow();
        User u= ur.findByEmail(email);
        if(ars.existsApplicationsByUserAndJob(u,j)){
            return "User Application Already Exists";
        }
        Applications a= new Applications();
        if("ACTIVE".equals(j.getStatus())) {
            a.setJob(j);
            a.setUser(u);
            a.setStatus("Applied");
            ers.sendApplicationReceived(email);
            ars.save(a);
            return "Application Sent SuccessFully";
        }
        return "Applications Closed";
    }
    public ResponseEntity<?> shortlist(int id) throws MessagingException{
            int rows= ars.shortlist(id);
            User u=ur.findById(ars.email(id)).orElseThrow();
            String email=u.getEmail();
        if(rows>0){
            ers.sendShortlisted(email);
            return ResponseEntity.ok("Updated Successfully");
        }
        else{
            throw new RuntimeException("Application not found");
        }
    }
}
