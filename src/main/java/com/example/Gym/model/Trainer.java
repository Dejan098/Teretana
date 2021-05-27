package com.example.Gym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("trainer")
public class Trainer extends User {


    @Column
    private Float ocena;

    @JsonIgnore
    @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
    private Set<Training> trainings = new HashSet<Training>();

    @ManyToOne(fetch = FetchType.EAGER)
    private FitnessCenter fitnescentar;

    public Trainer() {
    }
}
