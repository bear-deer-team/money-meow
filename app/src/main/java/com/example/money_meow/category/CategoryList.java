package com.example.money_meow.category;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {
    public static List<Category> categories= new ArrayList<>();

    public static Category FindByName(String name){
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).getCategoryName().equals(name)){
                Log.v("category",categories.get(i).getCategoryName());
                return categories.get(i);
            }
        }
        Log.v("category","can't find category");
        return null;
    }
}
