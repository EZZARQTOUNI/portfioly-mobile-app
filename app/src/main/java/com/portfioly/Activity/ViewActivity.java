package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.R;
import com.portfioly.models.Visit;
import com.portfioly.utils.Flags;

public class ViewActivity extends AppCompatActivity {

    ImageView platformeImage;
    ImageView countryImage;
    ImageView browserImage;
    TextView textCountyCode;
    TextView textTitle;
    TextView textDescript;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Visit v = ((Visit) getIntent().getSerializableExtra("visit"));

        countryImage = findViewById(R.id.country_image);
        browserImage = findViewById(R.id.browser_image);
        platformeImage = findViewById(R.id.platforme_image);
        textCountyCode = findViewById(R.id.text_county_code);
        textTitle = findViewById(R.id.text_title);
        textDescript = findViewById(R.id.text_descript);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        countryImage.setImageResource(Flags.getFlag(ViewActivity.this,v.getCountyCode().toLowerCase()));
        browserImage.setImageResource(Flags.getBrowser(ViewActivity.this,v.getBrowser()));
        platformeImage.setImageResource(Flags.getPlatforme(ViewActivity.this,v.getPlatform()));
        textCountyCode.setText(v.getCountyCode());
        textTitle.setText("This visit is from "+v.getCountry());
        textDescript.setText("Someone from "+v.getCity()+" city, from the region of "+v.getRegion()+" visited your website at "+v.getDate()+", from a "+v.getPlatform()+" systeme with "+v.getBrowser()+".");

    }
}