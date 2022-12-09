package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.R;
import com.portfioly.models.Mails;
import com.portfioly.models.Visit;
import com.portfioly.utils.Flags;

public class MailElementActivity extends AppCompatActivity {
    TextView subject;
    TextView full_name;
    TextView email;
    TextView message;
    TextView date;
    ImageView platformeImage;
    ImageView countryImage;
    ImageView browserImage;
    TextView textCountyCode;
    MaterialButton go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_element);
        Mails m = ((Mails) getIntent().getSerializableExtra("mail"));
        subject = findViewById(R.id.subject);
        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        message = findViewById(R.id.message);
        date = findViewById(R.id.date);
        countryImage = findViewById(R.id.country_image);
        browserImage = findViewById(R.id.browser_image);
        platformeImage = findViewById(R.id.platforme_image);
        textCountyCode = findViewById(R.id.text_county_code);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});

        message.setText(m.getMessage());
        date.setText(m.getTime());
        full_name.setText(m.getName());
        email.setText(m.getEmail());
        subject.setText(m.getSubject());
        countryImage.setImageResource(Flags.getFlag(MailElementActivity.this,m.getCountryCode().toLowerCase()));
        browserImage.setImageResource(Flags.getBrowser(MailElementActivity.this,m.getBrowser()));
        platformeImage.setImageResource(Flags.getPlatforme(MailElementActivity.this,m.getPlarform()));
        textCountyCode.setText(m.getCountryCode());

    }
}