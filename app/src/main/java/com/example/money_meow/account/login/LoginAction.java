package com.example.money_meow.account.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.MainActivity;
import com.example.money_meow.R;
import com.example.money_meow.account.Account;
import com.example.money_meow.account.signup.SignupAction;
import com.example.money_meow.database.MongoDBConnection;
import com.google.android.material.textfield.TextInputLayout;

public class LoginAction extends AppCompatActivity {

    private Account account;

    TextInputLayout username;
    TextInputLayout password;

    Button signupBtn;
    Button signinBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (TextInputLayout) findViewById(R.id.user_name);
        password = (TextInputLayout) findViewById(R.id.password);

        signinBtn = (Button) findViewById(R.id.login_btn);
        signupBtn = (Button) findViewById(R.id.signup_btn);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MongoDBConnection.connect();

                if (LoginValidation.isUsernameValid(username) ) {
                    password.getEditText().setText("");
                    return;
                } else {
                    if (LoginValidation.isPasswordValid(password, username)) {
                        return;
                    }
                }

                System.out.println("Login!");
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAction.this, SignupAction.class);
                startActivity(intent);
            }
        });


    }
}
