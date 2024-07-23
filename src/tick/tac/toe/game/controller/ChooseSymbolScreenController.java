/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

;
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

/**
 *
 * @author ayaah
 */


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
        // Set a key listener on Player1Choose to handle input directly
        Player1Choose.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSetChoice();
            }
        });
    }

    @FXML
    private void handleSetChoice() {
        player1Symbol = Player1Choose.getText().trim().toUpperCase();

        // Validate input
        if (!player1Symbol.equals("X") && !player1Symbol.equals("O")) {
            showAlert(AlertType.ERROR, "Invalid Choice", "Please enter 'X' or 'O'.");
            return;
        }

        // Set Player 2's choice
        player2Symbol = player1Symbol.equals("X") ? "O" : "X";
        Player2Choose.setText(player2Symbol);
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
        if (event.getSource() == btnStartId) {
            changeScene(event, "/tick/tac/toe/game/view/GameBoardScreen.fxml");
        }
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
