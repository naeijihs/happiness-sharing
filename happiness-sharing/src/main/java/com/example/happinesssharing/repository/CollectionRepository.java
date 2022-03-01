package com.example.happinesssharing.repository;

import com.example.happinesssharing.entity.Collection;
import com.example.happinesssharing.entity.Sharer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends BaseRepository<Collection,Integer>{
    @Query("from Collection c where c.collector=:collector")
    List<Collection> findByCollector(@Param("collector") Sharer collector);
}
