package com.example.happinesssharing.repository;

import com.example.happinesssharing.entity.Message;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends BaseRepository<Message,Integer>{
}
