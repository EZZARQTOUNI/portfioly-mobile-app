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

public class FeedBackActivity extends AppCompatActivity {
    UserService userService;
    TextInputEditText message;
    RatingBar feedback;
    MaterialButton sendButton;
    User user;
    MaterialButton go_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        userService = new UserService(FeedBackActivity.this);
        feedback = findViewById(R.id.feedback);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        sendButton = findViewById(R.id.save_button);
        message = findViewById(R.id.message);
        user = Helper.getUser(FeedBackActivity.this);
        sendButton.setOnClickListener(e->{

            String messageText = message.getText().toString();
            Boolean flag = true;
            if(messageText.length()<4){
                message.setError("Please add valid message");
                flag = false;
            }
            if(flag){
                sendButton.setEnabled(false);
                Map<String,String> d = new HashMap<>();
                d.put("email",user.getEmail());
                d.put("feedback", String.valueOf(feedback.getRating()));

                userService.SendMessageOrRequest(d, new VolleyCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.success(FeedBackActivity.this,"Your feedback was sent","Your feedback was sent succefuly, You can send a new feedback if you change your opinion in our application");
                        message.setText("");
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
}