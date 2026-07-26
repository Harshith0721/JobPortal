package org.example.jobportal.Repositories;

import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import org.example.jobportal.Entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobsRepository extends JpaRepository<Jobs,Integer> {
    @Query(value = "SELECT * FROM JOBS",nativeQuery = true)
    List<Jobs> jobs();
    @Transactional
    @Modifying
    @Query(value = "Update jobs set status='CLOSED' WHERE job_id=:id",nativeQuery = true)
    int change(@Param("id") int id);
}

