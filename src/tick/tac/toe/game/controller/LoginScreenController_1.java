 /* To change this license header, choose License Headers in Project Properties.
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.requestCreator;

/**
 * FXML Controller class
 *
 * @author mystore
 */
public class LoginScreenController_1 implements Initializable {

    @FXML
    private PasswordField passwordfield;
    @FXML
    private TextField namefield;
    @FXML
    private ImageView backbtn;
    @FXML
    private Button loginbtn;

    /**
     * Initializes the controller class.
     */
    String username = null;
    String password = null;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == loginbtn) {
            username = namefield.getText();
            password = passwordfield.getText();
            if (!(username.equals("") && password.equals(""))) {
                Client.sendRequest(requestCreator.login(username, password));
            }
        } else {
            
            changeScene(event,"/tick/tac/toe/game/view/Login$registerScreen.fxml");
        }
    }
    

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}