package com.ketodiyeti.ketodiyeti;

import java.io.Serializable;

public class Person implements Serializable {
    private String isim;
    private String mail;
    private String yas, kilo, boy;
    private String cinsiyet;
    private String kisiId;
    private String fotoUrl;

    public Person(String isim, String mail, String yas, String kilo, String boy, String cinsiyet, String kisiId, String fotoUrl) {
        this.isim = isim;
        this.mail = mail;
        this.yas = yas;
        this.kilo = kilo;
        this.boy = boy;
        this.cinsiyet = cinsiyet;
        this.kisiId = kisiId;
        this.fotoUrl = fotoUrl;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }

    public String getKilo() {
        return kilo;
    }

    public void setKilo(String kilo) {
        this.kilo = kilo;
    }

    public String getBoy() {
        return boy;
    }

    public void setBoy(String boy) {
        this.boy = boy;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getKisiId() {
        return kisiId;
    }

    public void setKisiId(String kisiId) {
        this.kisiId = kisiId;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
