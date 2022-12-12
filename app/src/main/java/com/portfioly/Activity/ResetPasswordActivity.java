package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.portfioly.R;
import com.portfioly.Services.UserService;
import com.portfioly.utils.Toast;
import com.portfioly.utils.VolleyCallBack;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ResetPasswordActivity extends AppCompatActivity {
    UserService userService;
    TextInputEditText email;
    TextInputEditText code;
    TextInputEditText password;
    TextInputEditText confirmationPassword;
    MaterialButton send;
    MaterialButton cancel;
    static int page = 0;
    TextInputLayout codeContainer;
    TextInputLayout passwordContainer;
    TextInputLayout ConfirmationContainer;
    LinearLayout text_reset;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        userService =  new UserService(ResetPasswordActivity.this);
        email = findViewById(R.id.email);
        code = findViewById(R.id.code);
        password = findViewById(R.id.password);
        confirmationPassword = findViewById(R.id.password_confirmation);
        send = findViewById(R.id.send_button);
        cancel = findViewById(R.id.cancel_button);
        text_reset = findViewById(R.id.text_reset);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        codeContainer = findViewById(R.id.codeContainer);
        passwordContainer = findViewById(R.id.passwordContainer);
        ConfirmationContainer = findViewById(R.id.confirmationContainer);
        cancel.setOnClickListener(e->{
            email.setText("");
            cancel.setVisibility(View.GONE);
            codeContainer.setVisibility(View.GONE);
            passwordContainer.setVisibility(View.GONE);
            ConfirmationContainer.setVisibility(View.GONE);
            send.setText("Send");
            text_reset.setVisibility(View.VISIBLE);
            page = 0;
            email.setEnabled(true);
            send.setEnabled(true);
        });
        send.setOnClickListener(e->{
            Map<String,String> data = new HashMap<>();
            send.setEnabled(false);
            if(page==0){
                Boolean flag = true;
                String emailText = email.getText().toString();
                if(emailText.length()<4 || !checkEmail(emailText)){
                    email.setError("Please, Enter a valid Email");
                    flag = false;
                }
                if(flag){
                    email.setEnabled(false);
                    data.put("email",emailText);
                    userService.RequestCodeForResset(data, new VolleyCallBack() {
                        @Override
                        public void onSuccess(Object o) {
                            Boolean f = (Boolean) o;
                            if(f){
                                cancel.setVisibility(View.VISIBLE);
                                codeContainer.setVisibility(View.VISIBLE);
                                passwordContainer.setVisibility(View.VISIBLE);
                                ConfirmationContainer.setVisibility(View.VISIBLE);
                                Toast.success(ResetPasswordActivity.this,"Code was sent to you","We send a 6 digits code to your email too confirm that you are the owner of this account");
                                send.setText("DONE");
                                page = 1;
                                text_reset.setVisibility(View.GONE);
                                send.setEnabled(true);
                            }
                        }

                        @Override
                        public void onError(int error) {
                            email.setEnabled(true);
                            Toast.error(ResetPasswordActivity.this,"Invalid Email","please check the email that you provided to us");
                        }

                        @Override
                        public void beforeSend() {}

                        @Override
                        public void onFinish() {
                            send.setEnabled(true);
                        }
                    });
                }
                else send.setEnabled(true);
            }
            if(page==1){
                String passwordText = password.getText().toString();
                String confirmationPasswordText = confirmationPassword.getText().toString();
                String emailText = email.getText().toString();
                String codeText = code.getText().toString();
                Boolean flag = true;
                if(passwordText.length()<4 ){
                    password.setError("Please, Enter a valid Password");
                    flag = false;
                }
                if(codeText.length()<4 ){
                    code.setError("Please, Enter a valid code");
                    flag = false;
                }
                if(confirmationPasswordText.length()<4 || !confirmationPasswordText.equals(passwordText) ){
                    confirmationPassword.setError("Please, Enter a valid Password");
                    flag = false;
                }
                if(flag)
                {
                    data.put("email",emailText);
                    data.put("code",codeText);
                    data.put("password",passwordText);
                    data.put("password_confirmation",passwordText);

                    userService.RessetPassword(data, new VolleyCallBack() {
                        @Override
                        public void onSuccess(Object o) {
                            Boolean f = (Boolean) o;
                            if(f){
                                finish();
                            }
                        }

                        @Override
                        public void onError(int error) {

                        }

                        @Override
                        public void beforeSend() {

                        }

                        @Override
                        public void onFinish() {

                        }
                    });
                }
                else send.setEnabled(true);
            }

        });
    }

    public boolean checkEmail(String email){
        return(Pattern.matches("([\\w+[._-]*]{2,}@[\\w]{2,}.[\\w]{2,})", email));
    }
}