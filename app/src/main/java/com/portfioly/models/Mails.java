package com.portfioly.models;

import java.io.Serializable;

public class Mails implements Serializable {
    private String name;
    private String subject;
    private String message;
    private String email;
    private String countryName;
    private String countryCode;
    private String regionName;
    private String cityName;
    private String time;
    private String browser;
    private String plarform;


    public Mails(String name, String subject, String message, String email, String countryName, String countryCode, String regionName, String cityName, String time, String browser, String plarform) {
        this.name = name;
        this.subject = subject;
        this.message = message;
        this.email = email;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.regionName = regionName;
        this.cityName = cityName;
        this.time = time;
        this.browser = browser;
        this.plarform = plarform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getPlarform() {
        return plarform;
    }

    public void setPlarform(String plarform) {
        this.plarform = plarform;
    }
}
