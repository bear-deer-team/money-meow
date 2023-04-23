package com.example.money_meow.account.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.money_meow.R;
import com.example.money_meow.account.Account;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.account.signup.SignupAction;
import com.example.money_meow.category.CategoryList;
import com.example.money_meow.database.CategoryQuery;
import com.example.money_meow.database.MongoDBConnection;
import com.example.money_meow.database.RealmDB;
import com.example.money_meow.database.TransactionQuery;
import com.example.money_meow.home.Home;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionList;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Timer;
import java.util.TimerTask;

public class LoginAction extends AppCompatActivity {

    private Account account;

    TextInputLayout username;
    TextInputLayout password;

    Button signupBtn;
    Button signinBtn;

    ProgressBar progressBar;
    ConstraintLayout loading;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (TextInputLayout) findViewById(R.id.user_name);
        password = (TextInputLayout) findViewById(R.id.password);

        signinBtn = (Button) findViewById(R.id.login_btn);
        signupBtn = (Button) findViewById(R.id.signup_btn);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        loading = (ConstraintLayout) findViewById(R.id.loadingFrame);


        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(MongoDBConnection.getApp().currentUser());
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
                                if (LoginValidation.isUsernameValid(username) ) {
                                    password.getEditText().setText("");
                                    progressBar.setVisibility(View.GONE);
                                    loading.setVisibility(View.GONE);
                                    return;
                                } else {
                                    if (LoginValidation.isPasswordValid(password, username)) {
                                        progressBar.setVisibility(View.GONE);
                                        loading.setVisibility(View.GONE);
                                        return;
                                    }
                                }
                                progressBar.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                                String userName = username.getEditText().getText().toString();

                                LoginAccount.getAcc(userName);

                                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putBoolean("isLoggedIn", true);
                                editor.putString("name",LoginAccount.account.getName());
                                editor.putString("userName",LoginAccount.account.getUserName());
                                editor.putString("email",LoginAccount.account.getEmail());
                                editor.putString("password",LoginAccount.account.getPassword());
                                editor.putFloat("balance",(float)LoginAccount.account.getBalance().getAmount());
                                editor.apply();

                                Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_SHORT).show();

                                RealmDB.configFile();
                                CategoryList.categories = CategoryQuery.getCategoryList();
                                TransactionList.mainList = TransactionQuery.FindByUserName(userName);
                                RealmDB.addToRealm(CategoryList.categories);
                                RealmDB.addToRealm(TransactionList.mainList);

                                Intent intent = new Intent(LoginAction.this, Home.class);
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

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAction.this, SignupAction.class);
                startActivity(intent);
            }
        });


    }
}
