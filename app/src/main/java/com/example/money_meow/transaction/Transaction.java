package com.example.money_meow.transaction;

import com.example.money_meow.category.Category;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    private int transaction_id;
    private Category transaction_category;
    private double transaction_amount;
    // thoi gian, hien chua nghi cach xu li
    // chac ep kieu
    private Date transaction_time;
    private String transaction_note;
    private String transactionType;

    // tạo một transaction
    public Transaction(int transaction_id, Category transaction_category, double transaction_amount,
                       Date transaction_time, String transaction_note) {
        this.transaction_id = transaction_id;
        this.transaction_category = transaction_category;
        this.transaction_time = transaction_time;
        this.transaction_amount = transaction_amount;
        this.transactionType = transaction_category.getCategoryType();
    }
    // lưu transaction vào database
    public void saveToDatabase(Transaction a) {
        //ket noi toi CSDl
        try {
            Connection cnt = DriverManager.getConnection("link database", "username", "password");
            PreparedStatement stmt = cnt.prepareStatement("INSERT INTO transactions (transaction_id, transaction_category, transaction_amount, transaction_time, transaction_note) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, a.getTransaction_id());
            stmt.setString(2, a.getTransaction_category().getCategory_name());
            stmt.setDouble(3, a.getTransaction_amount());
            stmt.setDate(4, a.getTransaction_time());
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
    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Category getTransaction_category() {
        return transaction_category;
    }

    public void setTransaction_category(Category transaction_category) {
        this.transaction_category = transaction_category;
    }

    public Date getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(Date transaction_time) {
        this.transaction_time = transaction_time;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_note() {
        return transaction_note;
    }

    public void setTransaction_note(String transaction_note) {
        this.transaction_note = transaction_note;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
