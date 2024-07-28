package tick.tac.toe.game.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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

    private boolean shouldRecord = false;
    private String filePath;

    private Button[] winningButtons;

    private String player1Symbol;
    private String player2Symbol;

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
        isXTurn = true;  // Player 1 starts the game
    }

    public void loadMovesFromFile(Path filePath) {
        new Thread(() -> {
            try {
                List<String> moves = Files.readAllLines(filePath);
                for (String move : moves) {
                    String[] parts = move.split(" - ");
                    if (parts.length == 2) {
                        String[] moveDetails = parts[1].split(": ");
                        if (moveDetails.length == 2) {
                            String buttonId = moveDetails[0].trim();
                            String symbol = moveDetails[1].trim();
                            Platform.runLater(() -> updateButtonWithSymbol(buttonId, symbol));
                            Thread.sleep(1000); // Adjust the sleep time as needed
                            
//                            // Check for a win after each move
//                            if (checkWin()) {
//                                Platform.runLater(this::drawWinningLine);
//                                break; // Stop processing further moves after a win
//                            }
                        }
                    }
                }
               Thread.sleep(2000); // Wait for 2 seconds after the last move
                Platform.runLater(this::moveToShowRewardVideoScreen);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private void updateButtonWithSymbol(String buttonId, String symbol) {
        Button button = getButtonById(buttonId);
        if (button != null) {
            button.setText(symbol);
            button.setStyle("-fx-text-fill: #FFD02D;");
        }
    }

    private Button getButtonById(String id) {
        switch (id) {
            case "Btn11":
                return Btn11;
            case "Btn12":
                return Btn12;
            case "Btn13":
                return Btn13;
            case "Btn21":
                return Btn21;
            case "Btn22":
                return Btn22;
            case "Btn23":
                return Btn23;
            case "Btn31":
                return Btn31;
            case "Btn32":
                return Btn32;
            case "Btn33":
                return Btn33;
            default:
                return null;
        }
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
            clickedButton.setText(player1Symbol);
            clickedButton.setStyle("-fx-text-fill: #FFD02D;");
            if (shouldRecord) {
                recordMove(clickedButton, "Player 1");
            }
        } else {
            clickedButton.setText(player2Symbol);
            clickedButton.setStyle("-fx-text-fill: #FFD02D;");
            if (shouldRecord) {
                recordMove(clickedButton, "Player 2");
            }
        }

        checkWinAndUpdateScore();
    }

    private void checkWinAndUpdateScore() {
        if (checkWin()) {
            if (isXTurn) {
                xScore += 10;
                playerXscore.setText(String.valueOf(xScore));
                drawWinningLine();
            } else {
                oScore += 10;
                playerOscore.setText(String.valueOf(oScore));
                drawWinningLine();
            }
            isGameOver = true;  // Set the game over flag

            waitForFiveSecondsAndMoveToShowRewardVideoScreen();  // Wait for three seconds then move to reward video screen
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
                winningButtons = new Button[]{board[i][0], board[i][1], board[i][2]};
                return true;
            } else if (checkThree(board[0][i], board[1][i], board[2][i])) {
                winningButtons = new Button[]{board[0][i], board[1][i], board[2][i]};
                return true;
            }
        }
        // Check diagonals
        if (checkThree(board[0][0], board[1][1], board[2][2])) {
            winningButtons = new Button[]{board[0][0], board[1][1], board[2][2]};
            return true;
        } else if (checkThree(board[0][2], board[1][1], board[2][0])) {
            winningButtons = new Button[]{board[0][2], board[1][1], board[2][0]};
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
        isXTurn = player1Symbol.equals("X");
    }

    public void setPlayerSymbols(String player1Symbol, String player2Symbol) {
        this.player1Symbol = player1Symbol;
        this.player2Symbol = player2Symbol;
        playerxName.setText(player1Symbol);
        playerOname.setText(player2Symbol);
        // Ensure Player 1 always starts the game
        isXTurn = true;
    }

    private void drawWinningLine() {
        for (Button button : winningButtons) {
            button.setStyle("-fx-background-color: yellow;");
        }
    }

    private void moveToShowRewardVideoScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tick/tac/toe/game/view/ShowRewardVideoScreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Btn11.getScene().getWindow();  // Get the current stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            SoundManager.pauseBackgroundMusic();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForFiveSecondsAndMoveToShowRewardVideoScreen() {
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
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

    public void setShouldRecord(boolean shouldRecord, String filePath) {
        this.shouldRecord = shouldRecord;
        this.filePath = filePath;
        if (shouldRecord) {
            System.out.println("Recording moves is enabled.");
            System.out.println("File Path: " + Paths.get(filePath).toAbsolutePath());
        } else {
            System.out.println("Recording moves is disabled.");
        }
    }

    private void recordMove(Button clickedButton, String playerName) {
        String move = playerName + " - " + clickedButton.getId() + ": " + clickedButton.getText();
        try {
            Path path = Paths.get(filePath);
            Files.write(path, (move + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            System.out.println("Recorded move: " + move);
        } catch (IOException e) {
            System.err.println("Failed to record move: " + e.getMessage());
        }
    }
    
   
}
