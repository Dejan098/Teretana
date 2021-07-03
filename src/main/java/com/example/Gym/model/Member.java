package com.example.Gym.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("member")
public class Member extends User {


    public Member() {
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private Set<Schedule> schedule;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private Set<Ocena> ocena=new HashSet<Ocena>();

    public Set<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Member(Integer id, String korisnickoime, String password, String namee, String surname, String phoneNumber, String email, Date birthDate, String rola, Boolean active, Set<Schedule> schedule) {
        super(id, korisnickoime, password, namee, surname, phoneNumber, email, birthDate, rola, active);
        this.schedule = schedule;
    }
}
