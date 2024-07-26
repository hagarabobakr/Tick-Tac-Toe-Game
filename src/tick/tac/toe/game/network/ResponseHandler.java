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
        switch (response) {
            case "playerNotExists":
               // handlePlayerNotExist();
                break;
            case "wrongPassword":
               // handleWrongPassword();
                break;
            case "loginSuccess":
              handleWrongPassword();
                
                break;
            case "player already online":
                //handlePlayerAlreadyOnline(data);
                break;
            case "player exists":
                //handlePlayerExist();
                break;
            case "reqister sucsess":
                //handleRegisterSuccess();
                break;
            case "online players list":
                //onlinePlayersList(data);
                break;
            case "all players list":
                //handleAllPlayersList(data);
                break;
            case "add new player":
                //handleAddNewPlayer(data);
                break;
            case "player left the game":
                //handlePlayerLeftTheGame(data);
                break;
            case "player in game":
                //handlePlayerInGame(data);
                break;
            case "player is offline":
                //handlePlayerIsOffline(data);
                break;
            case "game invitation":
                //handleGameInvitation(data);
                break;
            case "invitationSended":
                //handleInvitationSended(data);
                break;
            case "invitationRejected":
                //handleInvitationRejected(data);
                break;
            case "choose x or o":
                //handleChooseXOrO(data);
                break;
            case "update player data":
                //handleUpdatePlayerData(data);
                break;
            case "updateAvilablePlayesList":
                //handleUpdateAvilablePlayersList(data);
                break;
            case "player left multi game":
                //handlePlayerLeftMultiGame(data);
                break;
            case "serverIsClosed":
                //handleServerIsClosed();
                break;
            case "multi mode game history":
                //handelMultiModeGameHistory(data);
                break;
            default:
                break;
        }
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

    private static void handleWrongPassword() {
        Platform.runLater(() -> {
            try {
                TickTacToeGame.setRoot("SplashScreen");
            } catch (IOException ex) {
                System.out.println("can't set WrongPasswordView");
            }
        });
    }
        
}
