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
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.ResponseHandler;
import tick.tac.toe.game.network.ResponseListener;
import tick.tac.toe.game.network.requestCreator;

/**
 * FXML Controller class
 *
 * @author Electronica Care
 */
public class WaitingForOthersScreenController implements Initializable, ResponseListener {

    @FXML
    private VBox Box;
    @FXML
    private ProgressBar proBar;
    public static String ReciverName ,senderName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ResponseHandler.setListener(this);
                Client.sendRequest(requestCreator.getInvitedPlayers(ReciverName));


    }

    private void changeScene(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent parent = loader.load();
         GameBoardOnlineModeSenderScreenController controller = loader.getController();
        controller.setReciverName(ReciverName);
        controller.setSenderName(senderName);
        Scene scene = new Scene(parent);
        Stage stage = (Stage) Box.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void onResponse(String response) {
        JSONObject responseObject = (JSONObject) JSONValue.parse(response);
        ReciverName = (String) responseObject.get("acceptingPlayer");
        System.out.println("-----------" + ReciverName + "from waiting ");
        if (responseObject.get("response").equals("invitationAccepted")) {
            try {

                changeScene("/tick/tac/toe/game/view/GameBoardOnlineModeSenderScreen.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
                //Logger.getLogger(WaitingForOthersScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (responseObject.get("response").equals("invitationDeclined")) {
            System.out.println("invitationDeclined from onresponse");
        }
        else if(responseObject.get("response").equals("getPlayersInvited")){
          JSONObject  data = (JSONObject) responseObject.get("data");
           senderName =(String) data.get("sender");
        }
    }

}
