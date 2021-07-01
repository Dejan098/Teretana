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
    private Integer slobodnih_mesta;

    @Column
    private Date beginDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Training training;

    @ManyToOne(fetch = FetchType.EAGER)
    private FitnessCenter fitnesraspored;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "schedule_members", joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"))
    private Set<Member> member;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hall_schedules", joinColumns = @JoinColumn(name = "hall_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "schedule_id", referencedColumnName = "id"))
    private Set<Hall> halls = new HashSet<Hall>();

    public Schedule(Integer id, Integer price, Date beginDate) {
        this.id = id;
        this.price = price;
        this.beginDate = beginDate;
    }

    public Schedule() {
    }

    public Schedule(Integer id, Integer price, Date beginDate, Training training, FitnessCenter fitnesraspored, Set<Hall> halls) {
        this.id = id;
        this.price = price;
        this.beginDate = beginDate;
        this.training = training;
        this.fitnesraspored = fitnesraspored;
        this.halls = halls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public FitnessCenter getFitnesraspored() {
        return fitnesraspored;
    }

    public void setFitnesraspored(FitnessCenter fitnesraspored) {
        this.fitnesraspored = fitnesraspored;
    }

    public Set<Hall> getHalls() {
        return halls;
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
    }

    public Integer getSlobodnih_mesta() {
        return slobodnih_mesta;
    }

    public void setSlobodnih_mesta(Integer slobodnih_mesta) {
        this.slobodnih_mesta = slobodnih_mesta;
    }

    public Set<Member> getMember() {
        return member;
    }

    public void setMember(Set<Member> member) {
        this.member = member;
    }
}
