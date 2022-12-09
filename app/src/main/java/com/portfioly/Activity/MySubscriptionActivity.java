package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.R;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.SubscriptionHelper;

public class MySubscriptionActivity extends AppCompatActivity {

    User user;
    TextView package_title;
    TextView package_price;
    MaterialButton show_advantage;
    MaterialButton show_all;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subscription);
        user = Helper.getUser(MySubscriptionActivity.this);
        package_title = findViewById(R.id.package_title);
        package_price = findViewById(R.id.package_price);
        show_advantage = findViewById(R.id.show_advantage);
        show_all = findViewById(R.id.show_all);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        package_title.setText(user.getSubscribtion()+" PACKAGE");
        package_price.setText(SubscriptionHelper.getPrice(user.getSubscribtion()));
        show_advantage.setOnClickListener(e->{
            Intent i = new Intent(MySubscriptionActivity.this, PackageActivity.class);
            i.putExtra("subscription",user.getSubscribtion());
            startActivity(i);
        });
        show_all.setOnClickListener(e->{
            Intent i = new Intent(MySubscriptionActivity.this, SubscriptionsActivity.class);
            startActivity(i);
        });
    }

}