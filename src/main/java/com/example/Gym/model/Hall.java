package com.example.Gym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("hall")
public class Hall {
    @Id
    @SequenceGenerator(name="seq_hall", sequenceName = "seq_hall", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hall")
    private Integer id;

    @Column
    private Integer capacity;

    @Column
    private String label;

    @ManyToOne(fetch = FetchType.EAGER)
    private FitnessCenter fitnessala;

    @JsonIgnore
    @ManyToMany(mappedBy = "halls", fetch = FetchType.EAGER)
    private Set<Schedule> scheduleset = new HashSet<Schedule>();


    public Hall(Integer id, Integer capacity, String label) {
        this.id = id;
        this.capacity = capacity;
        this.label = label;
    }

    public Hall() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
