/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author bebawy
 */
public class GameBoardSinglePlayerScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String difficultyLevel;

    @FXML
    private void initialize() {
        // Use difficultyLevel to set up the game board
    }

    public void setDifficultyLevel(String difficulty) {
        this.difficultyLevel = difficulty;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
}
