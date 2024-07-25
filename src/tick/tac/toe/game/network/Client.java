/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Electronica Care
 */
public class Client {

    public static Socket mySocket;
    public static DataInputStream inputStream ;
    public static PrintStream printstream ;
    
     public static void openConnection() {
        
        try {
            mySocket = new Socket("localhost", 5005);
            inputStream = new DataInputStream(mySocket.getInputStream());
            printstream = new PrintStream(mySocket.getOutputStream());
            System.out.println("connection opened");
            AcceptResponses();
        } catch (IOException e) {
            //closeEveryThing(); function();
        }
    }
     
    private static void AcceptResponses(){
        //thread while(true)
        new Thread(() -> {
            try {
                String response;
                while (mySocket.isConnected() && (response = inputStream.readLine()) != null) {
                   // ResponseHandler.handleResponse(response);
                }
            } catch (IOException ex) {
                System.out.println("connection lost");
                //closeEveryThing();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("connection lost problem in accept response");
                //closeEveryThing();
            }
        }).start();
    }
    public static void sendRequest(String request){
        if(request == null)
            return;
        printstream.println(request);
    
    }
}
