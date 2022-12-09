package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.portfioly.R;
import com.portfioly.Services.UserService;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.Toast;
import com.portfioly.utils.VolleyCallBack;

import java.util.HashMap;
import java.util.Map;

public class ContactUsActivity extends AppCompatActivity {
    UserService userService;
    TextInputLayout fullNameContainer;
    TextInputLayout emailContainer;
    TextInputLayout subjectContainer;
    TextInputLayout messageContainer;
    TextInputEditText fullName;
    TextInputEditText email;
    TextInputEditText subject;
    TextInputEditText message;
    MaterialButton sendButton;
    User user;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        String token = Helper.getToken(ContactUsActivity.this);
        fullNameContainer = findViewById(R.id.fullNameContainer);
        emailContainer = findViewById(R.id.emailContainer);
        subjectContainer = findViewById(R.id.subjectContainer);
        messageContainer = findViewById(R.id.messageContainer);
        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        sendButton = findViewById(R.id.send_button);
        userService = new UserService(ContactUsActivity.this);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        if(!token.isEmpty() && token.length()>0){
            user = Helper.getUser(ContactUsActivity.this);
            //fullNameContainer.setVisibility(View.GONE);
            fullName.setText(user.getFirstname()+" "+user.getLastname());
            subject.setText("SUBJECT");
            subjectContainer.setVisibility(View.GONE);
            email.setEnabled(false);
            fullName.setEnabled(false);
            email.setText(user.getEmail());
        }

        sendButton.setOnClickListener(e->{
            String FullNameText = fullName.getText().toString();
            String emailText = email.getText().toString();
            String subjectText = subject.getText().toString();
            String messageText = message.getText().toString();
            Boolean flag = true;
            if(FullNameText.length()<4){
                fullName.setError("Please add valid name");
                flag = false;
            }
            if(emailText.length()<4){
                email.setError("Please add valid Email");
                flag = false;
            }
            if(subjectText.length()<4){
                subject.setError("Please add valid subject");
                flag = false;
            }
            if(messageText.length()<4){
                message.setError("Please add valid message");
                flag = false;
            }
            if(flag){
                sendButton.setEnabled(false);
                Map<String,String> d = new HashMap<>();
                d.put("email",emailText);
                userService.SendMessageOrRequest(d, new VolleyCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.success(ContactUsActivity.this,"Your request was sent","Your request was sent succefuly, we will return to you son as possible");
                        fullName.setText("");
                        email.setText("");
                        subject.setText("");
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