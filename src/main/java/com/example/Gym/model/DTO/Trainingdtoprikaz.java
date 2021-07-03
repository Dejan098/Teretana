package com.example.Gym.model.DTO;

public class Trainingdtoprikaz {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private String duration;
    private Integer ocena;

    public Trainingdtoprikaz(Integer id, String name, String description, String type, String duration, Integer ocena) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.duration = duration;
        this.ocena = ocena;
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

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }
}
