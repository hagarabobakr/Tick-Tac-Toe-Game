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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author bebawy
 */
public class ChoosePlayerTypeScreenController implements Initializable{
    @FXML
    private Button btnMultiplyPlayersId;
    @FXML
    private ImageView backbtn;
    @FXML
    private Button btnSinglePlayerId;
    
    
    @FXML
    private void handleImageAction(MouseEvent event) throws IOException {
        if(event.getSource() == backbtn ){
            changeScene_2(event, "/tick/tac/toe/game/view/ChooseModeScreen.fxml");
        }
        
    }    
    
    private void changeScene_2(Event event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnMultiplyPlayersId) {
            changeScene(event, "/tick/tac/toe/game/view/ChooseSymbolScreen.fxml");
        } else if(event.getSource() == btnSinglePlayerId){
            changeScene(event, "/tick/tac/toe/game/view/ChooseSymbolSinglePlayerScreen.fxml");
        
        }
    }
    
    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
   
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
