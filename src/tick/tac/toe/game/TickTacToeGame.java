/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.RequestCreator;
import tick.tac.toe.game.network.ResponseListener;

/**
 *
 * @author bebawy
 */
public class TickTacToeGame extends Application {
    public static Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/tick/tac/toe/game/view/SplashScreen.fxml"));
        
         scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // Client.openConnection("localhost"); 
       // String loginRequest = requestCreator.login("sammar", "1234");
       // Client.sendRequest(loginRequest);
        launch(args);
    }
    public static void setRoot(String fxml,ResponseListener controller) throws IOException {
        scene.setRoot(loadFXML(fxml, controller));
    }

    private static Parent loadFXML(String fxml,ResponseListener controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TickTacToeGame.class.getResource("/tick/tac/toe/game/view/" + fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }
    
}
