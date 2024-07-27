package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tick.tac.toe.game.network.Client;
import tick.tac.toe.game.network.ResponseHandler;
import tick.tac.toe.game.network.requestCreator;
import tick.tac.toe.game.network.ResponseListener;

public class LoginScreenController_1 implements Initializable, ResponseListener {

    @FXML
    private PasswordField passwordfield;
    @FXML
    private TextField namefield;
    @FXML
    private ImageView backbtn;
    @FXML
    private Button loginbtn;
    private static volatile String r;
    private String username = null;
    private String password = null;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == loginbtn) {
            username = namefield.getText();
            password = passwordfield.getText();
            if (!(username.equals("") && password.equals(""))) {
                // Send login request to the server
                Client.sendRequest(requestCreator.login(username, password));
                
                    try {
                        Thread.sleep(1000);
                        
                        Platform.runLater(() -> {
                            if ("loginSuccess".equals(r)) {
                                try {
                                    System.out.println(r);
                                    
                                    changeScene(event, "/tick/tac/toe/game/view/OnlinePlayersListScreen.fxml");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Login Failed");
                                alert.setHeaderText(null);
                                    if(r.equals("playerNotExists")){
                                        alert.setContentText("Login failed! user name not exist");
                                    }
                                    else if(r.equals("wrongPassword")) {
                                         alert.setContentText("Login failed! wrong password");
                                        
                                    }
                               
                                alert.showAndWait();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
               // }).start();
            }
        } else {
            // Change scene if another button is clicked
        }
    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the controller, if needed
        ResponseHandler.setListener(this);
    }

    @Override
    public void onResponse(String response) {
        if (response.equals("loginSuccess")) {
            r = "loginSuccess";
        } else if(response.equals("playerNotExists")) {
            r = "playerNotExists";
        }
        else if(response.equals("wrongPassword")){
            r="wrongPassword";
        }
          
    }

}
