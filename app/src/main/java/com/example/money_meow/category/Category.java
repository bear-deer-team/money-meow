package com.example.money_meow.category;




import android.content.res.Resources;
import android.content.Context;

import com.example.money_meow.database.CategoryQuery;

import io.realm.RealmObject;


public class Category extends RealmObject {
    private String categoryName;

    //INCOME or EXPENSE
    private String categoryType;
    private int image;

    public Category() {
    }

    public Category(String categoryName, String categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;

    }

    public Category(String categoryName){
        this.categoryName = categoryName;
        this.categoryType = CategoryList.FindByName(categoryName).getCategoryType();
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

    private void setImage(Context context) {
        String drawableName = this.categoryName;
        Resources resources = context.getResources();
        this.image = resources.getIdentifier(drawableName, "drawable", context.getPackageName());

    }

    public int getImage(Context context) {
        setImage(context);
        return this.image;
    }
}
