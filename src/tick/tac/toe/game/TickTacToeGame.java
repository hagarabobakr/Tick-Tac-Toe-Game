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
import tick.tac.toe.game.controller.SoundManager;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.requestCreator;

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
        stage.setOnCloseRequest(e -> {
            if (Client.userName != null) {
                Client.sendRequest(requestCreator.logout());
                System.out.println("stage closed inside");
            }
            
            System.exit(0);
        });
        stage.show();
        // Play background music
        SoundManager.playBackgroundMusic();
    }

    @Override
    public void stop() {
        // Stop background music when the application is closed
        SoundManager.stopBackgroundMusic();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TickTacToeGame.class.getResource("/tick/tac/toe/game/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
