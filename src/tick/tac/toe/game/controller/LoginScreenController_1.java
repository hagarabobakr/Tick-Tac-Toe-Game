 /* To change this license header, choose License Headers in Project Properties.
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.RequestCreator;
import tick.tac.toe.game.network.ResponseListener;

/**
 * FXML Controller class
 *
 * @author mystore
 */
public class LoginScreenController_1 implements Initializable, ResponseListener {

    @FXML
    private PasswordField passwordfield;
    @FXML
    private TextField namefield;
    @FXML
    private ImageView backbtn;
    @FXML
    private Button loginbtn;

    private String username = null;
    private String password = null;

    /**
     * Handles button click events.
     * Sends a login request if the login button is clicked, or changes the scene if another button is clicked.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == loginbtn) {
            username = namefield.getText();
            password = passwordfield.getText();
            if (!(username.equals("") && password.equals(""))) {
                // Send login request to the server
                Client.sendRequest(RequestCreator.login(username, password));
            }
        } else {
            // Change scene if another button is clicked
            changeScene(event, "/tick/tac/toe/game/view/Login$registerScreen.fxml");
        }
    }

    /**
     * Changes the scene to the specified FXML file.
     * 
     * @param event The event triggering the scene change.
     * @param fxmlFile The path to the FXML file.
     */
    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller, if needed
    }

    /**
     * Called when a response is received from the server.
     * Displays a success message and changes the scene if login is successful.
     * 
     * @param response The response message from the server.
     */
    @Override
    public void onResponse(String response) {
        // Display a success message and navigate to the home screen on successful login
        if (response.equals("loginSuccess")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully logged in!");
            alert.showAndWait();

            // Change the scene to the home screen after showing the alert
            try {
                changeScene(new ActionEvent(), "/tick/tac/toe/game/view/HomePageScreen.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}