package com.example.doodle_clone.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @OneToMany(mappedBy = "meeting")


    private List<Slot> slots;

    private String title, text, location;
    private int count, IDadmin, idUsers;

    //time ????


    public Meeting(String title, String text, String location, int count) {
        this.title = title;
        this.text = text;
        this.location = location;
        this.count = count;
    }


}
