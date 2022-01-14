
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import booklibrary.models.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Saleh Alkayyali
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXTextField userName, Email, phoneNumber, Email1;
    @FXML
    private JFXPasswordField Password, confirmPassword, Password1;

    @FXML
    private JFXButton register, logIn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        register.setOnMouseClicked((event) -> {
            new User(-1, userName.getText(), Email.getText(), Password.getText(), "", phoneNumber.getText()).create();
        });
        logIn.setOnMouseClicked((event) -> {
            User.login(Email1.getText(), Password1.getText());
           
        });
    }

}
