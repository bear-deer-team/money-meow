package com.example.money_meow.manageEngine.statistic;

import static java.lang.Math.max;

import android.graphics.Color;

import com.example.money_meow.manageEngine.filter.Filter;
import com.example.money_meow.transaction.Transaction;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graphic {
    public List<Transaction> sourceList = new ArrayList<>();
    public List<Transaction> expenseList = new ArrayList<>();
    public List<Transaction> incomeList = new ArrayList<>();
    public Set<String> categorySet = new HashSet<>();

    public static final int[] GRAYTONE_COLORS = {
            Color.rgb(116, 161, 142), Color.rgb(129, 173, 181), Color.rgb(178, 200, 145),
            Color.rgb(228, 153, 105), Color.rgb(200, 134, 145), Color.rgb(173, 133, 186),
            Color.rgb(185, 156, 107), Color.rgb(251, 194, 127), Color.rgb(149, 161, 195),
            Color.rgb(148, 148, 148), Color.rgb(178, 178, 178)
    };

    public Graphic() {
        Calendar calendar = Calendar.getInstance();
        sourceList = Filter.getListByMonth(calendar.getTime().getMonth() + 1, calendar.getTime().getYear() + 1900);
        expenseList = Filter.getExpenseList(sourceList);
        incomeList = Filter.getIncomeList(sourceList);
        for (Transaction transaction : sourceList) {
            categorySet.add(transaction.getTransactionCategory().getCategoryName());
        }
    }

    public Graphic(int month, int year) {
        sourceList = Filter.getListByMonth(month, year);
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
            if (sum != 0) {
                entries.add(new PieEntry((float) sum, categoryName));
            }
        }
        Collections.sort(entries, new Comparator<PieEntry>() {
            @Override
            public int compare(PieEntry entry1, PieEntry entry2) {
                // Sort entries in descending order by value percentage
                return Float.compare(entry2.getValue(), entry1.getValue());
            }
        });

        PieDataSet set = new PieDataSet(entries, "Election Results");
        set.setColors(GRAYTONE_COLORS);
        set.setValueTextSize(20f);
        set.setValueFormatter(new PercentFormatter(new DecimalFormat("#.#")));
        set.setSliceSpace(3f);
        set.setValueLinePart1OffsetPercentage(80f);
        set.setValueLinePart2Length(0.5f);
        PieData data = new PieData(set);

        pieChart.setData(data);
        pieChart.setDrawEntryLabels(false);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();
    }

    public void setDataForLineChart(LineChart lineChart) {
        List<Entry> valsComp1 = new ArrayList<Entry>(31);
        List<Entry> valsComp2 = new ArrayList<Entry>(31);

        List<Double> incomeByDay = new ArrayList<>(Collections.nCopies(31, 0.0));
        List<Double> expenseByDay = new ArrayList<>(Collections.nCopies(31, 0.0));



        float maxDay = 0;
        for (int i = 1; i < incomeList.size(); i++) {
            Date time = incomeList.get(i).getTime();
            incomeByDay.set(time.getDate(), incomeList.get(i).getTransactionAmount());
            maxDay = max(maxDay, time.getDate());
        }

        for (int i = 1; i < incomeByDay.size(); i++) {
            double presum = incomeByDay.get(i - 1);
            incomeByDay.set(i, incomeByDay.get(i) + presum);

        }

        for (int i = 1; i < expenseList.size(); i++) {
            Date time = expenseList.get(i).getTime();
            expenseByDay.set(time.getDate(), expenseList.get(i).getTransactionAmount());
            maxDay = max(maxDay, time.getDate());
        }

        for (int i = 1; i < expenseByDay.size(); i++) {
            double presum = expenseByDay.get(i - 1);
            expenseByDay.set(i, expenseByDay.get(i) + presum);
        }

        for (int i = 1; i <= maxDay; i++) {
            valsComp1.add(new Entry(i / maxDay, incomeByDay.get(i).floatValue()));
        }

        for (int i = 1; i <= maxDay; i++) {
            valsComp2.add(new Entry(i / maxDay, expenseByDay.get(i).floatValue()));
        }

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Income");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColors(Color.GREEN);
        setComp1.setValueTextSize(0f);

        LineDataSet setComp2 = new LineDataSet(valsComp2, "Expense");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColors(Color.RED);
        setComp2.setValueTextSize(0f);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);

        lineChart.setTouchEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(true);

        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setDrawGridLines(false); // Disable grid lines on X axis
        lineChart.getAxisLeft().setDrawGridLines(false); // Disable grid lines on Y axis
        lineChart.getXAxis().setDrawAxisLine(false); // Disable X axis bar
        lineChart.getAxisLeft().setDrawAxisLine(false); // Disable Y axis bar

        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setEnabled(false);
        lineChart.invalidate();



    }
}
