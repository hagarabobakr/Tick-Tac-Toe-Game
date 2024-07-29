/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.network;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
/**
 *
 * @author Electronica Care
 */
public class requestCreator {
    
    public static String login(String userName,String password){
        JSONObject userData = new JSONObject();
        userData.put("username", userName);
        userData.put("password", password);
        JSONObject request = new JSONObject();
        request.put("request", "login");
        request.put("data", userData);
        return request.toString();
    }
    
    public static String register(String userName,String password){
        JSONObject userData = new JSONObject();
        userData.put("username", userName);
        userData.put("password", password);
        JSONObject request = new JSONObject();
        request.put("request", "register");
        request.put("data", userData);
        return request.toString();
    }
    public static String logout(){
     JSONObject userData = new JSONObject();
     JSONObject request = new JSONObject();
     if(Client.userName != null)
        userData.put("username", Client.userName);
        request.put("request", "logout");
        request.put("data", userData);
        return request.toString();
        
    }
    public static String getOnlinePlayersList(){
        JSONObject request = new JSONObject();
        request.put("request", "getOnlinePlayersList");
        return request.toString();
    }
//    public static String sendInvitation(String name){
//        JSONObject request = new JSONObject();
//        request.put("request", "sendInvitation");
//        request.put("name", name);
//        return request.toString();
//    }
    public static String sendInvitation(String invitedPlayerName) {
        JSONObject invitationData  = new JSONObject();
        invitationData.put("invitationSender", Client.userName);
        invitationData.put("invitationReceiver", invitedPlayerName);
        JSONObject request = new JSONObject();
        request.put("request", "sendInvitation");
        request.put("data", invitationData);
        return request.toString();
    }
    public static String acceptInvitation(String senderPlayerName) {
        JSONObject responseData = new JSONObject();
        responseData.put("acceptingPlayer", Client.userName);
        responseData.put("sender", senderPlayerName);
        JSONObject response = new JSONObject();
        response.put("request", "acceptInvitation");
        response.put("data", responseData);
        return response.toString();
    }
    public static String declineInvitation(String senderPlayerName) {
        JSONObject responseData = new JSONObject();
        responseData.put("decliningPlayer", Client.userName);
        responseData.put("sender", senderPlayerName);
        JSONObject response = new JSONObject();
        response.put("request", "declineInvitation");
        response.put("data", responseData);
        return response.toString();
    }
    
}
