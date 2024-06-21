package com.example.eksamensprojekt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-discipline")
    private Set<Discipline> disciplines;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-participant")
    private Set<Participant> participants;
}
