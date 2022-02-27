package com.example.happinesssharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"agrees","disagrees","comments"})
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Sharer sharer;
    @OneToMany(mappedBy = "share",cascade = {CascadeType.REMOVE})
    private List<Agree> agrees;
    @OneToMany(mappedBy = "share",cascade = {CascadeType.REMOVE})
    private List<Disagree> disagrees;
    @OneToMany(mappedBy = "share",cascade = {CascadeType.REMOVE})
    private List<Comment> comments;
    private String title;
    private String content;
    private String category;
    @Column(columnDefinition = "timestamp default current_timestamp",insertable = false,updatable = false)
    private LocalDateTime time;
}
