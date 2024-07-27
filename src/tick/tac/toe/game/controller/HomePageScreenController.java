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
public class HomePageScreenController implements Initializable{
    @FXML
    private Button btnRecordedGamesId;
    @FXML
    private Button btnStartId;
    @FXML
    private Button btnExiteId;
    @FXML
    private ImageView backbtn;
    
    
    
    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnRecordedGamesId) {
            changeScene(event, "/tick/tac/toe/game/view/RecordedGamesListScreen.fxml");
        } else if (event.getSource() == btnStartId) {
            changeScene(event, "/tick/tac/toe/game/view/OnlinePlayersListScreen.fxml");
        } else if (event.getSource() == btnExiteId) {
            System.exit(0);
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
