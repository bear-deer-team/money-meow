package com.example.money_meow.manageEngine.calculation;

import com.example.money_meow.account.Account;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.transaction.Transaction;

import java.util.List;

public class Calculation {
    public static double balanceCalc(Double balance, List<Transaction> transactions){
        for (int i=0;i<transactions.size();i++){
            if(transactions.get(i).getTransactionType().equals("income")){
                balance+=transactions.get(i).getTransactionAmount();
            }
            else{
                balance-=transactions.get(i).getTransactionAmount();
            }
        }
        balance = (double) Math.round(balance * 100) / 100;
        return balance;
    }

}
