package com.example.Gym.model.DTO;

import java.util.Date;

public class StringDTO {
    private String kriterijumpretrage;
    private String vrednostPretrage;
    private Integer cena;
    private Date vremetermina;

    public StringDTO() {
    }

    public String getKriterijumpretrage() {
        return kriterijumpretrage;
    }

    public void setKriterijumpretrage(String kriterijumpretrage) {
        this.kriterijumpretrage = kriterijumpretrage;
    }

    public String getVrednostPretrage() {
        return vrednostPretrage;
    }

    public void setVrednostPretrage(String vrednostPretrage) {
        this.vrednostPretrage = vrednostPretrage;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Date getVremetermina() {
        return vremetermina;
    }

    public void setVremetermina(Date vremetermina) {
        this.vremetermina = vremetermina;
    }
}
