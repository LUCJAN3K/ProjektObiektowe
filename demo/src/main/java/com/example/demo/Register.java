package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class Register {
    public PasswordField passwordField1;
    public PasswordField passwordField2;
    public TextField usernameField;
    public Label registrationErrors;
    public ScenesController scenesController = new ScenesController();
    public void register(ActionEvent event){
        //connect to db
        try{
        Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/obiektowe","lucjan","test1234");
        Statement st = con.createStatement();
        String sql = "SELECT login FROM userdata WHERE login = '" + usernameField.getText()+"'";
        ResultSet rs = st.executeQuery(sql);
        //check username validity
        String foundLogin = null;
        while (rs.next()){
            foundLogin = rs.getString("login");
            break;
        }
        //check password validity and insert data if valid
        if(passwordField1.getText().equals(passwordField2.getText()) && !passwordField2.getText().isBlank() && foundLogin == null && !usernameField.getText().isBlank()){
            sql = "INSERT INTO `userdata`(`login`, `psw`) VALUES ('"+usernameField.getText()+"','"+passwordField1.getText()+"')";
            st.executeUpdate(sql);
            scenesController.SwitchSceneLogin(event);
        }else if(foundLogin != null){
            registrationErrors.setText("Username already taken");
        }else if(passwordField2.getText().isBlank()){
            registrationErrors.setText("Password cannot be blank");
        }else if(!passwordField1.getText().equals(passwordField2.getText())){
            registrationErrors.setText("Passwords are not matching");
        }else{
            registrationErrors.setText("Username cannot be blank");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void goBackToLogin(ActionEvent event) throws IOException {
        scenesController.SwitchSceneLogin(event);
    }
}
