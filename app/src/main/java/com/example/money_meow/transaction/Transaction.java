package com.example.money_meow.transaction;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.money_meow.category.Category;
import com.example.money_meow.database.CategoryQuery;
import com.example.money_meow.database.MongoDBInsert;

import org.bson.Document;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    private Category transactionCategory;

    private double transactionAmount;
    private String userName;
    private String name;
    // thoi gian, hien chua nghi cach xu li
    // chac ep kieu
    private Date transactionTime;
    private String transactionNote;
    private String transactionType;

    // constructor used in TransactionQuery
    public Transaction(String name, double transactionAmount, String userName, Date transactionTime, String transactionNote, String transactionType) {
        this.name = name;
        this.transactionCategory = CategoryQuery.FindByName(name);
        this.transactionAmount = transactionAmount;
        this.userName = userName;
        this.transactionTime = transactionTime;
        this.transactionNote = transactionNote;
        this.transactionType = transactionType;
    }

    // constructor used in TransactionAction
    public Transaction(Category category, double transactionAmount, String userName, Date transactionTime, String transactionNote) {
        this.name = category.getCategoryName();
        this.transactionCategory = category;
        this.transactionAmount = transactionAmount;
        this.userName = userName;
        this.transactionTime = transactionTime;
        this.transactionNote = transactionNote;
        this.transactionType = category.getCategoryType();
    }
    // tạo một transaction
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Transaction(String userName, Category transactionCategory, double transactionAmount, String transactionNote) {
        this.transactionCategory = transactionCategory;
        this.transactionTime = new Date();
        this.userName = userName;
        this.transactionAmount = transactionAmount;
        this.transactionNote = transactionNote;
        this.transactionType = transactionCategory.getCategoryType();
    }
    // lưu transaction vào database
    public void saveToDatabase(Transaction a) {
        //ket noi toi CSDl

        MongoDBInsert.insertOne("MoneyMeow","transactions",new Document()
                .append("userName",a.userName)
                .append("name",a.transactionCategory)
                .append("type",a.transactionType)
                .append("amount",a.transactionAmount)
                .append("note",a.transactionNote)
                .append("date", a.transactionTime));

    }
    //Hien thi thong tin trong transaction
    public void display(Transaction a) {
        // Lấy các thành phần giao diện

        // Cập nhật thông tin của giao dịch lên các thành phần giao diện

    }




    public Category getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(Category transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }
    public String formatDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(this.transactionTime);
        return formattedDate;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionNote() {
        return transactionNote;
    }

    public void setTransactionNote(String transactionNote) {
        this.transactionNote = transactionNote;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
