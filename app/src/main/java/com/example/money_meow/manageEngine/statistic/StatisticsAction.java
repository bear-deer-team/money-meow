package com.example.money_meow.manageEngine.statistic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ViewFlipper;


import androidx.core.util.Pair;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.home.Home;
import com.example.money_meow.manageEngine.filter.Filter;
import com.example.money_meow.manageEngine.searchEngine.SearchEngine;
import com.example.money_meow.manageEngine.searchEngine.TransactionAdapter;
import com.example.money_meow.setting.Settings;
import com.example.money_meow.transaction.TransactionAction;
import com.example.money_meow.transaction.TransactionList;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAction extends BaseActivity {
    private final String DEFALT_CHOOSE = "Choose your time";
    private RecyclerView rcvTransList;
    private CategoryAdapter categoryAdapter;
    private Button addTransBtn, homeBtn, historyBtn, searchBtn, settingBtn;
    private Button byTimeBtn, byCategoryBtn, byBothBtn;

    private Graphic graphic;

    ViewFlipper viewFlipper1, viewFlipper2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
        graphic = new Graphic();

        homeBtn = (Button) findViewById(R.id.HomeBtn);
        historyBtn = (Button) findViewById(R.id.HistoryBtn);
        addTransBtn = (Button) findViewById(R.id.AddTransBtn);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        settingBtn = (Button) findViewById(R.id.SettingBtn);

        byTimeBtn = (Button) findViewById(R.id.ByTimeBtn);
        byCategoryBtn = (Button) findViewById(R.id.ByCategoryBtn);
        byBothBtn = (Button) findViewById(R.id.ByBothBtn);

        rcvTransList = findViewById(R.id.c_details_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcvTransList.setLayoutManager(gridLayoutManager);


        viewFlipper1 = findViewById(R.id.view_flipper1);
        viewFlipper1.setDisplayedChild(0);

        viewFlipper2 = findViewById(R.id.view_flipper2);
        PieChart pieChart = findViewById(R.id.piechart);
        LineChart lineChart = findViewById(R.id.linechart);
        graphic.setDataForPieChart(pieChart);
        viewFlipper2.setDisplayedChild(0);

        categoryAdapter = new CategoryAdapter(Graphic.totalByCategory,this);
        rcvTransList.setAdapter(categoryAdapter);

        List<String> items = Filter.getRangeTime(TransactionList.mainList);
        items.add(DEFALT_CHOOSE);

        Spinner timeList = (Spinner) findViewById(R.id.timeList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.time_list_item, items);

        timeList.setAdapter(adapter);
        timeList.setSelection(items.size() - 1);

        timeList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String time = timeList.getSelectedItem().toString();
                if (!time.equals(DEFALT_CHOOSE)) {
                    int year = Integer.parseInt(time.substring(0, 4));
                    int month = Integer.parseInt(time.substring(5));
                    graphic = new Graphic(month, year);
                    graphic.setDataForPieChart(pieChart);
                    graphic.setDataForLineChart(lineChart);

                    categoryAdapter = new CategoryAdapter(Graphic.totalByCategory,StatisticsAction.this);
                    rcvTransList.setAdapter(categoryAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        byCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper1.setDisplayedChild(0);
                PieChart pieChart = findViewById(R.id.piechart);
                graphic.setDataForPieChart(pieChart);
                viewFlipper2.setDisplayedChild(0);
            }
        });

        byTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper1.setDisplayedChild(1);
                graphic.setDataForLineChart(lineChart);
                viewFlipper2.setDisplayedChild(1);
            }
        });

        byBothBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper1.setDisplayedChild(2);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsAction.this, Home.class);
                startActivity(intent);
            }
        });

        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsAction.this, TransactionAction.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsAction.this, SearchEngine.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsAction.this, Settings.class);
                startActivity(intent);
            }
        });
    }

}
