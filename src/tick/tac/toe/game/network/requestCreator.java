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
        String userName = Client.userName;
        userData.put("request","logout");
        userData.put("username", userName);
        return userData.toString();
    }
    public static String getOnlinePlayersList(){
        JSONObject request = new JSONObject();
        request.put("request", "getOnlinePlayersList");
        return request.toString();
    }
    public static String sendInvitation(String name){
        JSONObject request = new JSONObject();
        request.put("request", "sendInvitation");
        request.put("name", name);
        return request.toString();
    }
    
}
