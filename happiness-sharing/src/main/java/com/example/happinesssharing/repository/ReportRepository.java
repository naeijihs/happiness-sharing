package com.example.happinesssharing.repository;

import com.example.happinesssharing.entity.Report;
import com.example.happinesssharing.entity.Sharer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends BaseRepository<Report,Integer>{
    @Query("from Report r where r.state='UNSETTLED'")
    List<Report> findByState();
    @Query("from Report r where r.reporter=:reporter")
    List<Report> findByReporter(@Param("reporter") Sharer reporter);
}
