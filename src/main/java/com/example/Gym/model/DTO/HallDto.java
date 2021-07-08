package com.example.Gym.model.DTO;

public class HallDto {
    private Integer id;
    private Integer capacity;
    private String label;
    private Integer fitnessala;

    public HallDto(Integer capacity, String label, Integer fitnessala) {
        this.capacity = capacity;
        this.label = label;
        this.fitnessala = fitnessala;
    }

    public HallDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getFitnessala() {
        return fitnessala;
    }

    public void setFitnessala(Integer fitnessala) {
        this.fitnessala = fitnessala;
    }

    public HallDto(Integer id, Integer capacity, String label, Integer fitnessala) {
        this.id = id;
        this.capacity = capacity;
        this.label = label;
        this.fitnessala = fitnessala;
    }
}
