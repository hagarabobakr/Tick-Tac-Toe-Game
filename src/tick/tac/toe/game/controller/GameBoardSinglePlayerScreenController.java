/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author bebawy
 */
public class GameBoardSinglePlayerScreenController implements Initializable {

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
    private String difficultyLevel;

    private Button[] winningButtons;

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
        isGameOver = false;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (isGameOver) {
            return;
        }

        Button clickedButton = (Button) event.getSource();

        if (!clickedButton.getText().isEmpty()) {
            return;
        }

        clickedButton.setText("X");
        clickedButton.setStyle("-fx-text-fill: #FFD02D;");

        if (checkWin()) {
            xScore += 10;
            playerXscore.setText(String.valueOf(xScore));
            isGameOver = true;
            drawWinningLine();
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> moveToShowRewardVideoScreen());
            pause.play();
        } else if (isBoardFull()) {
            isGameOver = true;
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> moveToShowLoserVideoScreen());
            pause.play();
        } else {
            isXTurn = false;
            makeComputerMove();
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (checkThree(board[i][0], board[i][1], board[i][2])) {
                winningButtons = new Button[]{board[i][0], board[i][1], board[i][2]};
                return true;
            }
            if (checkThree(board[0][i], board[1][i], board[2][i])) {
                winningButtons = new Button[]{board[0][i], board[1][i], board[2][i]};
                return true;
            }
        }
        if (checkThree(board[0][0], board[1][1], board[2][2])) {
            winningButtons = new Button[]{board[0][0], board[1][1], board[2][2]};
            return true;
        }
        if (checkThree(board[0][2], board[1][1], board[2][0])) {
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
        isGameOver = false;
        isXTurn = playerxName.getText().equals("X");
        if (!isXTurn) {
            makeComputerMove();
        }
    }

    public void setPlayerSymbols(String player1Symbol, String player2Symbol) {
        playerxName.setText(player1Symbol);
        playerOname.setText(player2Symbol);
        isXTurn = player1Symbol.equals("X");
        if (!isXTurn) {
            makeComputerMove();
        }
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    private void makeComputerMove() {
        if (isGameOver) {
            return;
        }

        switch (difficultyLevel) {
            case "Easy":
                makeRandomMove();
                break;
            case "Medium":
                makeBestMove();
                break;
            case "Hard":
                makeBestMove();
                break;
        }

        if (checkWin()) {
            oScore += 10;
            playerOscore.setText(String.valueOf(oScore));
            isGameOver = true;
            drawWinningLine();
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> moveToShowLoserVideoScreen());
            pause.play();
        } else if (isBoardFull()) {
            isGameOver = true;
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> moveToShowLoserVideoScreen());
            pause.play();
        } else {
            isXTurn = true;
        }
    }

    private void makeRandomMove() {
        Random rand = new Random();
        while (true) {
            int row = rand.nextInt(3);
            int col = rand.nextInt(3);
            if (board[row][col].getText().isEmpty()) {
                board[row][col].setText("O");
                board[row][col].setStyle("-fx-text-fill: #FFD02D;");
                break;
            }
        }
    }

    private void makeMediumMove() {
        if (tryToWinOrBlock("O")) {
            return;
        }
        if (tryToWinOrBlock("X")) {
            return;
        }
        makeRandomMove();
    }

    private void makeBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().isEmpty()) {
                    board[i][j].setText("O");
                    int score = minimax(board, 0, false);
                    board[i][j].setText("");
                    board[i][j].setStyle("");
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        board[bestMove[0]][bestMove[1]].setText("O");
        board[bestMove[0]][bestMove[1]].setStyle("-fx-text-fill: #FFD02D;");
    }

    private int minimax(Button[][] board, int depth, boolean isMaximizing) {
        if (checkWin()) {
            return isMaximizing ? -1 : 1;
        }
        if (isBoardFull()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].getText().isEmpty()) {
                        board[i][j].setText("O");
                        int score = minimax(board, depth + 1, false);
                        board[i][j].setText("");
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].getText().isEmpty()) {
                        board[i][j].setText("X");
                        int score = minimax(board, depth + 1, true);
                        board[i][j].setText("");
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private boolean tryToWinOrBlock(String symbol) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (board[i][j].getText().isEmpty()) {
                board[i][j].setText(symbol);
                if (checkWin()) {
                    board[i][j].setText("");
                    return true;
                }
                board[i][j].setText("");
            }
        }
    }
    return false;
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
            Stage stage = (Stage) Btn11.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            // Stop the background music
            SoundManager.stopBackgroundMusic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveToShowLoserVideoScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tick/tac/toe/game/view/ShowLoserVideoScreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Btn11.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            // Stop the background music
            SoundManager.stopBackgroundMusic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleImageAction(MouseEvent event) throws IOException {
        changeScene_2(event, "/tick/tac/toe/game/view/ChooseLevelOfDifficultySinglePlayer.fxml"); // Assuming you want to go to the SplashScreen
    }

    private void changeScene_2(Event event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
