package com.example.money_meow.manageEngine.searchEngine;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.category.CategoryList;
import com.example.money_meow.home.HistoryListForHome;
import com.example.money_meow.home.Home;
import com.example.money_meow.transaction.CategoryAdapter;
import com.example.money_meow.transaction.Transaction;
import com.example.money_meow.transaction.TransactionAction;
import com.example.money_meow.transaction.TransactionList;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine extends BaseActivity {
    private RecyclerView rcvTransList;

    private String searchValue;
    public static LinearLayout searchTransLayout;

    private TransactionAdapter transactionAdapter;
    private EditText searchText;
    private Button returnBtn;
    public static ImageView searchImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching);

        //searchTransLayout = findViewById(R.id.searchTransLayout);

        rcvTransList = findViewById(R.id.TransactionList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcvTransList.setLayoutManager(gridLayoutManager);

//        transactionAdapter = new TransactionAdapter(getList(searchValue),this);
//        rcvTransList.setAdapter(transactionAdapter);

        searchText = (EditText) findViewById(R.id.edit_text_search);

        searchImg = findViewById(R.id.imageSearch);

        returnBtn = findViewById(R.id.ReturnHomeBtn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchEngine.this, Home.class);
                startActivity(intent);
            }
        });
        // Lắng nghe sự kiện nhập từ khóa tìm kiếm vào EditText
//        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    searchValue = searchText.getText().toString().trim();
//                    transactionAdapter.updateList(getList(searchValue));
//                    return true;
//                }
//                return false;
//            }
//        });
    }
//    private List<Transaction> getList(String keyword) {
//        List<Transaction> res = new ArrayList<>();
//        for (Transaction transaction : TransactionList.mainList) {
//            if (transaction.getTransactionCategory().getCategoryName().contains(keyword)) {
//                res.add(transaction);
//            }
//        }
//        return res;
//    }

//    private List<Transaction> filterList(List<Transaction> transactionList, String keyword) {
//        List<Transaction> res = new ArrayList<>();
//        for (Transaction transaction : transactionList) {
//            if (transaction.getTransactionCategory().getCategoryName().contains(keyword)) {
//                res.add(transaction);
//            }
//        }
//        return res;
//    }

}
