package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;




public class LoginController {
    @FXML
    private Button powrotbutton;
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
    loginzlylabel.setText("Zle bardzo zle");
    }
    else{
        loginzlylabel.setText("Proszę podaj login i hasło");
    }

    }

    public void setPowrotbutton(ActionEvent e) {
        Stage stage = (Stage) powrotbutton.getScene().getWindow();
        stage.close();

    }
}


