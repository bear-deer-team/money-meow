package com.example.money_meow.database;

import com.example.money_meow.transaction.Transaction;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionQuery {

    // lấy kết quả tìm kiếm là 1 list các document của collection "transactions"
    // và chuyển về list các Transaction
    public static List<Transaction> FindByUserName(String userName){
        List<Document> transactionDocs = new ArrayList<>(MongoDBQuery.find("MoneyMeow","transactions",new Document("userName",userName)));
        List<Transaction> transactions = new ArrayList<>();
        for(int i=0;i<transactionDocs.size();i++) {
            String id = transactionDocs.get(i).getObjectId("_id").toString();
            String name = transactionDocs.get(i).getString("name");
            String type = transactionDocs.get(i).getString("type");
            Double amount = transactionDocs.get(i).getDouble("amount");
            Date date = transactionDocs.get(i).getDate("date");
            String note = transactionDocs.get(i).getString("note");
            Transaction transaction = new Transaction(id,name,amount,userName,date,note,type);
            transactions.add(transaction);
        }
        return transactions;
    }

}
