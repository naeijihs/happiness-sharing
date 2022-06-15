package com.example.happinesssharing.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Sharer communicator;
    private String content;
    private String time;
}
