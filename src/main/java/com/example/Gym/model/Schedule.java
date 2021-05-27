package com.example.Gym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("schedule")
public class Schedule {

    @Id
    @SequenceGenerator(name = "seq_schedule", sequenceName = "seq_schedule", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_schedule")
    private Integer id;

    @Column
    private Integer price;

    @Column
    private Date beginDate;

    @OneToOne
    @JoinColumn(name = "training_id", referencedColumnName = "id", nullable = false)
    private Training training;

    @ManyToOne(fetch = FetchType.EAGER)
    private FitnessCenter fitnesraspored;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hall_schedules", joinColumns = @JoinColumn(name = "hall_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"))
    private Set<Hall> halls = new HashSet<Hall>();


}
