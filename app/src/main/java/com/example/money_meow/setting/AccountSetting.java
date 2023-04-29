package com.example.money_meow.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.money_meow.BaseActivity;
import com.example.money_meow.R;
import com.example.money_meow.account.LoginAccount;
import com.example.money_meow.account.PasswordEncryption;
import com.example.money_meow.account.signup.AccountValidation;
import com.example.money_meow.database.update.MongoDBUpdate;
import com.example.money_meow.home.Home;
import com.example.money_meow.information.Information;
import com.example.money_meow.transaction.TransactionAction;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import org.bson.Document;

public class AccountSetting extends BaseActivity {
    Button addTransBtn, homeBtn, historyBtn, transactionBtn, settingBtn;
    Button returnBtn, showBtn;

    TextView username, name, email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_edit);

        addTransBtn = findViewById(R.id.AddTransBtn);
        homeBtn = findViewById(R.id.HomeBtn);
        historyBtn = findViewById(R.id.HistoryBtn);
        transactionBtn = findViewById(R.id.transactionBtn);
        settingBtn = findViewById(R.id.SettingBtn);
        showBtn = findViewById(R.id.showBtn);
        returnBtn = findViewById(R.id.returnBtn);

        username = (TextView) findViewById(R.id.acc_username);
        name = findViewById(R.id.acc_name);
        email = findViewById(R.id.acc_email);
        password = findViewById(R.id.acc_pwd);

        setAccInfor();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSetting.this, Home.class);
                startActivity(intent);
            }
        });
        addTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSetting.this, TransactionAction.class);
                startActivity(intent);
            }
        });

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AccountSetting.this, R.style.AppBottomSheetDialogTheme);
        bottomSheetDialog.setContentView(R.layout.bottom_setting);
        TextInputLayout oldPassword = (TextInputLayout) bottomSheetDialog.findViewById(R.id.pwd_box);
        TextInputLayout newPwd = (TextInputLayout) bottomSheetDialog.findViewById(R.id.newpwd_box);
        TextInputLayout cfNewPwd = (TextInputLayout) bottomSheetDialog.findViewById(R.id.cf_newpwd_box);
        Button submit = bottomSheetDialog.findViewById(R.id.sumbitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isConfirmed(oldPassword, newPwd, cfNewPwd)) {
                    return;
                }
                MongoDBUpdate.update("MoneyMeow", "users",
                        new Document("password", LoginAccount.account.getPassword()),
                        new Document()
                                .append("name", LoginAccount.account.getName())
                                .append("userName", LoginAccount.account.getUserName())
                                .append("email", LoginAccount.account.getEmail())
                                .append("password", PasswordEncryption.encrypt(newPwd.getEditText().getText().toString()))
                                .append("balance", LoginAccount.account.getBalance()));
                LoginAccount.account.setPassword(newPwd.getEditText().getText().toString());
                oldPassword.getEditText().setText("");
                newPwd.getEditText().setText("");
                cfNewPwd.getEditText().setText("");

                bottomSheetDialog.dismiss();
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSetting.this, Settings.class);
                startActivity(intent);
            }
        });
    }

    private void setAccInfor() {
        username.setText(LoginAccount.account.getUserName());
        name.setText(LoginAccount.account.getName());
        email.setText(LoginAccount.account.getEmail());
        String pwd = "********";
        password.setText(pwd);
    }

    private boolean isConfirmed(TextInputLayout oldPassword, TextInputLayout newPwd, TextInputLayout cfNewPwd) {

        if(!isPwdMatched(oldPassword, LoginAccount.account.getPassword())
                | !isPwdMatched(newPwd, PasswordEncryption.encrypt(cfNewPwd.getEditText().getText().toString()))
                | AccountValidation.isPasswordInvalid(newPwd)) {
            oldPassword.getEditText().setText("");
            newPwd.getEditText().setText("");
            cfNewPwd.getEditText().setText("");
            return false;
        }
        return true;

    }

    private boolean isPwdMatched(TextInputLayout oldPwd, String newPwd) {
        if (PasswordEncryption.encrypt(oldPwd.getEditText().getText().toString()).equals(newPwd)) {
            return true;
        } else {
            oldPwd.setError("Password's not matched");
            return false;
        }
    }



}
