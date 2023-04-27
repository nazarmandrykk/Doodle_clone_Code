package com.example.doodle_clone.models;


import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.NonNull;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @OneToMany(mappedBy = "meeting")
    private List<Slot> slots;

    @NonNull private String title;
    @NonNull private String text;
    @NonNull private String location;
    @NonNull private int count;
    private int IDadmin;
    private int idUsers;

    //time ????





}
