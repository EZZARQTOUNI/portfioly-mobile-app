package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.R;
import com.portfioly.models.Visit;
import com.portfioly.utils.Flags;
import com.portfioly.utils.Helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

public class VisitsActivity extends AppCompatActivity {

    ArrayList<Visit> visits;
    LinearLayout viewContaienr;
    LinearLayout loaderContainer;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visits);
        visits = (ArrayList<Visit>) getIntent().getSerializableExtra("visits");
        viewContaienr = findViewById(R.id.visit_container);
        loaderContainer = findViewById(R.id.loader_container);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
            Helper.setTimout(()->{
                for(Visit v : visits){
                    createViewElement(v);
                }
            },1000);
            Helper.setTimout(()->{ loaderContainer.setVisibility(View.GONE);},2500);
    }

    public void createViewElement(Visit d){
        LinearLayout viewElement = (LinearLayout) getLayoutInflater().inflate(R.layout.view_element,null);
        ((TextView) viewElement.findViewById(R.id.title_elment)).setText(("FROM "+d.getCountry()).toUpperCase(Locale.ROOT));
        ((TextView) viewElement.findViewById(R.id.time_element)).setText(((d.getDate()).replace(",","")).replace(" PM","PM"));
        ((ImageView) viewElement.findViewById(R.id.flag_element)).setImageResource(Flags.getFlag(VisitsActivity.this,d.getCountyCode().toLowerCase()));
        ((MaterialButton) viewElement.findViewById(R.id.view_button)).setOnClickListener(e->{
            Intent i =  new Intent(VisitsActivity.this, ViewActivity.class);
            i.putExtra("visit", (Serializable) d);
            startActivity(i);
        });
        viewContaienr.addView(viewElement);
    }

}