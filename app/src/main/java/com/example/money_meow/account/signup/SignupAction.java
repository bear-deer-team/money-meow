package com.example.money_meow.account.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.MainActivity;
import com.example.money_meow.R;
import com.example.money_meow.account.login.LoginAction;
import com.example.money_meow.database.MongoDBConnection;
import com.example.money_meow.account.Account;
import com.google.android.material.textfield.TextInputLayout;


public class SignupAction extends AppCompatActivity {
    private Account account;
    TextInputLayout name;
    TextInputLayout userName;
    TextInputLayout password;
    TextInputLayout confirmPassword;
    TextInputLayout email;


    Button signUpBtn;
    Button loginNavig;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name = (TextInputLayout) findViewById(R.id.name);
        userName = (TextInputLayout) findViewById(R.id.user_name);
        password = (TextInputLayout) findViewById(R.id.password);
        confirmPassword = (TextInputLayout) findViewById(R.id.cf_password);
        email = (TextInputLayout) findViewById(R.id.email);


        signUpBtn = (Button) findViewById(R.id.signup_btn);
        loginNavig = (Button) findViewById(R.id.login_btn);



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MongoDBConnection.connect(SignupAction.this);

                if(AccountValidation.isNameInvalid(name)
                | AccountValidation.isUserNameInvalid(userName)
                | AccountValidation.isEmailInvalid(email)
                | AccountValidation.isPasswordInvalid(password)
                | AccountValidation.isConfirmPasswordInvalid(confirmPassword, password)) {
                    return;
                }

                account = new Account(name.getEditText().getText().toString(), userName.getEditText().getText().toString(),
                        email.getEditText().getText().toString(), password.getEditText().getText().toString());
                account.addNewUserToDB();
            }
        });

        loginNavig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupAction.this, LoginAction.class);
                startActivity(intent);
            }
        });


    }
}
