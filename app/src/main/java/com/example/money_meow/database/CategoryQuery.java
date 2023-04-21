package com.example.money_meow.database;

import android.content.Context;
import android.util.Log;

import com.example.money_meow.MainActivity;
import com.example.money_meow.category.Category;
import com.example.money_meow.home.Home;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class CategoryQuery {
    //lấy kết quả tìm kiếm là document chuyển sang Category

    public static List<Category> getCategoryList(){
        List<Document> categoryDocs = new ArrayList<>(MongoDBQuery.findAll("MoneyMeow","category"));
        List<Category> categories = new ArrayList<>();
        for(int i=0;i<categoryDocs.size();i++) {
            categories.add(new Category(categoryDocs.get(i).getString("name"), categoryDocs.get(i).getString("type")));
        }
        return categories;
    }
}
