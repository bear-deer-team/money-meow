package com.example.money_meow.account.signup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.money_meow.R;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.account.PasswordEncryption;
import com.example.money_meow.account.login.LoginAction;
import com.example.money_meow.account.Account;
import com.example.money_meow.category.CategoryList;
import com.example.money_meow.database.RealmDB;
import com.example.money_meow.database.insert.RealmInsert;
import com.example.money_meow.database.query.CategoryQuery;
import com.example.money_meow.database.query.TransactionQuery;
import com.example.money_meow.home.Home;
import com.example.money_meow.manageEngine.calculation.Calculation;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionList;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class SignupAction extends AppCompatActivity {
    private Account account;
    TextInputLayout name;
    TextInputLayout userName;
    TextInputLayout password;
    TextInputLayout confirmPassword;
    TextInputLayout email;


    Button signUpBtn;
    Button loginNavig;

    ProgressBar progressBar;
    ConstraintLayout loading;


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

        progressBar = (ProgressBar) findViewById(R.id.progress);
        loading = (ConstraintLayout) findViewById(R.id.loadingFrame);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread uiThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });

                Thread mainThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(AccountValidation.isNameInvalid(name)
                                        | AccountValidation.isUserNameInvalid(userName)
                                        | AccountValidation.isEmailInvalid(email)
                                        | AccountValidation.isPasswordInvalid(password)
                                        | AccountValidation.isConfirmPasswordInvalid(confirmPassword, password)) {
                                    password.getEditText().setText("");
                                    confirmPassword.getEditText().setText("");
                                    progressBar.setVisibility(View.GONE);
                                    loading.setVisibility(View.GONE);
                                    return;
                                }
                                progressBar.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                                account = new Account(name.getEditText().getText().toString(), userName.getEditText().getText().toString(),
                                        email.getEditText().getText().toString(), PasswordEncryption.encrypt(password.getEditText().getText().toString()));
                                account.addNewUserToDB();
                                LoginAccount.account = account;

                                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putBoolean("isLoggedIn", true);
                                editor.putString("name",LoginAccount.account.getName());
                                editor.putString("userName",LoginAccount.account.getUserName());
                                editor.putString("email",LoginAccount.account.getEmail());
                                editor.putString("password",LoginAccount.account.getPassword());
                                editor.putFloat("balance",LoginAccount.account.getBalance().floatValue());
                                editor.apply();

                                Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_SHORT).show();

                                RealmDB.configFile();
                                CategoryList.categories = CategoryQuery.getCategoryList();
                                TransactionList.mainList = new ArrayList<Transaction>();
                                RealmInsert.insertMany(CategoryList.categories);
                                RealmInsert.insertMany(TransactionList.mainList);
                                //LoginAccount.account.setBalance(Calculation.balanceCalc(LoginAccount.account.getBalance(),TransactionList.mainList));

                                Toast.makeText(getApplicationContext(), "Sign Up Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupAction.this, Home.class).putExtra("from", "Signup");
                                startActivity(intent);
                            }
                        });
                    }
                });
                uiThread.start();
                try {
                    uiThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mainThread.start();
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
