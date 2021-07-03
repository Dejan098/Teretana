package com.example.Gym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@DiscriminatorValue("ocena")
public class Ocena {

    @Id
    @SequenceGenerator(name = "seq_ocena", sequenceName = "seq_ocena", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ocena")
    private Integer id;

    @Column
    private Integer ocena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    @OneToOne(mappedBy = "ocena")
    private Training training;

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Ocena() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
