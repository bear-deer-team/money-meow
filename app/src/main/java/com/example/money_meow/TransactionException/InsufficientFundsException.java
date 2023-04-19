package com.example.money_meow.TransactionException;

public class InsufficientFundsException extends TransactionException {
    public InsufficientFundsException(Double number) {
        super(String.format("Số dư tài khoản không đủ $%.2f để thực hiện giao dịch", number));
    }
}

