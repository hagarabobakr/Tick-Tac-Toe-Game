/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.ResponseHandler;
import tick.tac.toe.game.network.ResponseListener;
import tick.tac.toe.game.network.requestCreator;

/**
 * FXML Controller class
 *
 * @author Electronica Care
 */
public class GameBoardOnlineModeSenderScreenController implements Initializable, ResponseListener {

    @FXML
    private Text playerXscore;
    @FXML
    private Text playerxName;
    @FXML
    private Button Btn11;
    @FXML
    private Button Btn12;
    @FXML
    private Button Btn13;
    @FXML
    private Button Btn21;
    @FXML
    private Button Btn22;
    @FXML
    private Button Btn23;
    @FXML
    private Button Btn31;
    @FXML
    private Button Btn32;
    @FXML
    private Button Btn33;
    @FXML
    private Text playerOscore;
    @FXML
    private Text playerOname;

    public static boolean isXTurn;
    public static boolean isGameOver;
    private int xScore = 0;
    private int oScore = 0;
    private Button[][] board;
    private Button[] winningButtons;
    public String senderName;
    public String reciverName;
    private String player1Symbol = "X";
    private String player2Symbol = "O";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
            // TODO
       playerxName.setText("sara");
     playerOname.setText("sara");
        
        playerxName.setVisible(true);
        playerOname.setVisible(true);
        playerXscore.setText(String.valueOf(xScore));
        playerOscore.setText(String.valueOf(oScore));

        board = new Button[][]{
            {Btn11, Btn12, Btn13},
            {Btn21, Btn22, Btn23},
            {Btn31, Btn32, Btn33}
        };
        isGameOver = false;  // Initialize the game over flag
        isXTurn = true;  // Player 1 starts the game

        ResponseHandler.setListener(this);
        

    }

    void setSenderName(String senderName) {
        this.senderName = senderName;
        playerxName.setText(senderName);
//    playerXscore.setText(String.valueOf(xScore));
//    playerxName.setVisible(true);
//    playerOname.setVisible(reciverName != null && !reciverName.isEmpty()); // Hide/Show playerOname based on its value
    }

    void setReciverName(String reciverName) {
        this.reciverName = reciverName;
        playerOname.setText(reciverName);
        //Client.sendRequest(requestCreator.getInvitedPlayers(reciverName));
//    playerOscore.setText(String.valueOf(oScore));
//    playerOname.setVisible(true);
//    playerxName.setVisible(senderName != null && !senderName.isEmpty()); // Hide/Show playerxName based on its value
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
      //  if (isXTurn) {
            clickedButton.setText("X");
            clickedButton.setStyle("-fx-text-fill: #FFD02D;");

            Client.sendRequest(requestCreator.sendMove(InvitationScreenController.reciverName, "X", clickedButton.getId()));
       // }
        disableButton(true);
        //String symbol = isXTurn ? player1Symbol : player2Symbol;
        isXTurn = !isXTurn;

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
            // isXTurn = !isXTurn;
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "No one wins the game.", ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                resetBoard();
            }
        });
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

    private void disableButton(boolean d) {
        Btn11.setDisable(d);
        Btn12.setDisable(d);
        Btn13.setDisable(d);

        Btn21.setDisable(d);
        Btn22.setDisable(d);
        Btn23.setDisable(d);

        Btn31.setDisable(d);
        Btn32.setDisable(d);
        Btn33.setDisable(d);
    }

    @Override
    public void onResponse(String response) {
        try {
            // Thread.sleep(1000);

            Platform.runLater(() -> {
                JSONObject requestObject = (JSONObject) JSONValue.parse(response);
                String r = (String) requestObject.get("response");
                JSONObject data = (JSONObject) requestObject.get("data");

                if (r.equals("sendMove")) {
                    String s=(String) data.get("sympol");
                    if(s.equals("O")){
                    Button b = getButtonById((String) data.get("btn"));
                    b.setText(s);
                    System.out.println("from onResponse");
                    disableButton(false);
                    }
                } 
//                else if (requestObject.get("response").equals("getPlayersInvited")) {
//                    //JSONObject  data = (JSONObject) requestObject.get("data");
//                    senderName = (String) data.get("sender");
//                }
            });
        } catch (Exception ex) {
            Logger.getLogger(GameBoardOnlineModeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleIncomingMove(String playerName, String symbol, String buttonId) {
        Button button = getButtonById(buttonId);
        if (button != null && button.getText().isEmpty()) {
            button.setText(symbol);
            button.setStyle("-fx-text-fill: #FFD02D;");
            checkWinAndUpdateScore();
            isXTurn = !isXTurn;
        }
    }

}
