/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LoginScreenController_1 implements Initializable {

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
    private PasswordField passwordfield;
    @FXML
    private TextField namefield;
    @FXML
    private Button login;
    
    
    String username = null;
    String password = null;
    @FXML
    private void SendLoginRequest(){
        Socket server;
        DataInputStream ear;
        PrintStream mouth;
        try{
            //take IP address from the previous screen
            server = new Socket(InetAddress.getLocalHost(), 5005);
            ear = new DataInputStream(server.getInputStream());
            mouth = new PrintStream(server.getOutputStream());
            login.setOnAction(event -> {
                try {
                    if(namefield.getText() != null){
                        username = namefield.getText();
                    }
                    else{
                        showAlert(AlertType.ERROR, "uncomplete data", "Please fill namefield");
                        return;
                    }
                    if(passwordfield.getText() != null){
                        password = passwordfield.getText();
                    }
                    else{
                        showAlert(AlertType.ERROR, "uncomplete data", "Please fill passwordfield");
                        return;
                    }
                    mouth.println(username);
                    String Msg = ear.readLine();
                    if(Msg=="exist"){
                        mouth.println(password);
                        String Msg2 = ear.readLine();
                        if(Msg2=="true"){
                            changeScene(event, "/tick/tac/toe/game/view/OnlinePlayersListScreen.fxml");
                        }
                        else{
                            showAlert(AlertType.ERROR, "incorrect Password", "Please enter the right password");
                            return;
                        }
                        
                    }
                    else{
                        showAlert(AlertType.ERROR, "wrong username", "Please enter the right username");
                        return;
                    }
                } catch (IOException ex) {
                    //Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            });
        }catch(IOException ex){
              ex.printStackTrace();
         }
         
         
    }
    
    
    
    
    
    @FXML
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
