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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import tick.tac.toe.game.network.Client;

/**
 * FXML Controller class
 *
 * @author sammar alaa
 */
public class EnterIpScreenController implements Initializable {

    @FXML
    private VBox Box;
    @FXML
    private TextField txtIpAddress;
    @FXML
    private Button btnConnect;

    /**
     * Initializes the controller class.
     */
    private String ip=null;
    
    @FXML
    public void getIPAdress(){
        ip = txtIpAddress.getText();
        if(ip != null){
            Client.openConnection(ip);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
