package com.example.Gym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
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

    public Float getOcena() {
        return ocena;
    }

    public void setOcena(Float ocena) {
        this.ocena = ocena;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }

    public FitnessCenter getFitnescentar() {
        return fitnescentar;
    }

    public void setFitnescentar(FitnessCenter fitnescentar) {
        this.fitnescentar = fitnescentar;
    }

    public Trainer(Float ocena, Set<Training> trainings, FitnessCenter fitnescentar) {
        this.ocena = ocena;
        this.trainings = trainings;
        this.fitnescentar = fitnescentar;
    }

    public Trainer(Integer id, String korisnickoime, String password, String namee, String surname, String phoneNumber, String email, Date birthDate, String rola, Boolean active, Float ocena, Set<Training> trainings, FitnessCenter fitnescentar) {
        super(id, korisnickoime, password, namee, surname, phoneNumber, email, birthDate, rola, active);
        this.ocena = ocena;
        this.trainings = trainings;
        this.fitnescentar = fitnescentar;
    }
}
