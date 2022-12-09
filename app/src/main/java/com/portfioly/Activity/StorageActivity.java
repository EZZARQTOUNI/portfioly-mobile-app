package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.material.button.MaterialButton;
import com.portfioly.R;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;

public class StorageActivity extends AppCompatActivity {
    User user;
    TextView total;
    TextView used;
    ArcProgress percentView;
    MaterialButton ask_more;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        user = Helper.getUser(StorageActivity.this);
        total = findViewById(R.id.total);
        used = findViewById(R.id.used);
        percentView = findViewById(R.id.percent_view);
        ask_more = findViewById(R.id.ask_more);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});

        float totalV = Float.parseFloat(user.getMaxstorage());
        float usedV = Float.parseFloat(user.getStorage());
        if(usedV<0.1) usedV= 0.10f;
        total.setText(String.format("%.02f", totalV)+" GB");
        used.setText(String.format("%.02f", usedV)+" GB");
        float percentage = usedV/totalV;
        if(percentage<2) percentage = 2f;
        percentView.setProgress((int) percentage);

        ask_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StorageActivity.this,ContactUsActivity.class));
            }
        });

    }

}