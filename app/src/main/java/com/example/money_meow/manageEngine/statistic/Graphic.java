package com.example.money_meow.manageEngine.statistic;

import android.graphics.Color;

import com.example.money_meow.manageEngine.filter.Filter;
import com.example.money_meow.transaction.Transaction;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graphic {
    public List<Transaction> sourceList = new ArrayList<>();
    public List<Transaction> expenseList = new ArrayList<>();
    public List<Transaction> incomeList = new ArrayList<>();
    public Set<String> categorySet = new HashSet<>();

    public Graphic() {
        sourceList = Filter.getListByCurrentMonth();
        expenseList = Filter.getExpenseList(sourceList);
        incomeList = Filter.getIncomeList(sourceList);
        for (Transaction transaction : sourceList) {
            categorySet.add(transaction.getTransactionCategory().getCategoryName());
        }
    }
    public void setDataForPieChart(PieChart pieChart) {

        List<PieEntry> entries = new ArrayList<>();

        for (String categoryName : categorySet) {
            double sum = 0;
            List<Transaction> listByCategory = Filter.getListByCategory(expenseList, categoryName);
            for (Transaction transaction : listByCategory) {
                sum += transaction.getTransactionAmount();
            }
            entries.add(new PieEntry((float) sum, categoryName));
        }

        PieDataSet set = new PieDataSet(entries, "Election Results");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setValueTextSize(20f);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();
    }

    public static void setDataForLineChart(LineChart lineChart) {
        List<Entry> valsComp1 = new ArrayList<Entry>();
        List<Entry> valsComp2 = new ArrayList<Entry>();

        Entry c1e1 = new Entry(0f, 100000f); // 0 == quarter 1
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(1f, 140000f); // 1 == quarter 2 ...
        valsComp1.add(c1e2);

        Entry c2e1 = new Entry(0f, 130000f); // 0 == quarter 1
        valsComp2.add(c2e1);
        Entry c2e2 = new Entry(1f, 115000f); // 1 == quarter 2 ...
        valsComp2.add(c2e2);

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Income");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColors(Color.GREEN);
        setComp1.setValueTextSize(10f);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "Extense");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColors(Color.RED);
        setComp2.setValueTextSize(10f);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setDrawGridLines(false); // Disable grid lines on X axis
        lineChart.getAxisLeft().setDrawGridLines(false); // Disable grid lines on Y axis
        lineChart.getXAxis().setDrawAxisLine(false); // Disable X axis bar
        lineChart.getAxisLeft().setDrawAxisLine(false); // Disable Y axis bar
        lineChart.invalidate();
    }
}
