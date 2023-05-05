package com.example.doodle_clone.models;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")

    private Meeting meeting;



    private String name;

    private int costMember;


    private int userID;


    public Slot(Meeting meeting, String name) {
        this.meeting = meeting;
        this.name = name;
    }

    public Slot(String name, int costMember) {
        this.name = name;
        this.costMember = costMember;
    }


}




