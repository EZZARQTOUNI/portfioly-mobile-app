package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.portfioly.R;
import com.portfioly.Services.UserService;
import com.portfioly.utils.Helper;
import com.portfioly.utils.Toast;
import com.portfioly.utils.VolleyCallBack;

import java.util.HashMap;
import java.util.Map;

public class EditPasswordActivity extends AppCompatActivity {

    TextInputEditText oldPassword;
    TextInputEditText password;
    TextInputEditText confirmPassword;
    MaterialButton save;
    UserService userService;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        oldPassword = findViewById(R.id.old_password);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        save = findViewById(R.id.save_button);
        userService = new UserService(EditPasswordActivity.this);
        save.setOnClickListener(e->{
            Boolean flag = true;
            String passwordT = password.getText().toString();
            String confirmPasswordT = confirmPassword.getText().toString();
            String oldPasswordT = oldPassword.getText().toString();

            if(oldPasswordT.length()<4  ) {
                oldPassword.setError("Please, Enter a valid Password");
                flag =false;
            }
            if(confirmPasswordT.length()<4 || !passwordT.equals(confirmPasswordT)){
                confirmPassword.setError("Please, Enter a valid Password");
                flag =false;
            }
            if(passwordT.length()<4){
                password.setError("Please, Enter a valid Password");
                flag =false;
            }
            if(flag){
                Map<String,String> data = new HashMap<>();
                data.put("old_password",oldPasswordT);
                data.put("password",passwordT);
                data.put("confirm_password",confirmPasswordT);
                data.put("token", Helper.getToken(EditPasswordActivity.this));
                userService.changePassword(data, new VolleyCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.success(EditPasswordActivity.this,"Password edited","Your password was edited successfully !");
                    }

                    @Override
                    public void onError(int error) {
                        save.setEnabled(true);
                    }

                    @Override
                    public void beforeSend() {
                        save.setEnabled(false);
                    }

                    @Override
                    public void onFinish() {
                        save.setEnabled(true);
                    }
                });
            }
        });


    }
}