package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;



public class Databaseconnection {
    public Connection databaseLink;
    public Connection getConnection() {
        String databaseName = "sys";
        String databaseUser = "localhost";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" +databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
;        }
        return databaseLink;
    }
}
