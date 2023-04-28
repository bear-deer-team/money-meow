package com.example.money_meow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.money_meow.database.delete.TransactionDelete;
import com.example.money_meow.database.insert.TransactionInsert;
import com.example.money_meow.database.update.TransactionUpdate;
import com.example.money_meow.transaction.TransactionList;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onPause()
    {
        super.onPause();
        TransactionDelete.deleteUptoDB();
        TransactionInsert.insertUptoDB();
        TransactionUpdate.insertUptoDB();
        TransactionList.destroy();
        System.out.println("Flags");
        overridePendingTransition(0, 0);
    }
}
