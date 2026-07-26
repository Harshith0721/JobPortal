package org.example.jobportal.Services;
import org.example.jobportal.Entity.Jobs;
import org.example.jobportal.Repositories.JobsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsService {
    private final JobsRepository jr;
    private final Logger l= LoggerFactory.getLogger(JobsService.class);
    public JobsService(JobsRepository jr) {
        this.jr = jr;
    }
    public List<Jobs> getJobs(){
        return jr.jobs();
    }
    public void addJobs(Jobs j){
        l.info("Got Request to Insert Job");
        jr.save(j);
        l.info("Job Created SucessFully");
    }
    public String statusChange(int id){
        int j=jr.change(id);
        if(j>0){
            return "Applications Closed";
        }
        throw new RuntimeException("Jobs with given id is not found");
    }
}
