package com.example.money_meow.transaction;

import android.widget.EditText;

import com.example.money_meow.TransactionException.InsufficientFundsException;
import com.example.money_meow.TransactionException.InvalidFundingAmountException;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.category.Category;

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
            System.out.println("Không thể chuyển đổi giá trị sang kiểu double");
            return false;
        }
    }
}
