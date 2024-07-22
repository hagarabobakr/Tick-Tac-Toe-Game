/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SplashScreenController implements Initializable {

    @FXML
    private Button btnEnterId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // No need for Timer or Platform.runLater
    }

    @FXML
    private void handleEnterButtonAction() throws IOException {
        // Load the ChooseModeScreen.fxml file
        Parent parent = FXMLLoader.load(getClass().getResource("/tick/tac/toe/game/view/ChooseModeScreen.fxml"));
        Scene scene = new Scene(parent);
        
        // Get the current stage from the button's scene
        Stage stage = (Stage) btnEnterId.getScene().getWindow();
        
        // Set the new scene and show it
        stage.setScene(scene);
        stage.show();
    }
}