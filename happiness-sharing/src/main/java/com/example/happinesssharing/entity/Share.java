package com.example.happinesssharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"agrees","reports","collections"})
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Sharer sharer;
    @OneToMany(mappedBy = "share",cascade = {CascadeType.ALL})
    private List<Agree> agrees;
    @OneToMany(mappedBy = "share",cascade = {CascadeType.ALL})
    @JsonIgnoreProperties({"share"})
    private List<Comment> comments;
    @OneToMany(mappedBy = "share",cascade = {CascadeType.ALL})
    private List<Report> reports;
    @OneToMany(mappedBy = "share",cascade = {CascadeType.ALL})
    private List<Collection> collections;
    private String title;
    private String text;
    private String category;
    private int agreeCount;
    private String picture;
    @Column(columnDefinition = "timestamp default current_timestamp",insertable = false,updatable = false)
    private LocalDateTime time;
}
