package com.portfioly.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Flags {

    private static final String[] countries = new String[] {
            "ad", "ae", "af", "ag", "ai", "al", "am", "ao", "ar", "at", "au", "ax", "az", "ba", "bb",
            "bd", "be", "bf", "bg", "bh", "bi", "bj", "bm", "bn", "bo", "br", "bs", "bt", "bw", "by",
            "bz", "ca", "caf", "cas", "cd", "cf", "cg", "ch", "ch", "ci", "cl", "cm", "cn",
            "cna", "co", "coc", "cr", "csa", "cu", "cv", "cy", "cz", "de", "dj", "dk", "dm", "dz", "ec",
            "ee", "eg", "er", "es", "et", "fi", "fj", "fm", "fr", "ga", "gb", "gd", "ge", "gh",
            "gm", "gn", "gq", "gr", "gt", "gw", "gy", "hk", "hn", "hr", "ht", "hu", "id", "ie", "il",
            "in", "iq", "ir", "is", "it", "jm", "jo", "jp", "ke", "kg", "kh", "km", "kn", "kp", "kr",
            "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma",
            "mc", "md", "me", "mg", "mk", "ml", "mm", "mn", "mo", "mr", "ms", "mt", "mu", "mv", "mw",
            "mx", "my", "mz", "na", "ne", "ng", "ni", "nl", "no", "np", "nz", "om", "pa", "pe", "pg",
            "ph", "pk", "pl", "pr", "pt", "pw", "py", "qa", "ro", "rs", "ru", "rw", "sa", "sb", "sc",
            "sd", "se", "sg", "si", "sk", "sl", "sm", "sn", "so", "sr", "st", "sv", "sy", "sz", "tc",
            "td", "tg", "th", "tj", "tl", "tm", "tn", "to", "tr", "tt", "tw", "tz", "ua", "ug", "us",
            "uy", "uz", "vc", "ve", "vg", "vn", "ws", "ww", "ye", "za", "zw"
    };

    public  static int getFlag(Context context, String flagName){
        Resources res =  context.getResources();
        flagName = flagName.toLowerCase();
        boolean isFlag = Arrays.asList(countries).contains(flagName);
        String flagTitle = (isFlag?(flagName):"united_nations");
        int drawableId=res.getIdentifier(flagTitle, "drawable", context.getPackageName());
        return(drawableId);
    }

    public  static int getBrowser(Context context, String browserName){
        Resources res =  context.getResources();
        Map<String,String> browser = new HashMap<>();
        browser.put("Opera","opera");
        browser.put("Chrome Mobile","chrome");
        browser.put("Chrome Webview","android");
        browser.put("Chrome","chrome");
        browser.put("Safari","safari");
        browser.put("Mobile Safari","safari");
        browser.put("Microsoft Edge","edge");
        browser.put("Android Browser","android");
        browser.put("Samsung Browser","samsung");
        browser.put("Internet Explorer","explorer");
        browser.put("MIUI Browser","xiaomi");

        boolean isFlag = browser.containsKey(browserName);
        String browserTitle = (isFlag?(browser.get(browserName)):"browser");
        int drawableId=res.getIdentifier(browserTitle, "drawable", context.getPackageName());
        return(drawableId);
    }
    public  static int getPlatforme(Context context, String platformName){
        Resources res =  context.getResources();
        Map<String,String> platform = new HashMap<>();
        platform.put("Android","android");
        platform.put("AndroidOS","android");
        platform.put("Windows","windows");
        platform.put("Mac","apple");
        platform.put("iOS","apple");
        platform.put("GNU/Linux","linux");
        platform.put("Ubuntu","ubuntu");

        boolean isFlag = platform.containsKey(platformName);
        String browserTitle = (isFlag?(platform.get(platformName)):"computer");
        int drawableId=res.getIdentifier(browserTitle, "drawable", context.getPackageName());
        return(drawableId);
    }
}
