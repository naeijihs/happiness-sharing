package com.example.happinesssharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"shares","agrees","comments","reports","collections","receives","sends"})
public class Sharer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = {CascadeType.ALL})
    @MapsId
    private User user;
    @OneToMany(mappedBy = "sharer",cascade = {CascadeType.ALL})
    private List<Share> shares;
    @OneToMany(mappedBy = "sharer",cascade = {CascadeType.ALL})
    private List<Agree> agrees;
    @OneToMany(mappedBy = "sharer",cascade = {CascadeType.ALL})
    private List<Comment> comments;
    @OneToMany(mappedBy = "sharer",cascade = {CascadeType.ALL})
    private List<Report> reports;
    @OneToMany(mappedBy = "sharer",cascade = {CascadeType.ALL})
    private List<Collection> collections;
    @OneToMany(mappedBy = "sharer",cascade = {CascadeType.ALL})
    private List<Message> receives;
    @OneToMany(mappedBy = "sharer",cascade = {CascadeType.ALL})
    private List<Message> sends;
    private String name;
    private String sex;
    private Integer age;
    private String province;
    private String contact;
}
