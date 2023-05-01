package com.example.money_meow.manageEngine.calculation;

import static org.junit.jupiter.api.Assertions.*;

import com.example.money_meow.transaction.Transaction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculationTest {
    @Test
    public void emptyTransactionList() {
        assertEquals(0.0, Calculation.balanceCalc(0.0, new ArrayList<>()));
    }

    @Test
    public void subtractTransactionList() {
        Transaction transaction = new Transaction(10.0, "extense");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        assertEquals(-10.0, Calculation.balanceCalc(0.0, transactionList));
    }

    @Test
    public void additionTransactionList() {
        Transaction transaction = new Transaction(10.0, "income");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        assertEquals(10.0, Calculation.balanceCalc(0.0, transactionList));
    }

    @Test
    public void multiTypeTransaction() {
        Transaction transaction1 = new Transaction(10.0, "income");
        Transaction transaction2 = new Transaction(20.0, "extense");
        Transaction transaction3 = new Transaction(10.0, "income");
        Transaction transaction4 = new Transaction(20.0, "extense");
        Transaction transaction5 = new Transaction(10.0, "income");
        Transaction transaction6 = new Transaction(20.0, "extense");
        Transaction transaction7 = new Transaction(10.0, "income");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        transactionList.add(transaction3);
        transactionList.add(transaction4);
        transactionList.add(transaction5);
        transactionList.add(transaction6);
        transactionList.add(transaction7);

        assertEquals(80, Calculation.balanceCalc(100.0, transactionList));
    }

}