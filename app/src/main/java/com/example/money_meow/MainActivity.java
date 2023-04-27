package com.example.money_meow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import com.example.money_meow.account.Account;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.account.signup.SignupAction;
import com.example.money_meow.category.Category;
import com.example.money_meow.category.CategoryList;
import com.example.money_meow.database.MongoDBConnection;
import com.example.money_meow.database.RealmDB;
import com.example.money_meow.database.query.RealmQuery;
import com.example.money_meow.home.Home;
import com.example.money_meow.manageEngine.calculation.Calculation;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Realm.init(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button start_btn = findViewById(R.id.start_button);
        MongoDBConnection.connect();
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if (isLoggedIn) {
                    // Người dùng đã đăng nhập, cho phép truy cập vào các tính năng yêu cầu đăng nhập
                    String userName = sharedPreferences.getString("userName", "");
                    String name = sharedPreferences.getString("name", "");
                    String email = sharedPreferences.getString("email", "");
                    String password = sharedPreferences.getString("password", "");
                    Double amount = (double)sharedPreferences.getFloat("balance", 0);
                    LoginAccount.account = new Account(name,userName,email,password,amount);
                    // Lấy danh sách các Category từ cơ sở dữ liệu Realm
                    CategoryList.categories = RealmQuery.getDB(Category.class);
                    TransactionList.mainList = RealmQuery.getDB(Transaction.class);
                    TransactionList.sortDatesDescending();
                    LoginAccount.account.setBalance(Calculation.balanceCalc(LoginAccount.account.getBalance(),TransactionList.mainList));

                    intent.setClass(MainActivity.this, Home.class);
                } else {
                    // Người dùng chưa đăng nhập, yêu cầu đăng nhập trước khi truy cập vào các tính năng yêu cầu đăng nhập
                    intent.setClass(MainActivity.this, SignupAction.class);
                }

                startActivity(intent);
            }
        });

    }
}