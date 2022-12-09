package com.portfioly.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.portfioly.MainActivity;
import com.portfioly.R;
import com.portfioly.Services.UserService;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.VolleyCallBack;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        userService =  new UserService(SplashActivity.this);
        userService.setContext(SplashActivity.this);
        String token = Helper.getToken(SplashActivity.this);
        if(token.isEmpty() ){
            Intent i  =  new Intent(SplashActivity.this, LoginActivity.class);
            Helper.setTimout(()->{
                startActivity(i);
                finish();
            },3000);

        }
        else{
            Map<String,String> data = new HashMap<String,String>();
            data.put("token", token);
            userService.checkUserByToken(data, new VolleyCallBack() {
                @Override
                public void onSuccess(Object o) {
                    User u = (User) o;
                    u.setMobileToken(data.get("token"));
                    Intent i  =  new Intent(SplashActivity.this, MainActivity.class);
                    Helper.setTimout(()->{
                        startActivity(i);
                        finish();
                    },500);
                }

                @Override
                public void onError(int error) {
                    Intent i  =  new Intent(SplashActivity.this, LoginActivity.class);
                    Helper.setTimout(()->{
                        startActivity(i);
                        finish();
                    },3000);
                }

                @Override
                public void beforeSend() {
                }

                @Override
                public void onFinish() {}
            });
        }

    }
}
