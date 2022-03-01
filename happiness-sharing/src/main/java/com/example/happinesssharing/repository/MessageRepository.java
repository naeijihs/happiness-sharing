package com.example.happinesssharing.repository;

import com.example.happinesssharing.entity.Message;
import com.example.happinesssharing.entity.Sharer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends BaseRepository<Message,Integer>{
    @Query("from Message m where m.receiver=:receiver")
    List<Message> findByReceiver(@Param("receiver")Sharer receiver);
    @Query("from Message m where m.sender=:sender")
    List<Message> findBySender(@Param("sender")Sharer sender);

}
