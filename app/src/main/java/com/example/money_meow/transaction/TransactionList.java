package com.example.money_meow.transaction;

import java.util.List;

public class TransactionList {

//    mainList dung cho ca qua trinh hoat dong cua app va de chen vao json sau khi app dong
//    addList va deleteList de them xoa truc tiep cho mongodb khi app dong
    public static List<Transaction> mainList;
    private static List<Transaction> addList;
    private static List<Transaction> deleteList;

    public void add(Transaction transaction){
        mainList.add(transaction);
        addList.add(transaction);
    }
    public void delete(Transaction _transaction){
        String id = _transaction.getId();
        mainList.removeIf(transaction -> transaction.getId().equals(id));
        deleteList.add(_transaction);
    }
}