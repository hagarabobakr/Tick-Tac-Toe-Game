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
    public static DataInputStream inputStream;
    public static PrintStream printStream;
    public static String response;
    public static String userName;

    public static void openConnection(String ip) {

        try {
            mySocket = new Socket(ip, 5005);
            inputStream = new DataInputStream(mySocket.getInputStream());
            printStream = new PrintStream(mySocket.getOutputStream());
            System.out.println("connection opened");

            AcceptResponses();
        } catch (IOException e) {
            closeConnection();
            e.printStackTrace();
        }
    }

    private static void AcceptResponses() {
        //thread while(true)
        new Thread(() -> {
            try {
                //String response;
                while (mySocket.isConnected() && (response = inputStream.readLine()) != null) {
                    //adding response handler
                    ResponseHandler.handleResponse(response);
                    System.out.println(response);
                }
            } catch (IOException ex) {
                System.out.println("connection lost");
                closeConnection();
                ex.printStackTrace();
            } catch (Exception e) {
                closeConnection();
                e.printStackTrace();
                System.out.println("connection lost problem in accept response");

            }
        }).start();
    }

    public static void sendRequest(String request) {
        if (request == null) {
            return;
        }
        System.out.println(request);
        printStream.println(request);

    }

    public static void closeConnection() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (printStream != null) {
                printStream.close();
            }
            if (mySocket != null) {
                mySocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
