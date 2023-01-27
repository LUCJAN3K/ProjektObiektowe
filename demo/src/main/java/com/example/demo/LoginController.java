package com.example.demo;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class LoginController {
    public PasswordField psw;
    public TextField inputLogin;
    String login1;
    String password;
    public Label invalidLogin;
    ScenesController scenesController = new ScenesController();
    public void login(ActionEvent event){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projektobiektowe","root","");
            Statement st = con.createStatement();
            String sql = "SELECT login,psw FROM userdata WHERE login = '" + inputLogin.getText()+"' AND psw = '"+psw.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            login1 = null;
            password = null;
            while (rs.next()){
                login1 = rs.getString("login");
                password = rs.getString("psw");
                break;
            }
            //System.out.println(psw.getText() + " "+ password);
            if(password != null){
               // System.out.println("test");
                scenesController.SwitchSceneMainMenu(event);
            }else{
                invalidLogin.setVisible(true);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        }
    public void register(ActionEvent event) throws IOException {
        scenesController.SwitchSceneRegister(event);
    }
}
