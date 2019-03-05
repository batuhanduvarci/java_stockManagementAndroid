package com.example.agent48.termproject.Object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Agent48 on 7.05.2018.
 */

public class User {

    @SerializedName("ID")
    private String id;
    @SerializedName("SIRKET_ADI")
    private String companyName;
    @SerializedName("AD")
    private String name;
    @SerializedName("SOYAD")
    private String surname;
    @SerializedName("E_POSTA")
    private String email;
    @SerializedName("KULLANICI_ADI")
    private String username;
    @SerializedName("SIFRE")
    private String password;
    @SerializedName("DURUM")
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
