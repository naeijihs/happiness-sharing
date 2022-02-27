package com.example.happinesssharing.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Share share;
    @ManyToOne
    private Sharer commenter;
    private String content;
    @Column(columnDefinition = "timestamp default current_timestamp",insertable = false,updatable = false)
    private LocalDateTime time;
}
