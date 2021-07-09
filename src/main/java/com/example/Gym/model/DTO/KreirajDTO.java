package com.example.Gym.model.DTO;

public class KreirajDTO {
    private String name;
    private String description;
    private String type;
    private String duration;

    public KreirajDTO() {
    }

    public KreirajDTO(String name, String description, String type, String duration) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.duration = duration;
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
