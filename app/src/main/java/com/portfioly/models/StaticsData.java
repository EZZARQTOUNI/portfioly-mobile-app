package com.portfioly.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StaticsData {
    ArrayList<SimpleData> browsers;
    ArrayList<SimpleData> country;
    ArrayList<SimpleData> plarform;
    ArrayList<SimpleData> views;

    public StaticsData(ArrayList<SimpleData> browsers, ArrayList<SimpleData> country, ArrayList<SimpleData> plarform, ArrayList<SimpleData> views) {
        this.browsers = browsers;
        this.country = country;
        this.plarform = plarform;
        this.views = views;
    }

    public ArrayList<SimpleData> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(ArrayList<SimpleData> browsers) {
        this.browsers = browsers;
    }

    public ArrayList<SimpleData> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<SimpleData> country) {
        this.country = country;
    }

    public ArrayList<SimpleData> getPlarform() {
        return plarform;
    }

    public void setPlarform(ArrayList<SimpleData> plarform) {
        this.plarform = plarform;
    }

    public ArrayList<SimpleData> getViews() {
        return views;
    }

    public void setViews(ArrayList<SimpleData> views) {
        this.views = views;
    }
    public List<Double> getViewsList(){
        ArrayList<Double> d = new ArrayList<>();
        int n = views.size();
        int j= 0;
        for(int i = n-1;i>-1;i--) {
            if(j==10) break;
            d.add(views.get(i).getCount());
            j++;
        }
        return d;
    }
    public  int getTotalViews(){
        int s = 0;
        for(SimpleData e : getViews())
            s+=e.getCount();
        return s;
    }

    public HashMap<Integer,String> getViewsHelper(){
        HashMap<Integer, String> m = new HashMap<>();
        int n = views.size();
        int j= 0;
        for(int i = n-1;i>-1;i--) {
            if(j==10) break;
            j++;
        }
        return  m;
    }
    public Map<String, Integer> getCountriesData(){
        Map<String, Integer> data = new HashMap<>();
        int j= 0;
        for(SimpleData e : country) {
            if(j==10) break;
            data.put(e.name,(e.count).intValue());
            j++;
        }
        return  data;
    }

    public Map<String, Integer> getBrowsersData() {
        Map<String, Integer> data = new HashMap<>();
        int j= 0;
        for(SimpleData e : browsers) {
            if(j==10) break;
            data.put(e.name,(e.count).intValue());
            j++;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (int num : list) {
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        return  sortedMap;
    }

    public Map<String, Integer> getPlarformData() {
        Map<String, Integer> data = new HashMap<>();
        int j= 0;
        for(SimpleData e : plarform) {
            if(j==10) break;
            data.put(e.name,(e.count).intValue());
            j++;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (int num : list) {
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        return  sortedMap;
    }
}
