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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mystore
 */
public class LoginRegisterScreenController implements Initializable {

    @FXML
    private AnchorPane Anchorpn;
    @FXML
    private VBox vbox;
    @FXML
    private Label TIClabel;
    @FXML
    private Button login;
    @FXML
    private Button regester;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == regester) {
            changeScene(event, "/tick/tac/toe/game/view/RegisterScreen.fxml");
        }
        if (event.getSource() == login) {
            changeScene(event, "/tick/tac/toe/game/view/LoginScreen.fxml");
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
