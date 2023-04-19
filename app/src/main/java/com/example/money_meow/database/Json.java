package com.example.money_meow.database;

import com.example.money_meow.transaction.Transaction;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Json {
    private String file;


    public void init (String url){
        this.file = url;
    }
    public List<Transaction> getTranSactionList(String userName){
        Gson gson = new Gson();
        List<Transaction> res = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file + userName));
            Type listType = new TypeToken<List<Transaction>>(){}.getType();
            res = gson.fromJson(br, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void addToJson(List<Transaction> transactions,String userName){
        Gson gson = new Gson();
        String json = gson.toJson(transactions);
        try {
            FileWriter writer = new FileWriter(file + userName);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
