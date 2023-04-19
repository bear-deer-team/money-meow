package com.example.money_meow.transaction;

import android.widget.EditText;

import com.example.money_meow.transactionException.InsufficientFundsException;
import com.example.money_meow.transactionException.InvalidFundingAmountException;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.category.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionValidation {
    private void checkAmount(double amount, Category category) throws InvalidFundingAmountException, InsufficientFundsException {
        if (amount < 0) {
            throw new InvalidFundingAmountException(new Double(amount));
        }
        if (amount > LoginAccount.account.getBalance().getAmount() && category.getCategoryType().equals("intense")) {
            throw new InsufficientFundsException(new Double(amount));
        }
    }

    public boolean isTransactionAmountValid(EditText valInput, Category category) {
        String val = valInput.getEditableText().toString();
        try {
            double value = Double.parseDouble(val);
            try {
                this.checkAmount(value, category);
                return true;
            } catch (InvalidFundingAmountException e) {
                valInput.setError("Invalid funding. Your transaction amount is negative.");
                return false;
            } catch (InsufficientFundsException e) {
                valInput.setError("Insufficient fund. Your transaction amount exceeds your account balance.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Number format exception");
            return false;
        }
    }

    public boolean isDatetimeValid (EditText dateTimeInput) {
        String datetime = dateTimeInput.getEditableText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // đặt giá trị lenient là false để bắt buộc kiểm tra tính hợp lệ

        try {
            Date date = dateFormat.parse(datetime);
            System.out.println("Valid datetime.");
        } catch (ParseException e) {
            dateTimeInput.setError("Invalid datetime, make sure to follow DD/MM/YYYY format.");
            System.out.println("Invalid datetime!");
        }
        return true;
    }
}
