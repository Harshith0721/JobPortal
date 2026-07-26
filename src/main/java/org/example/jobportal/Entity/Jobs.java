package org.example.jobportal.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int jobId;
    private String title;
    private String company;
    private String skills;
    private String jobType;
    private String status;
    private long salary;
    @OneToMany(mappedBy = "job")
    private List<Applications> Applications;
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
