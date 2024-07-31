/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
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
public class OnlinePlayersListScreenController implements Initializable, ResponseListener {

    @FXML
    private VBox Box;
    @FXML
    private Label gameNameId;
    @FXML
    private ImageView backbtn;
    @FXML
    private Text txtOnlinePlayersId;
    @FXML
    private ListView<String> onlinePlayersListView;
    @FXML
    private Button btnContinueId;
    @FXML
    private ComboBox<String> choosePlayer;
    // public static String r;
    public JSONObject data;
    public long size;
    public ArrayList<String> players = new ArrayList<>();
    private static volatile String r;
    private String senderName;
    private String reciverName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Client.sendRequest(requestCreator.getOnlinePlayersList());
        ResponseHandler.setListener(this);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String selectedItem = choosePlayer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            try {
                Client.sendRequest(requestCreator.sendInvitation(selectedItem));
                Thread.sleep(1000);
                changeScene(event, "/tick/tac/toe/game/view/WaitingForOthersScreen.fxml");
            } catch (InterruptedException ex) {
                Logger.getLogger(OnlinePlayersListScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("wrong");
        }

    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent parent = loader.load();
        WaitingForOthersScreenController con = loader.getController();
        con.ReciverName = reciverName;
        con.senderName = senderName;
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void changeScene(String fxmlFile, String styleSheet) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent parent = loader.load();
        InvitationScreenController controller = loader.getController();
        controller.setSenderName(senderName);
        controller.setReciverName(reciverName);
        //System.out.println(senderName + " " + reciverName + " from changeScene");

        parent.getStylesheets().add(styleSheet);
        Scene scene = new Scene(parent);
        Stage stage = (Stage) Box.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void changeScene(String fxmlFile) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) Box.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleImageAction(MouseEvent event) {
    }

    @Override
    public void onResponse(String response) {

        JSONObject responseObject = (JSONObject) JSONValue.parse(response);
        if (responseObject.get("response").equals("onlinePlayersList")) {
            data = (JSONObject) responseObject.get("data");
            size = (long) responseObject.get("count");

            for (int i = 0; i < size; i++) {
                String player = (String) data.get(i + "");
                if (player != Client.userName) {
                    players.add(player);
                }
            }
            //JSONArray playersArray = (JSONArray) responseObject.get("data");
            ObservableList<String> options = FXCollections.observableArrayList(players);
            choosePlayer.setItems(options);

        } else if (responseObject.get("response").equals("invitationSent")) {
            Platform.runLater(() -> {
                try {

                    JSONObject data = (JSONObject) responseObject.get("data");
                    senderName = (String) data.get("sender");
                    reciverName = (String) data.get("receiver");
                    System.out.println((String) data.get("sender") + " sender from on response");

                    changeScene("/tick/tac/toe/game/view/InvitationScreen.fxml", "resources/styles/general.css");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

    }

    private void navigateToWaitingScreen() {
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
