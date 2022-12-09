package com.portfioly.models;

public class User {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String subscribtion;
    private String mobileToken;
    private String phonenumber;
    private String storage;
    private String maxstorage;


    public User(Long id, String firstname, String lastname, String username, String email, String subscribtion, String mobileToken,String phonenumber,String storage,String maxstorage) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.subscribtion = subscribtion;
        this.mobileToken = mobileToken;
        this.phonenumber = phonenumber;
        this.storage = storage;
        this.maxstorage  = maxstorage;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getSubscribtion() {
        return subscribtion;
    }

    public String getMobileToken() {
        return mobileToken;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubscribtion(String subscribtion) {
        this.subscribtion = subscribtion;
    }

    public void setMobileToken(String mobileToken) {
        this.mobileToken = mobileToken;
    }

    public String getPhonenumber() {return phonenumber;}

    public String getStorage() {return storage;}

    public String getMaxstorage() {return maxstorage;}

    public void setPhonenumber(String phonenumber) {this.phonenumber = phonenumber;}

    public void setStorage(String storage) {this.storage = storage;}

    public void setMaxstorage(String maxstorage) {this.maxstorage = maxstorage;}
}
