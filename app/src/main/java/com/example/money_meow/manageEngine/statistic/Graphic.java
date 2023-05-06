package com.example.money_meow.manageEngine.statistic;

import android.graphics.Color;

import com.example.money_meow.manageEngine.filter.Filter;
import com.example.money_meow.transaction.Transaction;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.LegendEntry;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
