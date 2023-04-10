package com.example.money_meow.user.signup;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.R;
import com.example.money_meow.user.User;
import com.google.android.material.textfield.TextInputEditText;

public class SignupAction extends AppCompatActivity {
    private User user;
    TextInputEditText name;
    TextInputEditText userName;
    TextInputEditText password;
    TextInputEditText confirmPassword;
    TextInputEditText email;


    Button signUpBtn;
    Button loginNavig;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name = (TextInputEditText) findViewById(R.id.name);
        userName = (TextInputEditText) findViewById(R.id.user_name);
        password = (TextInputEditText) findViewById(R.id.password);
        confirmPassword = (TextInputEditText) findViewById(R.id.cf_password);
        email = (TextInputEditText) findViewById(R.id.email);


        signUpBtn = (Button) findViewById(R.id.signup_btn);
        loginNavig = (Button) findViewById(R.id.login_btn);

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String pwd = password.toString();
                String cfpwd = confirmPassword.toString();
                if (editable.length() > 0 && pwd.length() > 0) {
                    if(!cfpwd.equals(pwd)){
                        Toast.makeText(SignupAction.this,"Password Not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new User(name.toString(), userName.toString(), email.toString(), password.toString());
                System.out.println(user.getUserName());
            }
        });


    }
}
