package com.example.demo.model;

import javax.xml.crypto.Data;
import java.util.Date;

public class Search {

    private String country;
    private String city;
    private String date;

    public Search() {
    }

    public Search(String country, String city, String date) {
        this.country = country;
        this.city = city;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Search{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
