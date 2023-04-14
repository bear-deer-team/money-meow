package com.example.money_meow.account.signup;

import com.google.android.material.textfield.TextInputLayout;

public class AccountValidation {
    public static boolean isNameInvalid(TextInputLayout valInput) {
        String val = valInput.getEditText().getText().toString();
        if (val.isEmpty()) {
            valInput.setError("This field mustn't be empty!");
            return true;
        } else {
            return false;
        }
    }

    public static boolean isUserNameInvalid(TextInputLayout valInput) {
        String val = valInput.getEditText().getText().toString();

        String nameRegex = "^[a-zA-Z0-9._]+$";
        if (val.isEmpty()) {
            valInput.setError("This field mustn't be empty!");
            return true;
        } else if (val.length() > 20) {
            valInput.setError("User Name is too long!");
            return true;
        } else if (val.length() < 6) {
            valInput.setError("User Name is too short!");
            return true;
        } else if (val.startsWith("_") || val.startsWith(".")) {
            valInput.setError("No _ or . at the beginning");
            return true;
        } else if (!val.matches(nameRegex)) {
            valInput.setError("User Name must only contain a-z, A-Z, 0-9, '.' and '_'");
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmailInvalid(TextInputLayout valInput) {
        String val = valInput.getEditText().getText().toString();
        String emailRegex = "^[a-z0-9]+@[a-z]+\\.[a-z]{2,3}S";

        if (val.isEmpty()) {
            valInput.setError("This field mustn't be empty!");
            return true;
        } else if (!val.matches(emailRegex)) {
            valInput.setError("Email format is invalid!");
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPasswordInvalid(TextInputLayout valInput) {
        String val = valInput.getEditText().getText().toString();
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

        if (val.isEmpty()) {
            valInput.setError("This field mustn't be empty!");
            return true;
        } else if (!val.matches(passwordRegex)) {
            valInput.setError("Password must contains at least eight characters, " +
                    "including at least one number and includes both lower " +
                    "and uppercase letters and special characters");
            return true;
        } else {
            return false;
        }
    }

    public static boolean isConfirmPasswordInvalid(TextInputLayout cfPwdInput, TextInputLayout pwdValInput) {
        String cfpwd = cfPwdInput.getEditText().getText().toString();
        String pwd = pwdValInput.getEditText().getText().toString();

        if (cfpwd.isEmpty()) {
            cfPwdInput.setError("This field mustn't be empty!");
            return true;
        } else if (!cfpwd.equals(pwd)) {
            cfPwdInput.setError("Confirm Password doesn't match password.");
            return true;
        } else {
            return false;
        }
    }


}