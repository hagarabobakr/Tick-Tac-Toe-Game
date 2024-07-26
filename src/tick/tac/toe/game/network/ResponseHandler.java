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

/**
 *
 * @author Electronica Care
 */
public class ResponseHandler {
   public static void handleResponse(String responseString) {

        JSONObject requestObject = (JSONObject) JSONValue.parse(responseString);
         String response = (String) requestObject.get("response");
        JSONObject data = (JSONObject) requestObject.get("data");
        Platform.runLater(() -> {
            switch (response) {
                case "loginSuccess":
                    try {
                        TickTacToeGame.setRoot("SplashScreen");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "playerNotExists":
                    // handlePlayerNotExist();
                    break;
                case "wrongPassword":
                    // handleWrongPassword();
                    break;
                default:
                    break;
            }
       });
    }
    private static void handlePlayerNotExist() {
        Platform.runLater(() -> {
            try {
                TickTacToeGame.setRoot("PlayerNotExistView");
            } catch (IOException ex) {
                System.out.println("can't set player not exits view");
            }
        });
    }
        
}
