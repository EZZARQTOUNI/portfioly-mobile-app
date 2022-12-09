package com.portfioly.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;
import com.portfioly.MainActivity;
import com.portfioly.R;
import com.portfioly.Services.UserService;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.VolleyCallBack;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout loaderContainer;
    private ScrollView formContainer;
    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private MaterialButton loginButton;
    private UserService userService;
    private TextView forgotPassword;
    private  TextView contactUs;
    String firebaseToekn = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loaderContainer =(LinearLayout) findViewById(R.id.loader_container);
        formContainer =(ScrollView) findViewById(R.id.form_container);
        emailInput = (TextInputEditText) findViewById(R.id.email);
        passwordInput = (TextInputEditText) findViewById(R.id.password);
        loginButton = (MaterialButton) findViewById(R.id.login_button);
        loginButton.setOnClickListener(e->{onLogin(e,getBaseContext());});
        userService = new UserService(LoginActivity.this);
        forgotPassword = findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(e->{startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));});
        contactUs = findViewById(R.id.contact_us);
        contactUs.setOnClickListener(e->{startActivity(new Intent(LoginActivity.this, ContactUsActivity.class));});

    }
    public void onLogin(View e, Context context){

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult();
                        firebaseToekn = token;
                        Log.println(Log.INFO,"lllll",firebaseToekn);
                        boolean flag = true;
                        String email = emailInput.getText().toString();
                        String password = passwordInput.getText().toString();
                        if(email.length()<4 || !checkEmail(email) ) {
                            emailInput.setError("Please, Enter a valid email");
                            flag =false;
                        }
                        if(password.length()<4){
                            passwordInput.setError("Please, Enter a valid password");
                            flag =false;
                        }
                        if(flag){
                            showLoader();
                            Map<String,String> data = new HashMap<String,String>();
                            data.put("email",email);
                            data.put("password",password);
                            data.put("firebase",token);

                            userService.Login(data, new VolleyCallBack() {
                                @Override
                                public void onSuccess(Object o) {
                                    User user = (User) o;
                                    Helper.saveUser(context,user);
                                    Log.w("test",Helper.getToken(LoginActivity.this));
                                    //newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                                    Helper.setTimout(()->{
                                        Intent newIntent = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(newIntent);
                                        finish();
                                    },1500);
                                }

                                @Override
                                public void onError(int error) {
                                    Helper.setTimout(()->{
                                        hideLoader();
                                    },1000);
                                }

                                @Override
                                public void beforeSend() {

                                }

                                @Override
                                public void onFinish() {

                                }
                            });
                        }
                    }
                });
    }
    public boolean checkEmail(String email){
        return(Pattern.matches("([\\w+[._-]*]{2,}@[\\w]{2,}.[\\w]{2,})", email));
    }
    public void showLoader(){
        formContainer.setVisibility(View.GONE);
        loaderContainer.setVisibility(View.VISIBLE);
    }
    public void hideLoader(){
        formContainer.setVisibility(View.VISIBLE);
        loaderContainer.setVisibility(View.GONE);
    }
}