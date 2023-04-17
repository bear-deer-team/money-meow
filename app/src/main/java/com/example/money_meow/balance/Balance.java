package com.example.money_meow.balance;

public class Balance {

    private double amount;
    public Balance() {
        amount = 0;
    }

    public Balance(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void add(double amount) {
        this.amount += amount;
    }

    public void subtract(double amount) {
        this.amount -= amount;
    }
}
