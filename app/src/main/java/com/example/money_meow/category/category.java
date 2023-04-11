package com.example.money_meow.category;

public class category {
    private String category_name;
    private int category_id;

    public category() {

    }

    public category(String category_name, int category_id) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    @Override
    public String toString() {
        return category_name;
    }
}
