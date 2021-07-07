package com.example.Gym.model.DTO;

import java.time.LocalDate;

public class ScheduleDTO {
    private Integer id;
    private Integer price;
    private Integer sala;
    private LocalDate beginDate;
    private Integer training;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ScheduleDTO(Integer id, Integer price, Integer sala, LocalDate beginDate, Integer training) {
        this.id = id;
        this.price = price;
        this.sala = sala;
        this.beginDate = beginDate;
        this.training = training;
    }

    public ScheduleDTO() {
    }

    public ScheduleDTO(Integer price, Integer sala, LocalDate beginDate, Integer training) {
        this.price = price;
        this.sala = sala;
        this.beginDate = beginDate;
        this.training = training;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public Integer getTraining() {
        return training;
    }

    public void setTraining(Integer training) {
        this.training = training;
    }
}
