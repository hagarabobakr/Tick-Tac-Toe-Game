package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameBoardScreenController implements Initializable {

    @FXML
    private Button Btn11, Btn12, Btn13, Btn21, Btn22, Btn23, Btn31, Btn32, Btn33;

    @FXML
    private Text playerXscore, playerOscore;

    @FXML
    private Label playerxName, playerOname;

    private boolean isXTurn;
    private boolean isGameOver;
    private int xScore = 0;
    private int oScore = 0;
    private Button[][] board;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerxName.setVisible(false);
        playerOname.setVisible(false);
        playerXscore.setText(String.valueOf(xScore));
        playerOscore.setText(String.valueOf(oScore));

        board = new Button[][]{
            {Btn11, Btn12, Btn13},
            {Btn21, Btn22, Btn23},
            {Btn31, Btn32, Btn33}
        };
        isGameOver = false;  // Initialize the game over flag
        isXTurn = true;  // Set initial turn to X
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (isGameOver) {
            return;  // Do nothing if the game is over
        }

        Button clickedButton = (Button) event.getSource();

        if (!clickedButton.getText().isEmpty()) {
            return; // Button already clicked
        }

        if (isXTurn) {
            clickedButton.setText("X");
            clickedButton.setStyle("-fx-text-fill: #FFD02D;");
        } else {
            clickedButton.setText("O");
            clickedButton.setStyle("-fx-text-fill: #FFD02D;");
        }

        if (checkWin()) {
            if (isXTurn) {
                xScore += 10;
                playerXscore.setText(String.valueOf(xScore));
            } else {
                oScore += 10;
                playerOscore.setText(String.valueOf(oScore));
            }
            isGameOver = true;  // Set the game over flag
            waitForTenSecondsAndMoveToShowRewardVideoScreen();  // Wait for three seconds then move to reward video screen
        } else if (isBoardFull()) {
            isGameOver = true;  // Set the game over flag if board is full
            showAlertAndReset();  // Show alert and reset game for a draw
        } else {
            isXTurn = !isXTurn;
        }
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkThree(board[i][0], board[i][1], board[i][2])) {
                return true;
            } else if (checkThree(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        // Check diagonals
        if (checkThree(board[0][0], board[1][1], board[2][2])) {
            return true;
        } else if (checkThree(board[0][2], board[1][1], board[2][0])) {
            return true;
        }
        return false;
    }

    private boolean checkThree(Button b1, Button b2, Button b3) {
        String s1 = b1.getText();
        return !s1.isEmpty() && s1.equals(b2.getText()) && s1.equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (Button[] row : board) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (Button[] row : board) {
            for (Button button : row) {
                button.setText("");
            }
        }
        isGameOver = false;  // Reset the game over flag
        // Start the new game with the same initial player
        isXTurn = playerxName.getText().equals("X");
    }

    public void setPlayerSymbols(String player1Symbol, String player2Symbol) {
        playerxName.setText(player1Symbol);
        playerOname.setText(player2Symbol);
        // Set initial turn based on player 1's symbol
        isXTurn = player1Symbol.equals("X");
    }

    private void moveToShowRewardVideoScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tick/tac/toe/game/view/ShowRewardVideoScreen1.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Btn11.getScene().getWindow();  // Get the current stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void waitForTenSecondsAndMoveToShowRewardVideoScreen() {
        PauseTransition pause = new PauseTransition(Duration.seconds(10));
        pause.setOnFinished(event -> moveToShowRewardVideoScreen());
        pause.play();
    }


    private void showAlertAndReset() {
        Alert alert = new Alert(AlertType.INFORMATION, "No one wins the game.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                resetBoard();
            }
        });
    }
}