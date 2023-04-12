package com.example.money_meow.balance;

public class Balance {

    private double amount;
    public Balance() {
        amount = 0;
    }

    public double getAmount() {
        return amount;
    }

    public Balance(double amount) {
        this.amount = amount;
    }

    public void add(double amount) {
        this.amount += amount;
    }

    public void subtract(double amount) {
        this.amount -= amount;
    }
}
