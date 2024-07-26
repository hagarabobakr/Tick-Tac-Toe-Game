/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tick.tac.toe.game.network.Client;

/**
 * FXML Controller class
 *
 * @author sammar alaa
 */
public class EnterIpScreenController implements Initializable {

    @FXML
    private VBox Box;
    @FXML
    private TextField txtIpAddress;
    @FXML
    private Button btnConnect;

    /**
     * Initializes the controller class.
     */
    private String ip = null;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnConnect) {
            ip = txtIpAddress.getText();
            if (!ip.equals("")) {
                Client.openConnection(ip);
                System.out.println(ip);
                if(!Client.mySocket.isClosed()){
                    changeScene(event,"/tick/tac/toe/game/view/Login$registerScreen.fxml");
                }
                else{
                    showAlert("Connection Failed", "Could not establish connection to the server.");
                }
            }
            else{
                showAlert("Connection Failed", "Could not establish connection to the server.");
            }
            
        }
    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
