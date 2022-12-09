package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.portfioly.MainActivity;
import com.portfioly.R;
import com.portfioly.Services.UserService;
import com.portfioly.models.User;
import com.portfioly.utils.Helper;
import com.portfioly.utils.Toast;
import com.portfioly.utils.VolleyCallBack;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class EditProfileActivity extends AppCompatActivity {

    TextInputEditText firstName;
    TextInputEditText lastName;
    TextInputEditText email;
    TextInputEditText phoneNumber;
    MaterialButton saveButton;
    User user;
    UserService userService;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        firstName =  findViewById(R.id.first_name);
        lastName =  findViewById(R.id.last_name);
        email =  findViewById(R.id.email);
        phoneNumber =  findViewById(R.id.phone_number);
        saveButton =  findViewById(R.id.save_button);
        user = Helper.getUser(EditProfileActivity.this);
        firstName.setText(user.getFirstname());
        lastName.setText(user.getLastname());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhonenumber());
        userService = new UserService(EditProfileActivity.this);
        saveButton.setOnClickListener(e->{
            try {
                email.onEditorAction(EditorInfo.IME_ACTION_DONE);
                lastName.onEditorAction(EditorInfo.IME_ACTION_DONE);
                phoneNumber.onEditorAction(EditorInfo.IME_ACTION_DONE);
                firstName.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
            catch (Exception ex){}

            Boolean flag = true;
            String emailT = email.getText().toString();
            String firstnameT = firstName.getText().toString();
            String lastNameT = lastName.getText().toString();
            String phoneNumberT = phoneNumber.getText().toString();

            if(emailT.length()<4 || !checkEmail(emailT) ) {
                email.setError("Please, Enter a valid email");
                flag =false;
            }
            if(firstnameT.length()<2){
                firstName.setError("Please, Enter a valid first name");
                flag =false;
            }
            if(phoneNumberT.length()<6){
                phoneNumber.setError("Please, Enter a valid phone number");
                flag =false;
            }
            if(lastNameT.length()<2){
                lastName.setError("Please, Enter a valid last name");
                flag =false;
            }

            if(flag){
                Map<String,String> data = new HashMap<>();
                data.put("firstname",firstnameT);
                data.put("lastname",lastNameT);
                data.put("email",emailT);
                data.put("phonenumber",phoneNumberT);
                data.put("token",Helper.getToken(EditProfileActivity.this));
                userService.updateProfile(data, new VolleyCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        user.setEmail(emailT);
                        user.setFirstname(firstnameT);
                        user.setLastname(lastNameT);
                        user.setPhonenumber(phoneNumberT);
                        Helper.saveUser(EditProfileActivity.this,user);
                        Toast.success(EditProfileActivity.this,"Profile edited","Your profile was edited successfully !");

                    }

                    @Override
                    public void onError(int error) {
                        saveButton.setEnabled(true);
                    }

                    @Override
                    public void beforeSend() {
                        saveButton.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        saveButton.setEnabled(true);
                    }
                });
            }
        });
    }

    public boolean checkEmail(String email){
        return(Pattern.matches("([\\w+[._-]*]{2,}@[\\w]{2,}.[\\w]{2,})", email));
    }

}