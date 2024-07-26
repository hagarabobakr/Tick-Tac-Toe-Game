/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.requestCreator;

/**
 * FXML Controller class
 *
 * @author mystore
 */
public class RegisterScreenController implements Initializable {

    @FXML
    private TextField namefield;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private PasswordField confirmfield;
    @FXML
    private Button register;

    /**
     * Initializes the controller class.
     */
    private String username = null;
    private String password = null;
    private String confirmedPassword = null;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        username = namefield.getText();
        password = passwordfield.getText();
        confirmedPassword = confirmfield.getText();
        if (!(username.equals("") && password.equals("") && confirmedPassword.equals(""))) {
            if (password.equals(confirmedPassword)) {
                if (validation(password)) {
                    Client.sendRequest(requestCreator.register(username, password));
                } else {
                    //weak password
                }
            } else {
                //unmatched passwords
            }
        } else {
            //empty fields
        }
//        if (event.getSource() == register) {
//            changeScene(event, "/tick/tac/toe/game/view/OnlinePlayersListScreen.fxml");
//        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validation(String pass) {
        // Check if the name contains both letters and numbers
        String complexPasswordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return pass.matches(complexPasswordPattern);

    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
