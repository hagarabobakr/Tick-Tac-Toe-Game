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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private PasswordField passwordfield;
    @FXML
    private PasswordField confirmfield;
    @FXML
    private Button register;

    /**
     * Initializes the controller class.
     */
    private String username = null;
    private String password = null;
    private String confirmedPassword = null;
    
    private void SendRegisterRequest(){
        register.setOnAction(event -> {
                if(namefield.getText() != null){
                    if(validation(namefield.getText())){
                        username = namefield.getText();
                    }
                    else{
                        showAlert(AlertType.ERROR, "invalid data", "username must contain both letters and numbers.");
                        return;
                    }
                }
                else{
                    showAlert(AlertType.ERROR, "uncomplete data", "Please fill namefield");
                    return;
                }
                if(passwordfield.getText() != null){
                    if(validation(passwordfield.getText())){
                        password = passwordfield.getText();
                    }
                    else{
                        showAlert(AlertType.ERROR, "invalid data", "password must contain both letters and numbers.");
                        return;
                    }
                }
                else{
                    showAlert(AlertType.ERROR, "uncomplete data", "Please fill passwordfield");
                    return;
                }
                if(confirmfield.getText() != null){
                     confirmedPassword = confirmfield.getText();
                }
                else{
                    showAlert(AlertType.ERROR, "uncomplete data", "Please fill confirm passwordfield");
                    return;
                }
                if(password.equals(confirmedPassword) == true){
                   //send register request to server 
                }
                else{
                    showAlert(AlertType.ERROR, "wrong data", "be sure that confirmedPassword is the same of password");
                    return;
                }
        });
         
    }
    private boolean validation(String name) {
        // Check if the name contains both letters and numbers
        boolean hasLetter = name.matches(".*[a-zA-Z]+.*");
        boolean hasNumber = name.matches(".*[0-9]+.*");
        return hasLetter && hasNumber;
    }
    
    @FXML
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
