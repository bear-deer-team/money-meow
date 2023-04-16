package com.example.money_meow.database;

import com.example.money_meow.category.Category;

import org.bson.Document;


public class CategoryQuery {
    //lấy kết quả tìm kiếm là document chuyển sang Category
    public static Category FindByName(String name){
       Document categoryDoc = new Document(MongoDBQuery.queryOne("MoneyMeow","category",new Document("name",name)));
       Category category = new Category(categoryDoc.get("name",String.class),categoryDoc.get("type",String.class));
       return category;
    }
}
