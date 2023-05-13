package com.example.money_meow.account;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    public static String encrypt(String password)  {
        try
        {
            /* MessageDigest instance for hashing using SHA256 */
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            BigInteger number = new BigInteger(1, messageDigest.digest((password.getBytes())));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32)
            {
                hexString.insert(0, '0');
            }

            System.out.println(password + " " + hexString);
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
            return password;
        }

    }



}
