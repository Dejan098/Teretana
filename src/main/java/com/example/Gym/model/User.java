package com.example.Gym.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.DiscriminatorType.STRING;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rolee", discriminatorType=STRING)
@Table(name="users")
public class User implements UserDetails{

    @Id
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    private Integer id;



    @Column(nullable = false)
    private String password;

    @Column
    private String namee;

    @Column
    private String surname;

    @Column
    private String phoneNumber;

    @Column(nullable = false, unique = true)
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
        this.password = password;
        this.namee = namee;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.rola = rola;
        this.active = active;
    }


}
