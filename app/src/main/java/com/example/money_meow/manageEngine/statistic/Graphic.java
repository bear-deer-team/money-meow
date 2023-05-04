package com.example.money_meow.manageEngine.statistic;

import android.graphics.Color;

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
import java.util.List;

public class Graphic {
    public static void setDataForPieChart(PieChart pieChart) {
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

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
