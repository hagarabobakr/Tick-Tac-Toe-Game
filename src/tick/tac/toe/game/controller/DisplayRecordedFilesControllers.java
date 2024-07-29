/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ayaah
 */
public class DisplayRecordedFilesControllers implements Initializable {

    @FXML
    private Button btnStartId;
    @FXML
    private Button btnExiteId;
    @FXML
    private Label gameNameId;
    private String player1Symbol;
    private String player2Symbol;

    @FXML
    private ComboBox<String> recordedGamesComboBoxId;

    private static final String RECORDINGS_DIRECTORY = "C:/GameRecord";

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnStartId) {
            changeScene(event, "/tick/tac/toe/game/view/RecordGameOptionScreen.fxml");
        } else if (event.getSource() == btnExiteId) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recordedGamesComboBoxId.setVisible(true);
        recordedGamesComboBoxId.setOnAction(this::handleComboBoxAction);
        loadRecordedGames();
    }

    @FXML
    private void handleComboBoxAction(ActionEvent event) {
        String selectedFile = recordedGamesComboBoxId.getValue();
        if (selectedFile != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tick/tac/toe/game/view/GameBoardScreen.fxml"));
                Parent root = loader.load();

                GameBoardScreenController controller = loader.getController();
                Path filePath = Paths.get(RECORDINGS_DIRECTORY, selectedFile);
                controller.setShouldRecord(false, filePath.toString());
                controller.loadMovesFromFile(filePath);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadRecordedGames() {
        Path directory = Paths.get(RECORDINGS_DIRECTORY);

        System.out.println("Looking in directory: " + directory.toAbsolutePath());

        if (Files.exists(directory) && Files.isDirectory(directory)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, "*")) {
                List<String> recordedGames = new ArrayList<>();
                for (Path entry : stream) {
                    recordedGames.add(entry.getFileName().toString());
                }
                ObservableList<String> observableList = FXCollections.observableArrayList(recordedGames);
                recordedGamesComboBoxId.setItems(observableList);
            } catch (IOException e) {
                System.err.println("Error reading directory: " + e.getMessage());
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }

    public void setPlayerSymbols(String player1Symbol, String player2Symbol) {
        this.player1Symbol = player1Symbol;
        this.player2Symbol = player2Symbol;
    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent parent = loader.load();

        // Get the HomePAgeOfflineScreenController and pass the symbols
        RecordGameOptionScreenController controller = loader.getController();
        controller.setPlayerSymbols(player1Symbol, player2Symbol);

        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
