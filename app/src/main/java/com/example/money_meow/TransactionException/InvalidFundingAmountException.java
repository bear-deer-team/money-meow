package com.example.money_meow.TransactionException;

public class InvalidFundingAmountException extends TransactionException {
    public InvalidFundingAmountException(Double number) {
        super(String.format("Số tiền không hợp lệ: $%.2f", number));
    }
}
