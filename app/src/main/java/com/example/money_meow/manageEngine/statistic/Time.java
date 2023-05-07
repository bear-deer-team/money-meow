package com.example.money_meow.manageEngine.statistic;


public class Time {
    String date = new String();
    double income = 0;
    double expense = 0;
    double total = 0;

    public Time(String date, double income, double expense) {
        this.date = date;
        this.income = income;
        this.expense = expense;
        this.total = income - expense;
    }

    public String getDate() {
        return date;
    }

    public double getIncome() {
        return this.income;
    }

    public double getExpense() {
        return this.expense;
    }

    public double getTotal() {
        return this.total;
    }
}
