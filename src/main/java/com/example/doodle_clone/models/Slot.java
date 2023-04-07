package com.example.doodle_clone.models;

import jakarta.persistence.*;

@Entity
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Meeting meeting() {
        return meeting;
    }
    public Slot(){

    }

    public Slot setMeeting(Meeting meeting) {
        this.meeting = meeting;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
    private Meeting meeting;


    private String name;
    private int costMember;



    private int idUsers;

    public Slot(Meeting meeting, String name) {
        this.costMember = costMember();
        this.name = name;
    }

    public int idUsers() {
        return idUsers;
    }

    public Slot setIdUsers(int idUsers) {
        this.idUsers = idUsers;
        return this;
    }

    public Slot(String name, int costMember) {
        this.name = name;
        this.costMember = costMember;
    }

    public String name() {
        return name;
    }

    public Slot setName(String name) {
        this.name = name;
        return this;
    }

    public int costMember() {
        return costMember;
    }

    public Slot setCostMember(int costMember) {
        this.costMember = costMember;
        return this;
    }
}




