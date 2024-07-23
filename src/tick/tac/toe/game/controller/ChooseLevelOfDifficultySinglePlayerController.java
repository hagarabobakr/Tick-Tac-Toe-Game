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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bebawy
 */
public class ChooseLevelOfDifficultySinglePlayerController implements Initializable {

    @FXML
    private Button btnEasyId;
    @FXML
    private Button btnMediumId;
    @FXML
    private Button btnHardId;

    private String playerSymbol;

    public void setPlayerSymbol(String symbol) {
        this.playerSymbol = symbol;
    }
     

    
    @FXML
    private void handleDifficultyButtonAction(ActionEvent event) throws IOException {
        String difficulty = "";
        
        if (event.getSource() == btnEasyId) {
            difficulty = "Easy";
        } else if (event.getSource() == btnMediumId) {
            difficulty = "Medium";
        } else if (event.getSource() == btnHardId) {
            difficulty = "Hard";
        }

        changeScene(event,"/tick/tac/toe/game/view/GameBoardSinglePlayerScreen.fxml", difficulty);
    }
    
    private void changeScene(ActionEvent event, String fxmlFile, String difficulty) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent parent = loader.load();
        // Pass the difficulty level to the GameBoardScreenController
        GameBoardSinglePlayerScreenController controller = loader.getController();
        controller.setDifficultyLevel(difficulty);
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
