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
import javafx.event.EventType;
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
public class RegisterScreenController implements Initializable {

    @FXML
    private AnchorPane Anchorpn;
    @FXML
    private VBox vbox;
    @FXML
    private GridPane gridpn;
    @FXML
    private Label namelabel;
    @FXML
    private Label passwordlabel;
    @FXML
    private Label confirmlabel;
    @FXML
    private ImageView nameimg;
    @FXML
    private ImageView passwordimg;
    @FXML
    private ImageView confirmimg;
    @FXML
    private TextField namefield;
    @FXML
    private TextField passwordfield;
    @FXML
    private TextField confirmfield;
    @FXML
    private Button register;
    
    private String username = null;
    private String password = null;
    private String confirmedPassword = null;
    
    private void SendRegisterRequest(){
        register.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
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
                if(confirmfield.getText() != null){
                     confirmedPassword = confirmfield.getText();
                }
                else{
                    System.out.println("please fill confirmfield");
                }
                if(password.equals(confirmedPassword) == true){
                   //send register request to server 
                }
                else{
                    System.out.println("be sure that confirmedPassword is the same of password ");
                }
            }
        });
         
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == register) {
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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
