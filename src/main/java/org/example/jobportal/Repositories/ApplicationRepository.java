package org.example.jobportal.Repositories;

import jakarta.transaction.Transactional;
import org.example.jobportal.Entity.Applications;
import org.example.jobportal.Entity.Jobs;
import org.example.jobportal.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Applications,Integer> {
    boolean existsApplicationsByUserAndJob(User user, Jobs j);
    @Query(value = "SELECT * FROM Applications ",nativeQuery = true)
    List<Applications> getApps();
    List<Applications> findApplicationsByUserEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "update applications set status='Shortlisted' where application_id=:id",nativeQuery = true)
    public int shortlist(@Param(value="id") int id);
    @Query(value = "select user_id from applications where job_id=:id",nativeQuery = true)
    public int email(@Param("id")int id);
}
