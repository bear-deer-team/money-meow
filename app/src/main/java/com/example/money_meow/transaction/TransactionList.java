package com.example.money_meow.transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionList {

//    mainList dung cho ca qua trinh hoat dong cua app va de chen vao json sau khi app dong
//    addList va deleteList de them xoa truc tiep cho mongodb khi app dong
    public static List<Transaction> mainList = new ArrayList<>();
    public static List<Transaction> addList = new ArrayList<>();
    public static List<Transaction> deleteList = new ArrayList<>();
    public static List<Transaction> updateList = new ArrayList<>();

    public static void add(Transaction transaction){
        mainList.add(transaction);
        addList.add(transaction);
    }
    public static void delete(Transaction _transaction){
        String id = _transaction.getId();
        mainList.removeIf(transaction -> transaction.getId().equals(id));
        deleteList.add(_transaction);
    }

    public static void update(Transaction transaction){
        for (int i = 0; i < mainList.size(); i++) {
            if (mainList.get(i).getId().equals(transaction.getId())) {
                mainList.set(i, transaction);
            }
        }
        updateList.add(transaction);
    }

    public static void destroy() {
        updateList.clear();
        deleteList.clear();
        addList.clear();
    }
}
