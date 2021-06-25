package com.example.Gym.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("training")
public class Training {
    @Id
    @SequenceGenerator(name="seq_training", sequenceName = "seq_training", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_training")
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;


    @Column
    private String type;

    @Column
    private String duration;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    private Trainer trainer;


    @OneToOne(mappedBy = "training", cascade = CascadeType.ALL)
    private Schedule schedule;

    public Training(Integer id, String name, String description, String type, String duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.duration = duration;
    }

    public Training() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
