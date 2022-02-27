package com.example.happinesssharing.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Sharer collector;
    @ManyToOne
    private Share share;
}
