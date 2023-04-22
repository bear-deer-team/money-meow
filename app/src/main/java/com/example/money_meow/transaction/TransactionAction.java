package com.example.money_meow.transaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.R;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.category.Category;
import com.example.money_meow.home.Home;

import java.util.Date;


public class TransactionAction extends AppCompatActivity {
    private EditText datetime;
    private EditText amount;
    private Category category;
    private Transaction transaction;

    private Button returnBtn,close,open;
    private Button acptTransBtn;

    private LinearLayout categoryLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trans);

        categoryLayout = findViewById(R.id.categoryLayout);
        categoryLayout.setVisibility(View.GONE);
        open = findViewById(R.id.categoryBtn);
        close = findViewById(R.id.closeBtn);




        // tạm thời khởi tạo một category mặc định, chờ Vi hoàn thiện category
        category = new Category("buying", "extense");
        datetime = (EditText) findViewById(R.id.edit_text_date);
        amount = (EditText) findViewById(R.id.edit_text_amount);
        returnBtn = (Button) findViewById(R.id.ReturnHomeBtn);
        acptTransBtn = (Button) findViewById(R.id.AcptTransBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransactionAction.this, Home.class);
                startActivity(intent);
            }
        });
        acptTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = TransactionValidation.getDatetime(datetime);
                if(TransactionValidation.isTransactionAmountInvalid(amount, category)
                | date == null) {
                    return;
                }
                double transactionAmount = Double.parseDouble(amount.getEditableText().toString());
                transaction = new Transaction(category, transactionAmount,
                        LoginAccount.account.getUserName(), date, "demo");
                // transaction.saveToDatabase();

                if(category.getCategoryType().equals("extense")) {
                    LoginAccount.account.getBalance().subtract(transactionAmount);
                } else {
                    LoginAccount.account.getBalance().add(transactionAmount);
                }

                Toast.makeText(getApplicationContext(), "Add Transaction Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TransactionAction.this, Home.class);
                startActivity(intent);
            }
        });

        open.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                categoryLayout.setVisibility(View.VISIBLE);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        categoryLayout.setVisibility(View.GONE);
                    }
                });
            }
        });

    }
}
