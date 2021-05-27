package com.example.Gym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("fitnesscenter")
public class FitnessCenter {

    @Id
    @SequenceGenerator(name="seq_fitnesscenter", sequenceName = "seq_fitnesscenter", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_fitnesscenter")
    private Integer id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "fitnescentar", fetch = FetchType.EAGER)
    private Set<Trainer> trainers = new HashSet<Trainer>();

    @JsonIgnore
    @OneToMany(mappedBy = "fitnesraspored", fetch = FetchType.EAGER)
    private Set<Schedule> schedulers = new HashSet<Schedule>();

    @JsonIgnore
    @OneToMany(mappedBy = "fitnessala", fetch = FetchType.EAGER)
    private Set<Hall> halls = new HashSet<Hall>();

    public FitnessCenter(Integer id, String name, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public FitnessCenter() {

    }

    public Set<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Set<Trainer> trainers) {
        this.trainers = trainers;
    }

    public Set<Schedule> getSchedulers() {
        return schedulers;
    }

    public void setSchedulers(Set<Schedule> schedulers) {
        this.schedulers = schedulers;
    }

    public Set<Hall> getHalls() {
        return halls;
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
