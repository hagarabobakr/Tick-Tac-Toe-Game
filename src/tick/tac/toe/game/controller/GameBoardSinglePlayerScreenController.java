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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author bebawy
 */
public class GameBoardSinglePlayerScreenController implements Initializable {

    @FXML
    private Text playerXscore;
    @FXML
    private Text playerOscore;
    @FXML
    private Label playerxName;
    @FXML
    private Label playerOname;

    @FXML
    private Button Btn11;
    @FXML
    private Button Btn12;
    @FXML
    private Button Btn13;
    @FXML
    private Button Btn21;
    @FXML
    private Button Btn22;
    @FXML
    private Button Btn23;
    @FXML
    private Button Btn31;
    @FXML
    private Button Btn32;
    @FXML
    private Button Btn33;
    
    private String difficultyLevel;
    private String playerSymbol;
    private boolean playerTurn;

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
