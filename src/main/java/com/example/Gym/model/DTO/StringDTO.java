package com.example.Gym.model.DTO;

import java.util.Date;

public class StringDTO {
    private String kriterijum;
    private String naziv;
    private String tip;
    private String opis;
    private Integer cena;
    private Date vreme;

    public StringDTO(String kriterijum, String naziv, String tip, String opis, Integer cena, Date vreme) {
        this.kriterijum = kriterijum;
        this.naziv = naziv;
        this.tip = tip;
        this.opis = opis;
        this.cena = cena;
        this.vreme = vreme;
    }

    public String getKriterijum() {
        return kriterijum;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }
}
