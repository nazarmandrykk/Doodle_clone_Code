package com.example.doodle_clone.models.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String emailId;
    private String password;

    @Column(name="first_name")
    private String firstName;
    private String lastName;

    private boolean isEnabled;
}
