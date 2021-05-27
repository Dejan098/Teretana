package com.example.Gym.model.DTO;

import javax.persistence.*;
import java.util.Date;

public class UserDTO {

    private Integer id;


    private String username;

    private String password;


    private String namee;


    private String surname;


    private String phoneNumber;


    private String email;


    private Date birthDate;


    private String rola;

    public UserDTO(Integer id, String username, String password, String namee, String surname, String phoneNumber, String email, Date birthDate, String rola, Boolean active) {
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

    private Boolean active;

    public UserDTO() {

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
