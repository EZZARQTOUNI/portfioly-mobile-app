package com.portfioly.utils;

public class SubscriptionHelper {

    public  static  String getPrice(String p){
        if(p.equals("STARTER")) return "$35.98";
        else if(p.equals("PLATINUM")) return "$57.86";
        else if(p.equals("DIAMOND")) return "$89.58";
        else return  "";
    }
}
