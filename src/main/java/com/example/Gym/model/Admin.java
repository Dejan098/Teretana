package com.example.Gym.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("admin")

public class Admin extends User{


    public Admin(Integer id, String username, String password, String name, String surname, String phoneNumber, String email, Date birthDate, String rola, Boolean active) {
        super(id, username, password, name, surname, phoneNumber, email, birthDate, rola, active);
    }

    public Admin() {

    }
}
