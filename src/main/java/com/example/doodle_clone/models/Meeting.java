package com.example.doodle_clone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "meeting")
    private List<Slot> slots;


    public List<Slot> slots() {
        return slots;
    }

    public Meeting setSlots(List<Slot> slots) {
        this.slots = slots;
        return this;
    }

    public Meeting(){

    }

    private String title,text,location;
    private int count,IDadmin,idUsers;

    //time ????



    public Meeting( String title, String text, String location, int count) {
        this.title = title;
        this.text = text;
        this.location = location;
        this.count = count;
    }


    public Long id() {
        return id;
    }

    public Meeting setId(Long id) {
        this.id = id;
        return this;
    }

    public String title() {
        return title;
    }

    public Meeting setTitle(String title) {
        this.title = title;
        return this;
    }

    public String text() {
        return text;
    }

    public Meeting setText(String text) {
        this.text = text;
        return this;
    }

    public String location() {
        return location;
    }

    public Meeting setLocation(String location) {
        this.location = location;
        return this;
    }

    public int count() {
        return count;
    }

    public Meeting setCount(int count) {
        this.count = count;
        return this;
    }

    public int IDadmin() {
        return IDadmin;
    }

    public Meeting setIDadmin(int IDadmin) {
        this.IDadmin = IDadmin;
        return this;
    }

    public int idUsers() {
        return idUsers;
    }

    public Meeting setIdUsers(int idUsers) {
        this.idUsers = idUsers;
        return this;
    }

    //inner










}