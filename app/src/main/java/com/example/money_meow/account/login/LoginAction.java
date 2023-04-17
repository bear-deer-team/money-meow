package com.example.money_meow.account.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.MainActivity;
import com.example.money_meow.R;
import com.example.money_meow.account.Account;
import com.example.money_meow.account.signup.SignupAction;
import com.example.money_meow.database.MongoDBConnection;
import com.example.money_meow.home.Home;
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


        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(MongoDBConnection.getApp().currentUser());

                if (LoginValidation.isUsernameValid(username) ) {
                    password.getEditText().setText("");
                    return;
                } else {
                    if (LoginValidation.isPasswordValid(password, username)) {
                        return;
                    }
                }

                Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginAction.this, Home.class);
                startActivity(intent);
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
