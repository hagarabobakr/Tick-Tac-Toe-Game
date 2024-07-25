package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ShowRewardVideoScreenController implements Initializable {

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
            URL videoUrl = getClass().getResource("/tick/tac/toe/game/resources/video/rewerd.mp4");
            if (videoUrl == null) {
                throw new RuntimeException("Video file not found!");
            }

            String videoPath = videoUrl.toExternalForm();
            System.out.println("Video path: " + videoPath); // Print the video path

            Media media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            // Play the video
            mediaPlayer.play();

            // Add listener to detect when video ends
            mediaPlayer.setOnEndOfMedia(() -> {
                btnContinue.setDisable(false); // Enable the continue button when video ends
            });

            // Initially disable the continue button
            btnContinue.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleContinueAction() {
        // Stop the video and close the current stage
        mediaPlayer.stop();
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