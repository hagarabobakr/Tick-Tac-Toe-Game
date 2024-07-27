package tick.tac.toe.game.controller;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SoundManager {

    private static MediaPlayer mediaPlayer;

    public static void playBackgroundMusic() {
        if (mediaPlayer == null) {
            URL resource = SoundManager.class.getResource("/tick/tac/toe/game/resources/sound/background_sound.mp3");
            if (resource == null) {
                System.err.println("Sound file not found!");
                return;
            }
            Media media = new Media(resource.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        }
        mediaPlayer.play();
    }

    public static void stopBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void pauseBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public static void resumeBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }
}
