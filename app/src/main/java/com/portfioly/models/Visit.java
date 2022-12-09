package com.portfioly.models;

import java.io.Serializable;

public class Visit implements Serializable {
    private  String country;
    private  String countyCode;
    private  String region;
    private  String city;
    private  String browser;
    private  String platform;
    private  String date;

    public Visit(String country, String countyCode, String region, String city, String browser, String platform, String date) {
        this.country = country;
        this.countyCode = countyCode;
        this.region = region;
        this.city = city;
        this.browser = browser;
        this.platform = platform;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
