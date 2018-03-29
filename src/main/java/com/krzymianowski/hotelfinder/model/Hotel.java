package com.krzymianowski.hotelfinder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hotel {

    @Id
    @GeneratedValue
    private long id;
    private String hotelName;
    private double stars;
    private double price;
    private String cityName;
    private String countryCode;
    private String countryName;
    private String address;
    private String location;
    private String url;
    private String latitude;
    private String longitude;

    public Hotel() {
    }

    public Hotel(String hotelName, double stars, double price, String cityName, String countryCode, String countryName,
                 String address, String location, String url, String latitude, String longitude) {
        this.hotelName = hotelName;
        this.stars = stars;
        this.price = price;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.address = address;
        this.location = location;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(byte stars) {
        this.stars = stars;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
