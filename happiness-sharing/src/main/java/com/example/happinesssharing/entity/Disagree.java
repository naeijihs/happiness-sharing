package com.example.happinesssharing.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Disagree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Share share;
    @ManyToOne
    private Sharer sharer;
}

