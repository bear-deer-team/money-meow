package com.example.money_meow.category;

public class Category {
    private String categoryName;
    private int categoryID;

    //INCOME or EXPENSE
    private String categoryType;

    public Category() {

    }

    public Category(String categoryName, int categoryID) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
