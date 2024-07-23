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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bebawy
 */
public class ChooseSymbolSinglePlayerScreenController implements Initializable {

    @FXML
    private TextField txtFieldSymboleId;
    @FXML
    private Button btnStartId;

    private String playerSymbol;
    
    @FXML
    private void handleStartButtonAction(ActionEvent event) throws IOException {
        String inputSymbol = txtFieldSymboleId.getText().trim().toUpperCase();
        if (inputSymbol.equals("X") || inputSymbol.equals("O")) {
            playerSymbol = inputSymbol;
            changeScene(event, "/tick/tac/toe/game/view/ChooseLevelOfDifficultySinglePlayer.fxml");
        } else {
            showAlert("Invalid Symbol", "Please enter either 'X' or 'O'.");
        }
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent parent = loader.load();
        
        // Pass player symbol to the next controller if needed
        ChooseLevelOfDifficultySinglePlayerController controller = loader.getController();
        controller.setPlayerSymbol(playerSymbol);

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
