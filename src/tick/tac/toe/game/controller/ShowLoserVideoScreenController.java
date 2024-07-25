/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 *
 * @author ayaah
 */
public class ShowLoserVideoScreenController implements Initializable{

    @FXML
    private AnchorPane anchorId;

    @FXML
    private VBox vBoxId;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button btnContinue;

    @FXML
    private Label gameNameId;

    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Load the video file
            URL videoUrl = getClass().getResource("/tick/tac/toe/game/resources/video/loser.mp4");
            if (videoUrl == null) {
                throw new RuntimeException("Video file not found!");
            }

            String videoPath = videoUrl.toExternalForm();
            System.out.println("Video path: " + videoPath); // Print the video path

            Media media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            // Initially disable the continue button
            btnContinue.setDisable(true);
            System.out.println("Continue button initially disabled.");

            // Play the video
            mediaPlayer.play();

            // Add listener to detect when video ends
            mediaPlayer.setOnEndOfMedia(() -> {
                System.out.println("Video ended. Enabling continue button.");
                btnContinue.setDisable(false); // Enable the continue button when video ends
            });

            // Add listener for errors
            mediaPlayer.setOnError(() -> {
                System.out.println("Error occurred: " + mediaPlayer.getError().getMessage());
                // Enable the button if an error occurs
                btnContinue.setDisable(false);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleContinueAction() {
        System.out.println("Continue button clicked.");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        navigateToHomePage();
    }

    private void navigateToHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tick/tac/toe/game/view/HomePageScreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) anchorId.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
