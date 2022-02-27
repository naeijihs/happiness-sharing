package com.example.happinesssharing.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Sharer receiver;
    @ManyToOne
    private Sharer sender;
}
