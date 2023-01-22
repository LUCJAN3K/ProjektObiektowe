package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginController {
    @FXML
    private Button exitgamebutton;
    @FXML
    private Label loginzlylabel;
    @FXML
    private TextField logintext;
    @FXML
    private PasswordField haslotext;





    public void setloginbutton(ActionEvent e) {
        loginzlylabel.setText("Spróbuj jeszcze raz");
        if    (logintext.getText().isBlank() == false && haslotext.getText().isBlank() ==false)
        {
            validateLogin();
            loginzlylabel.setText("Zle bardzo zle");
        }
        else{
            loginzlylabel.setText("Proszę podaj login i hasło");
        }

    }

    public void setPowrotbutton(ActionEvent e) {
        Stage stage = (Stage) exitgamebutton.getScene().getWindow();
        stage.close();

    }
    public void validateLogin() {
        Databaseconnection connectNow = new Databaseconnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE login = '" + logintext.getText() + "' AND password = '" + haslotext.getText() + "" ;
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1)==1){
                    loginzlylabel.setText("Welcome");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("mainmenu.fxml"));

                }
                else
                {
                    loginzlylabel.setText("Wrong login or password, Please try again");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


