package com.example.money_meow.transaction;

import com.example.money_meow.category.Category;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    private int transactionID;
    private Category transactionCategory;
    private double transactionAmount;
    // thoi gian, hien chua nghi cach xu li
    // chac ep kieu
    private Date transactionTime;
    private String transactionNote;
    private String transactionType;

    // tạo một transaction
    public Transaction(int transactionID, Category transactionCategory, double transactionAmount,
                       Date transactionTime, String transactionNote) {
        this.transactionID = transactionID;
        this.transactionCategory = transactionCategory;
        this.transactionTime = transactionTime;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionCategory.getCategoryType();
    }
    // lưu transaction vào database
    public void saveToDatabase(Transaction a) {
        //ket noi toi CSDl
        try {
            Connection cnt = DriverManager.getConnection("link database", "username", "password");
            PreparedStatement stmt = cnt.prepareStatement("INSERT INTO transactions (transactionID, transactionCategory, transactionAmount, transactionTime, transactionNote) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, a.getTransactionID());
            stmt.setString(2, a.getTransactionCategory().getCategoryName());
            stmt.setDouble(3, a.getTransactionAmount());
            stmt.setDate(4, a.getTransactionTime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Hien thi thong tin trong transaction
    public void display(Transaction a) {
        // Lấy các thành phần giao diện

        // Cập nhật thông tin của giao dịch lên các thành phần giao diện

    }
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
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
