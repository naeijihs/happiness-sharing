package com.example.happinesssharing.repository;

import com.example.happinesssharing.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Integer>{
    @Query("from User u where u.username=:username")
    User getUserByUsername(@Param("username") String username);
}
