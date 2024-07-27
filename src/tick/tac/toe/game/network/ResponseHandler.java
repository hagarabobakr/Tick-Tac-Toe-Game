/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.network;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tick.tac.toe.game.TickTacToeGame;
import tick.tac.toe.game.controller.LoginScreenController_1;

/**
 *
 * @author Electronica Care
 */
public class ResponseHandler {
     public static String response;
     private static ResponseListener listener;

    public static void setListener(ResponseListener responseListener) {
        listener = responseListener;
    }
    public static void handleResponse(String responseString) {
        JSONObject requestObject = (JSONObject) JSONValue.parse(responseString);
         response = (String) requestObject.get("response");
         if(response.equals("onlinePlayersList")){
             JSONObject responseData = new JSONObject();
             responseData.put("response", "onlinePlayersList");
             responseData.put("count",requestObject.get("count"));
             responseData.put("data",requestObject.get("data"));
             response = responseData.toString();
         }
        Platform.runLater(() -> {
            if (listener != null) {
                listener.onResponse(response);
            }
        });
    }
}