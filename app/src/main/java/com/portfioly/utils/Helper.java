package com.portfioly.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.google.android.material.button.MaterialButton;
import com.portfioly.Activity.ForumActivity;
import com.portfioly.models.User;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static void saveUser(Context context, User user)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", user.getMobileToken());
        editor.putString("username", user.getUsername());
        editor.putString("email", user.getEmail());
        editor.putString("firstname", user.getFirstname());
        editor.putString("lastname", user.getLastname());
        editor.putString("subscribtion", user.getSubscribtion());
        editor.putString("phonenumber", user.getPhonenumber());
        editor.putString("storage", user.getStorage());
        editor.putString("maxstorage", user.getMaxstorage());

        editor.apply();
    }

    public static void removeUser(Context context)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("token");
        editor.remove("username");
        editor.remove("email");
        editor.remove("firstname");
        editor.remove("lastname");
        editor.remove("subscribtion");
        editor.remove("phonenumber");
        editor.remove("storage");
        editor.remove("maxstorage");

        editor.apply();
    }

    public static String getToken(Context context)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        final String token = sharedPref.getString("token","");
        return token;
    }

    public static User getUser(Context context)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        final String token = sharedPref.getString("token","");
        final String firstname = sharedPref.getString("firstname","");
        final String lastname = sharedPref.getString("lastname","");
        final String username = sharedPref.getString("username","");
        final String email = sharedPref.getString("email","");
        final String subscribtion = sharedPref.getString("subscribtion","");
        final String phonenumber = sharedPref.getString("phonenumber","");
        final String storage = sharedPref.getString("storage","");
        final String maxstorage = sharedPref.getString("maxstorage","");
        return new User(0L,firstname,lastname,username,email,subscribtion,token,phonenumber,storage,maxstorage);
    }
    public static void  initForumButton(MaterialButton btn, Activity activity){
        Intent intent = new Intent(activity, ForumActivity.class);
        btn.setOnClickListener(e->{activity.startActivity(intent);});

    }

    public  static void setTimout(Runnable runnable,int delay){
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        runnable.run();
                    }
                }, delay);
    }
    public static ArrayList<Integer> generateColors(int size){
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#2196f3"));
        colors.add(Color.parseColor("#00bcd4"));
        colors.add(Color.parseColor("#009688"));
        colors.add(Color.parseColor("#4caf50"));
        colors.add(Color.parseColor("#8bc34a"));
        colors.add(Color.parseColor("#1976d2"));
        colors.add(Color.parseColor("#00bcd4"));
        colors.add(Color.parseColor("#2196f3"));
        colors.add(Color.parseColor("#0066ff"));
        colors.add(Color.parseColor("#2196f3"));
        colors.add(Color.parseColor("#009688"));
        colors.add(Color.parseColor("#f44336"));
        colors.add(Color.parseColor("#e91e63"));
        colors.add(Color.parseColor("#63C5DA"));
        colors.add(Color.parseColor("#9c27b0"));
        colors.add(Color.parseColor("#673ab7"));

        colors.add(Color.parseColor("#2196f3"));
        colors.add(Color.parseColor("#00bcd4"));
        colors.add(Color.parseColor("#009688"));
        colors.add(Color.parseColor("#4caf50"));
        colors.add(Color.parseColor("#8bc34a"));
        colors.add(Color.parseColor("#cddc39"));
        colors.add(Color.parseColor("#ffeb3b"));
        colors.add(Color.parseColor("#ffc107"));
        colors.add(Color.parseColor("#ff9800"));
        colors.add(Color.parseColor("#ff9800"));
        colors.add(Color.parseColor("#f44336"));
        colors.add(Color.parseColor("#e91e63"));
        colors.add(Color.parseColor("#63C5DA"));
        colors.add(Color.parseColor("#9c27b0"));
        colors.add(Color.parseColor("#673ab7"));

        if(colors.size()<=size) return (ArrayList<Integer>) colors.subList(0,size);
        return   (ArrayList<Integer>) colors;
    }


}
