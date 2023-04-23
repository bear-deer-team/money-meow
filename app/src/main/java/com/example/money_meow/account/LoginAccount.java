package com.example.money_meow.account;


import com.example.money_meow.database.UserQuery;

import io.realm.mongodb.User;

// lop nay luu account hien dang duoc dang nhap, su dung lop nay goi ra ten de lay tu csdl hoac luu vao csdl voi thong tin cua account
public class LoginAccount {
    public static Account account;
    public static void getAcc(String userName){
        account = UserQuery.FindByUserName(userName);
    }

    public static void logout() {
        account = null;
    }
}
