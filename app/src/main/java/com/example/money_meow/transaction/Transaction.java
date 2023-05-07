package com.example.money_meow.transaction;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.money_meow.category.Category;
import com.example.money_meow.category.CategoryList;
import com.example.money_meow.database.insert.MongoDBInsert;

import org.bson.Document;
import org.bson.types.ObjectId;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Transaction extends RealmObject implements Comparable{

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private Category transactionCategory;

    private double transactionAmount;
    private String userName;
    private String name;
    // thoi gian, hien chua nghi cach xu li
    // chac ep kieu
    private Date transactionTime;
    private String transactionNote;
    private String transactionType;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public Date getTime() {
        return transactionTime;
    }


    public Transaction() {
    }

    // constructor used in TransactionQuery
    public Transaction(String id, String name, double transactionAmount, String userName, Date transactionTime, String transactionNote, String transactionType) {
        this.id = id;
        this.name = name;
        this.transactionCategory = CategoryList.FindByName(this.name);
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

    //Using in CalcuTest
    public Transaction(double transactionAmount, String transactionType) {
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
    }

    public Transaction(double transactionAmount, Category category) {
        this.transactionAmount = transactionAmount;
        this.transactionCategory = category;
        this.transactionType = transactionCategory.getCategoryType();
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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

    public String getId() {
        return id;
    }

    public Document toDocument() {
        return new Document()
                .append("id", id)
                .append("userName",userName)
                .append("name",name)
                .append("type",transactionType)
                .append("amount",transactionAmount)
                .append("note",transactionNote)
                .append("date", transactionTime);
    }

    @Override
    public int compareTo(Object o) {
        Transaction another = (Transaction) o;
        return another.getTransactionTime().compareTo(this.transactionTime);
    }

}
