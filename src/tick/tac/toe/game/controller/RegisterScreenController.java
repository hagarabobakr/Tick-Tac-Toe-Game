/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.ResponseHandler;
import tick.tac.toe.game.network.ResponseListener;
import tick.tac.toe.game.network.requestCreator;

/**
 * FXML Controller class
 *
 * @author mystore
 */
public class RegisterScreenController implements Initializable, ResponseListener {

    @FXML
    private TextField namefield;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private PasswordField confirmfield;
    @FXML
    private Button register;
    @FXML
    private ImageView backbtn;

    /**
     * Initializes the controller class.
     */
    private String username = null;
    private String password = null;
    private String confirmedPassword = null;
    private String r;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        username = namefield.getText();
        password = passwordfield.getText();
        confirmedPassword = confirmfield.getText();
        if (!(username.equals("") && password.equals("") && confirmedPassword.equals(""))) {
            if (password.equals(confirmedPassword)) {
                if (validation(password)) {
                    Client.sendRequest(requestCreator.register(username, password));
                    try {
                        Thread.sleep(1000);

                        Platform.runLater(() -> {

                            if (r.equals("successfulReqisration")) {
                                try {
                                    changeScene(event, "/tick/tac/toe/game/view/OnlinePlayersListScreen.fxml");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }

                            } else if (r.equals("playerExist")) {
                                showAlert(Alert.AlertType.ERROR, "Player exist", "player exist! please log in..");
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //weak password
                    showAlert(Alert.AlertType.ERROR, "Weak Password", "Password must be at least 8 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character.");
                }
            } else {
                //unmatched passwords
                showAlert(Alert.AlertType.ERROR, "unmatched passwords", "unmatched passwords...");
            }
        } else {
            //empty fields
            showAlert(Alert.AlertType.ERROR, "empty fields", "Please, fill the fields first..");
        }
    }

    @FXML
    private void handleImageAction(MouseEvent event) throws IOException {
        changeScene_2(event, "/tick/tac/toe/game/view/Login$registerScreen.fxml"); // Assuming you want to go to the SplashScreen
    }

    private void changeScene_2(Event event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
        ResponseHandler.setListener(this);
    }

    @Override
    public void onResponse(String response) {
        JSONObject requestObject = (JSONObject) JSONValue.parse(response);
        response = (String) requestObject.get("response");
        if (response.equals("successfulReqisration")) {
            r = "successfulReqisration";
        } else if (response.equals("playerExist")) {
            r = "playerExist";
        }

    }
}
