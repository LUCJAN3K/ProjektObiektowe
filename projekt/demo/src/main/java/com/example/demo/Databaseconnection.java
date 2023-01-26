package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;



public class Databaseconnection {
    public Connection databaseLink;
    public Connection getConnection() {
        String databaseName = "";
        String databaseUser = "";
        String databasePassword = "";
        String url = "" +databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
;        }
        return databaseLink;
    }
}
