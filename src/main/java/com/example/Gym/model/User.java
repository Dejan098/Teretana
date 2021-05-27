package com.example.Gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.DiscriminatorType.STRING;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rolee", discriminatorType=STRING)
@Table(name="users")

public class User{

    @Id
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String namee;

    @Column
    private String surname;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private Date birthDate;

    @Column
    private String rola;

    @Column
    private Boolean active;

    @Transient
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public User() {
    }

    public User(Integer id, String username, String password, String namee, String surname, String phoneNumber, String email, Date birthDate, String rola, Boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.namee = namee;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.rola = rola;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
