package com.example.money_meow.transaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.category.Category;
import com.example.money_meow.category.CategoryList;
import com.example.money_meow.home.Home;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.Date;


public class TransactionAction extends BaseActivity {

    private EditText datetime;
    private EditText amount;
    public static Category category;
    private Transaction transaction;

    private Button returnBtn;
    private Button close;
    private Button open;
    private Button deleteBtn;
    private Button acptTransBtn;
    private Button note;

    public static ConstraintLayout categoryLayout;

    public TabLayout categoryTabLayout;

    private RecyclerView rcvCategory;

    public static ImageView cateImg;
    public static TextView cateName;

    public static Transaction trans;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trans);

        categoryLayout = findViewById(R.id.categoryLayout);
        categoryLayout.setVisibility(View.GONE);
        open = findViewById(R.id.categoryBtn);
        close = findViewById(R.id.closeBtn);

        categoryTabLayout = categoryLayout.findViewById(R.id.tabLayout);
        TabLayout.Tab incomeTab = categoryTabLayout.getTabAt(0);
        TabLayout.Tab expenseTab = categoryTabLayout.getTabAt(1);

        rcvCategory = categoryLayout.findViewById(R.id.CategoryList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this ,3);
        rcvCategory.setLayoutManager(gridLayoutManager);

        CategoryAdapter categoryAdapter = new CategoryAdapter(CategoryList.categories,this);


        // tạm thời khởi tạo một category mặc định, chờ Vi hoàn thiện category
        category = new Category("buying", "extense");
        datetime = (EditText) findViewById(R.id.edit_text_date);
        amount = (EditText) findViewById(R.id.edit_text_amount);
        returnBtn = (Button) findViewById(R.id.ReturnHomeBtn);
        acptTransBtn = (Button) findViewById(R.id.AcptTransBtn);
        deleteBtn = (Button) findViewById(R.id.DeleteBtn);
        deleteBtn.setVisibility(View.GONE);

        note = (Button) findViewById(R.id.note);
        note.setTransformationMethod(null);

        cateImg = findViewById(R.id.imageView2);
        cateName = findViewById(R.id.categoryText);
        cateImg.setImageResource(category.getImage(this));
        cateName.setText(category.getCategoryName());

        if(trans!=null){
            deleteBtn.setVisibility(View.VISIBLE);
            cateImg.setImageResource(trans.getTransactionCategory().getImage(this));
            cateName.setText(trans.getTransactionCategory().getCategoryName());
            category = trans.getTransactionCategory();
            datetime.setText(trans.formatDate());
            amount.setText(Double.toString(trans.getTransactionAmount()));
            note.setText(trans.getTransactionNote());
        }

        categoryAdapter.filterCategoryList("income");
        rcvCategory.setAdapter(categoryAdapter);
        if (incomeTab != null) {
            incomeTab.select();
            incomeTab.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categoryAdapter.filterCategoryList("income");
                }
            });
        }

        if (expenseTab != null) {
            expenseTab.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categoryAdapter.filterCategoryList("extense");
                }
            });
        }
        rcvCategory.setAdapter(categoryAdapter);

        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        TransactionAction.this,
                        mDateSetListener,
                        year,
                        month,
                        day);
                dialog.show();
            }
        });
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trans = null;
                Intent intent = new Intent(TransactionAction.this, Home.class);
                startActivity(intent);

            }
        });

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TransactionAction.this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.transaction_note);
        EditText noteText = bottomSheetDialog.findViewById(R.id.note);
        Button submit = bottomSheetDialog.findViewById(R.id.showBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setText(noteText.getText());
                bottomSheetDialog.dismiss();
            }
        });
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

        acptTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = TransactionValidation.getDatetime(datetime);
                String note = noteText.getText().toString();
                if(TransactionValidation.isTransactionAmountInvalid(amount, category)
                        | date == null) {
                    return;
                }
                double transactionAmount = Double.parseDouble(amount.getText().toString());
                if(trans != null){
                    transaction = new Transaction(trans.getId(),category.getCategoryName(),transactionAmount,
                            LoginAccount.account.getUserName(),date,note,category.getCategoryType());
                }else {
                    transaction = new Transaction(category, transactionAmount,
                            LoginAccount.account.getUserName(), date, note);
                }
                if(transaction.getTransactionType().equals("income")){
                    LoginAccount.account.setBalance(
                            LoginAccount.account.getBalance()+transactionAmount);
                }else{
                    LoginAccount.account.setBalance(
                            LoginAccount.account.getBalance()-transactionAmount);
                }
                if(trans == null) {
                    TransactionList.add(transaction);
                    TransactionList.sortDatesDescending(TransactionList.mainList);
                    Toast.makeText(getApplicationContext(), "Add Transaction Successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    TransactionList.update(transaction);
                    Toast.makeText(getApplicationContext(), "Update Transaction Successfully!", Toast.LENGTH_SHORT).show();
                }
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

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransactionList.delete(trans);
                Toast.makeText(getApplicationContext(), "Delete Transaction Successfully!", Toast.LENGTH_SHORT).show();
                trans = null;
                Intent intent = new Intent(TransactionAction.this, Home.class);
                startActivity(intent);
            }
        });

    }
    public DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    datetime.setText(selectedDate);
                }
            };
}