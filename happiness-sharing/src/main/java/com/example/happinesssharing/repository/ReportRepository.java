package com.example.happinesssharing.repository;

import com.example.happinesssharing.entity.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends BaseRepository<Report,Integer>{
    @Query("from Report r where r.state='UNSETTLED'")
    List<Report> findByState();
}
