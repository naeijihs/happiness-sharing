package com.example.happinesssharing.repository;

import com.example.happinesssharing.entity.Share;
import com.example.happinesssharing.entity.Sharer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShareRepository extends BaseRepository<Share,Integer>{
    @Query("from Share s where s.sharer=:sharer")
    List<Share> getOwnShares(@Param("sharer") Sharer sharer);
}
