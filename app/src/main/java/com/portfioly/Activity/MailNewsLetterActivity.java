package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RatingBar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.portfioly.R;
import com.portfioly.Services.UserService;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.Toast;
import com.portfioly.utils.VolleyCallBack;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MailNewsLetterActivity extends AppCompatActivity {
    UserService userService;
    TextInputEditText email;
    MaterialButton sendButton;
    User user;
    MaterialButton go_back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_news_letter);
        userService = new UserService(MailNewsLetterActivity.this);
        sendButton = findViewById(R.id.save_button);
        email = findViewById(R.id.email);
        user = Helper.getUser(MailNewsLetterActivity.this);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        sendButton.setOnClickListener(e->{

            String messageText = email.getText().toString();
            Boolean flag = true;
            if(messageText.length()<4 || !checkEmail(messageText) ) {
                email.setError("Please, Enter a valid email");
                flag =false;
            }
            if(flag){
                sendButton.setEnabled(false);
                Map<String,String> d = new HashMap<>();
                d.put("email",user.getEmail());
                userService.NewsLetter(d, new VolleyCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.success(MailNewsLetterActivity.this,"Subscription Done","Your are subscribed succefuly to our news letter");
                        email.setText("");
                        Helper.setTimout(()->{finish();},3000);
                    }

                    @Override
                    public void onError(int error) {

                    }

                    @Override
                    public void beforeSend() {

                    }

                    @Override
                    public void onFinish() {
                        sendButton.setEnabled(true);
                    }
                });
            }
        });
    }
    public boolean checkEmail(String email){
        return(Pattern.matches("([\\w+[._-]*]{2,}@[\\w]{2,}.[\\w]{2,})", email));
    }
}