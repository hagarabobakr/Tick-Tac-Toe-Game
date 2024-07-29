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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import tick.tac.toe.game.network.ResponseListener;

/**
 * FXML Controller class
 *
 * @author Electronica Care
 */
public class InvitationScreenController implements Initializable,ResponseListener {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button AcceptBtn;
    
    @FXML
    private Button RefuseBtn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == AcceptBtn) {
            
        }
        if (event.getSource() == RefuseBtn) {
            
        }
        
    }
    @Override
    public void onResponse(String response) {
        
    }
}
