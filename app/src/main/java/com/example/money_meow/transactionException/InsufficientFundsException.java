package com.example.money_meow.transactionException;

public class InsufficientFundsException extends TransactionException {
    public InsufficientFundsException(Double number) {
        super(String.format("Số dư tài khoản không đủ $%.2f để thực hiện giao dịch", number));
    }
}

