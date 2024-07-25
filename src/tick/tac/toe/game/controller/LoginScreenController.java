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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mystore
 */
public class LoginScreenController implements Initializable {

    @FXML
    private AnchorPane Anchorpn;
    @FXML
    private VBox vbox;
    @FXML
    private GridPane gridpn;
    @FXML
    private ImageView nameicon;
    @FXML
    private Label namelabel;
    @FXML
    private Label passwordlabel;
    @FXML
    private ImageView passwordicon;
    @FXML
    private TextField passwordfield;
    @FXML
    private TextField namefield;
    @FXML
    private Button login;

    /**
     * Initializes the controller class.
     */
    private String username = null;
    private String password = null;
    
    private void SendLoginRequest(){
        login.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(namefield.getText() != null){
                    username = namefield.getText();
                }
                else{
                    System.out.println("please fill namefield");
                }
                if(passwordfield.getText() != null){
                    password = passwordfield.getText();
                }
                else{
                    System.out.println("please fill passwordfield");
                }
            //send username and password to server   
            
            }
        });
         
    }
    
    
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == login) {
            changeScene(event, "/tick/tac/toe/game/view/OnlinePlayersListScreen.fxml");
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
