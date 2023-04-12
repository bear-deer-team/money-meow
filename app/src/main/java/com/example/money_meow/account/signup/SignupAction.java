package com.example.money_meow.account.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.R;
import com.example.money_meow.database.MongoDBConnection;
import com.example.money_meow.account.Account;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.mongodb.mongo.MongoCollection;

public class SignupAction extends AppCompatActivity {
    private Account account;
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



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = new Account(name.getText().toString(), userName.getText().toString(), email.getText().toString(), password.getText().toString());
                MongoDBConnection.connect(SignupAction.this);
                account.addNewUserToDB(MongoDBConnection.getApp());
            }
        });


    }
}
