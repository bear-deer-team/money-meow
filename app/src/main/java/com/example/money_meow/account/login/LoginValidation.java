package com.example.money_meow.account.login;

import com.example.money_meow.account.PasswordEncryption;
import com.example.money_meow.database.MongoDBQuery;
import com.google.android.material.textfield.TextInputLayout;

import org.bson.Document;

public class LoginValidation {
    public static boolean isUsernameValid (TextInputLayout valInput) {
        String val = valInput.getEditText().getText().toString();

        if (!MongoDBQuery.isExist("MoneyMeow", "users", new Document().append("userName", val))) {
            valInput.setError("User Name is not existed!");
            return true;
        } else {
            valInput.setError(null);
            return false;
        }
    }

    public static boolean isPasswordValid (TextInputLayout valInput, TextInputLayout username) {
        String val = valInput.getEditText().getText().toString();

        Document userInfor = MongoDBQuery.queryOne("MoneyMeow", "users", new Document("userName", username.getEditText().getText().toString()));

        if (!PasswordEncryption.encrypt(val).equals(userInfor.getString("password"))) {
            valInput.setError("Password is incorrect!");
            return true;
        } else {
            valInput.setError(null);
            return false;
        }
    }


}
