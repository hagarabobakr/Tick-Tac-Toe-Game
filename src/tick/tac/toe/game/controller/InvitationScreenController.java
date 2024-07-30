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
import javafx.stage.Stage;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.ResponseListener;
import tick.tac.toe.game.network.requestCreator;

/**
 * FXML Controller class
 *
 * @author Electronica Care
 */
public class InvitationScreenController implements Initializable, ResponseListener {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button AcceptBtn;

    @FXML
    private Button RefuseBtn;
    String senderName, reciverName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    void setSenderName(String senderName) {
        this.senderName = senderName;
        System.out.println( this.senderName);
    }

    void setReciverName(String reciverName) {
        this.reciverName = reciverName;
        System.out.println( this.reciverName);
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == AcceptBtn) {
            Client.sendRequest(requestCreator.acceptInvitation(senderName));
            System.out.println(senderName);
            changeScene(event, "/tick/tac/toe/game/view/GameBoardScreen.fxml");

        }
        if (event.getSource() == RefuseBtn) {
             Client.sendRequest(requestCreator.declineInvitation(senderName));

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
    public void onResponse(String response) {

    }

    
}
