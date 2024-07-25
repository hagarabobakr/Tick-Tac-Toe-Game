package tick.tac.toe.game.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RecordGameOptionScreenController implements Initializable {

//    @FXML
//    private VBox vbox;
//    @FXML
//    private Label TIClabel;
//    @FXML
//    private CheckBox checkrecord;
//    @FXML
//    private Button Start;
//
//    private boolean shouldRecord = false;
//    private String filePath;
//
//    @FXML
//    private void handleButtonAction(ActionEvent event) throws IOException {
//        if (event.getSource() == Start) {
//            shouldRecord = checkrecord.isSelected();
//            if (shouldRecord) {
//                try {
//                    // Generate a filename based on current date and time
//                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
//                    filePath = "D:/Tick-Tac-Toe-Game/src/tick/tac/toe/game/GameRecord/GameRecord_" + timestamp;
//                    
//                    Path path = Paths.get(filePath);
//                    // Ensure the directory exists
//                    Files.createDirectories(path.getParent());
//                    // Create or reset the file
//                    Files.write(path, "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
//                    System.out.println("File for recording moves opened successfully.");
//                    System.out.println("File Path: " + path.toAbsolutePath());
//                } catch (IOException e) {
//                    System.err.println("Failed to open file for recording moves: " + e.getMessage());
//                }
//            }
//            changeScene(event, "/tick/tac/toe/game/view/GameBoardScreen.fxml");
//        }
//    }
//
//    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//        Parent parent = loader.load();
//        Scene scene = new Scene(parent);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//
//        // Pass the shouldRecord flag and file path to the new controller
//        if (fxmlFile.contains("GameBoardScreen")) {
//            GameBoardScreenController controller = loader.getController();
//            controller.setShouldRecord(shouldRecord, filePath);
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code if needed
    }
}
