/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.network;

import java.io.IOException;
import javafx.application.Platform;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tick.tac.toe.game.TickTacToeGame;
import tick.tac.toe.game.controller.LoginScreenController_1;

/**
 *
 * @author Electronica Care
 */
public class ResponseHandler {
    public static void handleResponse(String responseString) {
        JSONObject requestObject = (JSONObject) JSONValue.parse(responseString);
        String response = (String) requestObject.get("response");

        Platform.runLater(() -> {
            if (response.equals("loginSuccess")) { // Use 'response' here, not 'responseString'
                try {
                    // Instead of creating a new controller, you should use the one that JavaFX uses with the FXML
                    LoginScreenController_1 loginController = new LoginScreenController_1();
                    TickTacToeGame.setRoot("HomePageScreen", loginController);
                    loginController.onResponse(responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
    
        

