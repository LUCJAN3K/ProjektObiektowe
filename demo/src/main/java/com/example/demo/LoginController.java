package com.example.demo;
import java.sql.*;
public class LoginController {
    public static void connect(){
    try{
        Connection con = DriverManager.getConnection("localhost");
        System.out.println("uwu");
    }catch(Exception e){
        e.printStackTrace();
    }
}}
