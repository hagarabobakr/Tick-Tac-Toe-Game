package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ChooseSymbolScreenController implements Initializable {

    @FXML
    private TextField Player1Choose;

    @FXML
    private TextField Player2Choose;

    @FXML
    private Button btnStartId;

    @FXML
    private Label player1;

    @FXML
    private Label player2;

    private String player1Symbol = "";
    private String player2Symbol = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Player1Choose.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSetChoice();
            }
        });

        Player2Choose.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handlePlayer2Choice();
            }
        });
    }

    @FXML
    private void handleSetChoice() {
        player1Symbol = Player1Choose.getText().trim().toUpperCase();

        if (player1Symbol.isEmpty()) {
            // Do not automatically set Player 2's symbol if Player 1's input is empty
            player2Symbol = "";
            Player2Choose.setText("");
            return;
        }

        if (!player1Symbol.equals("X") && !player1Symbol.equals("O")) {
            showAlert(AlertType.ERROR, "Invalid Choice", "Player 1 must enter 'X' or 'O'.");
            return;
        }

        // Set Player 2's choice
        player2Symbol = player1Symbol.equals("X") ? "O" : "X";
        Player2Choose.setText(player2Symbol);
    }

    @FXML
    private void handlePlayer2Choice() {
        player2Symbol = Player2Choose.getText().trim().toUpperCase();

        if (player2Symbol.isEmpty()) {
            // Do not automatically update Player 1's symbol if Player 2's input is empty
            player1Symbol = "";
            Player1Choose.setText("");
            return;
        }

        if (!player2Symbol.equals("X") && !player2Symbol.equals("O")) {
            showAlert(AlertType.ERROR, "Invalid Choice", "Player 2 must enter 'X' or 'O'.");
            return;
        }

        // Automatically update Player 1's choice based on Player 2's input
        player1Symbol = player2Symbol.equals("X") ? "O" : "X";
        Player1Choose.setText(player1Symbol);
    }

    @FXML
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (player1Symbol.isEmpty() || player2Symbol.isEmpty()) {
            showAlert(AlertType.ERROR, "Incomplete Choice", "Both players must choose a symbol before starting.");
            return;
        }

        if (player1Symbol.equals(player2Symbol)) {
            showAlert(AlertType.ERROR, "Invalid Choice", "Both players cannot have the same symbol.");
            return;
        }

        changeScene(event, "/tick/tac/toe/game/view/GameBoardScreen.fxml");
    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent parent = loader.load();

        GameBoardScreenController controller = loader.getController();
        controller.setPlayerSymbols(player1Symbol, player2Symbol);

        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
