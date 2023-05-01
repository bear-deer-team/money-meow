package com.example.money_meow.category;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryList {
    public static List<Category> categories= new ArrayList<>();

    public static Category FindByName(String name){
        for(int i=0;i<categories.size();i++){
            if(categories.get(i).getCategoryName().equals(name)){
                return categories.get(i);
            }
        }
        Log.v("category","can't find category");
        return null;
    }

    public static List<Category> removeDuplicate() {
        Set<String> nameSet = new HashSet<>();
        List<Category> result = new ArrayList<>();

        for (Category category : categories) {
            if (!nameSet.contains(category.getCategoryName())) {
                nameSet.add(category.getCategoryName());
                result.add(category);
            }
        }

        return result;
    }

}
