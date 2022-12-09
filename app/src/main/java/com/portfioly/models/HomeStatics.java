package com.portfioly.models;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeStatics implements Serializable {
    private  Integer  works;
    private  Integer  services;
    private  Integer  skills;
    private  Integer  views;
    private ArrayList<Visit> visits;

    public HomeStatics(Integer works, Integer services, Integer skills, Integer views, ArrayList<Visit> visits) {
        this.works = works;
        this.services = services;
        this.skills = skills;
        this.views = views;
        this.visits = visits;
    }

    public Integer getWorks() {
        return works;
    }

    public void setWorks(Integer works) {
        this.works = works;
    }

    public Integer getServices() {
        return services;
    }

    public void setServices(Integer services) {
        this.services = services;
    }

    public Integer getSkills() {
        return skills;
    }

    public void setSkills(Integer skills) {
        this.skills = skills;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public ArrayList<Visit> getVisits() {
        return visits;
    }

    public void setVisits(ArrayList<Visit> visits) {
        this.visits = visits;
    }
}
