package com.fashionstore.util;

import java.sql.Connection;

import com.fashionstore.util.DBConnection;

public class DBConnectionTest {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {

            System.out.println("Database Connected Successfully");

        } else {

            System.out.println("Database Connection Failed");
        }
    }
}
