/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tick.tac.toe.game.network.ResponseListener;

/**
 * FXML Controller class
 *
 * @author Electronica Care
 */
public class WaitingForOthersScreenController implements Initializable,ResponseListener {

    @FXML
    private VBox Box;
    @FXML
    private ProgressBar proBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private void changeScene(String fxmlFile) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
    Scene scene = new Scene(parent);
    Stage stage = (Stage) Box.getScene().getWindow();  
    stage.setScene(scene);
    stage.show();
}
    @Override
    public void onResponse(String response) {
        JSONObject responseObject = (JSONObject) JSONValue.parse(response);
        if(responseObject.get("response").equals("invitationAccepted")){
            try {
                changeScene("/tick/tac/toe/game/view/InvitationScreen.fxml");
            } catch (IOException ex) {
                //Logger.getLogger(WaitingForOthersScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if(responseObject.get("response").equals("invitationDeclined")){
            
        }
    }
    
}
