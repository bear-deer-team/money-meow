package com.example.money_meow.manageEngine.statistic;

import static java.lang.Math.max;

import android.graphics.Color;

import com.example.money_meow.category.Category;
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
    public static List<Transaction> totalByCategory = new ArrayList<>();
    public static List<Time> totalByTime = new ArrayList<>();

    private int month;
    private int year;

    public static final int[] GRAYTONE_COLORS = {
            Color.rgb(116, 161, 142), Color.rgb(129, 173, 181), Color.rgb(178, 200, 145),
            Color.rgb(228, 153, 105), Color.rgb(200, 134, 145), Color.rgb(173, 133, 186),
            Color.rgb(185, 156, 107), Color.rgb(251, 194, 127), Color.rgb(149, 161, 195),
            Color.rgb(148, 148, 148), Color.rgb(178, 178, 178)
    };

    public Graphic() {
        Calendar calendar = Calendar.getInstance();
        month = calendar.getTime().getMonth() + 1;
        year = calendar.getTime().getYear() + 1900;
        sourceList = Filter.getListByMonth(calendar.getTime().getMonth() + 1, calendar.getTime().getYear() + 1900);
        expenseList = Filter.getExpenseList(sourceList);
        incomeList = Filter.getIncomeList(sourceList);
        for (Transaction transaction : sourceList) {
            categorySet.add(transaction.getTransactionCategory().getCategoryName());
        }
    }

    public Graphic(int month, int year) {
        this.month = month;
        this.year = year;
        sourceList = Filter.getListByMonth(month, year);
        expenseList = Filter.getExpenseList(sourceList);
        incomeList = Filter.getIncomeList(sourceList);
        for (Transaction transaction : sourceList) {
            categorySet.add(transaction.getTransactionCategory().getCategoryName());
        }
    }

    public void setDataForPieChart(PieChart pieChartExpense, PieChart pieChartIncome) {
        totalByCategory.clear();

        List<PieEntry> entriesExpense = new ArrayList<>();
        List<PieEntry> entriesIncome = new ArrayList<>();

        for (String categoryName : categorySet) {
            double sum = 0;
            List<Transaction> listByCategory = Filter.getListByCategory(expenseList, categoryName);
            for (Transaction transaction : listByCategory) {
                sum += transaction.getTransactionAmount();
            }
            if (sum != 0) {
                entriesExpense.add(new PieEntry((float) sum, categoryName));
            }

            sum = 0;
            listByCategory = Filter.getListByCategory(incomeList, categoryName);
            for (Transaction transaction : listByCategory) {
                sum += transaction.getTransactionAmount();
            }
            if (sum != 0) {
                entriesIncome.add(new PieEntry((float) sum, categoryName));
            }
        }

        for (String categoryName : categorySet) {
            double sum = 0;
            List<Transaction> listByCategory = Filter.getListByCategory(sourceList, categoryName);
            for (Transaction transaction : listByCategory) {
                sum += transaction.getTransactionAmount();
            }
            if (sum != 0) {
                totalByCategory.add(new Transaction(sum, new Category(categoryName)));
            }
        }
        Collections.sort(totalByCategory, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction transaction1, Transaction transaction2) {
                // Sort entries in descending order by value percentage
                return Double.compare(transaction2.getTransactionAmount(), transaction1.getTransactionAmount());
            }
        });
        Collections.sort(entriesExpense, new Comparator<PieEntry>() {
            @Override
            public int compare(PieEntry entry1, PieEntry entry2) {
                // Sort entries in descending order by value percentage
                return Float.compare(entry2.getValue(), entry1.getValue());
            }
        });

        PieDataSet setIncome = new PieDataSet(entriesIncome, "Election Results");
        setIncome.setColors(GRAYTONE_COLORS);
        setIncome.setValueTextSize(10f);
        setIncome.setValueFormatter(new PercentFormatter(new DecimalFormat("#.#")));
        setIncome.setSliceSpace(3f);
        setIncome.setValueLinePart1OffsetPercentage(80f);
        setIncome.setValueLinePart2Length(0.5f);

        PieData dataIncome = new PieData(setIncome);

        pieChartIncome.setCenterText("Income");
        pieChartIncome.setData(dataIncome);
        pieChartIncome.setDrawEntryLabels(false);
        pieChartIncome.setUsePercentValues(true);
        pieChartIncome.getDescription().setEnabled(false);
        pieChartIncome.getLegend().setEnabled(false);
        pieChartIncome.invalidate();

        PieDataSet setExpense = new PieDataSet(entriesExpense, "Election Results");
        setExpense.setColors(GRAYTONE_COLORS);
        setExpense.setValueTextSize(10f);
        setExpense.setValueFormatter(new PercentFormatter(new DecimalFormat("#.#")));
        setExpense.setSliceSpace(3f);
        setExpense.setValueLinePart1OffsetPercentage(80f);
        setExpense.setValueLinePart2Length(0.5f);

        PieData dataExpense = new PieData(setExpense);

        pieChartExpense.setCenterText("Expense");
        pieChartExpense.setData(dataExpense);
        pieChartExpense.setDrawEntryLabels(false);
        pieChartExpense.setUsePercentValues(true);
        pieChartExpense.getDescription().setEnabled(false);
        pieChartExpense.getLegend().setEnabled(false);
        pieChartExpense.invalidate();


    }

    public void setDataForLineChart(LineChart lineChart) {
        totalByTime.clear();
        List<Entry> valsComp1 = new ArrayList<Entry>(32);
        List<Entry> valsComp2 = new ArrayList<Entry>(32);

        List<Double> incomeByDay = new ArrayList<>(Collections.nCopies(32, 0.0));
        List<Double> expenseByDay = new ArrayList<>(Collections.nCopies(32, 0.0));

        float maxDay = 0;
        for (int i = 0; i < incomeList.size(); i++) {
            Date time = incomeList.get(i).getTime();
            incomeByDay.set(time.getDate(), incomeList.get(i).getTransactionAmount());
            maxDay = max(maxDay, time.getDate());
        }

        for (int i = 0; i < expenseList.size(); i++) {
            Date time = expenseList.get(i).getTime();
            expenseByDay.set(time.getDate(), expenseList.get(i).getTransactionAmount());
            maxDay = max(maxDay, time.getDate());
        }


        float preIncome = 0;
        float preExpense = 0;
        for (int i = 1; i <= maxDay; i++) {
            float income = preIncome + incomeByDay.get(i).floatValue();
            float expense = preExpense + expenseByDay.get(i).floatValue();
            valsComp1.add(new Entry(i, income));
            valsComp2.add(new Entry(i, expense));
            if (incomeByDay.get(i) != 0 || expenseByDay.get(i) != 0) {
                totalByTime.add(new Time(Integer.toString(i) + "/"
                        + Integer.toString(month) + "/" + Integer.toString(year),
                        incomeByDay.get(i), expenseByDay.get(i)));
            }
            preIncome += incomeByDay.get(i);
            preExpense += expenseByDay.get(i);
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
        lineChart.getXAxis().setGranularity(1/maxDay);

        lineChart.setTouchEnabled(true);
        lineChart.setScaleEnabled(true);
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
