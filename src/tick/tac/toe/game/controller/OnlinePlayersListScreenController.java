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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Client.sendRequest(requestCreator.getOnlinePlayersList());
        ResponseHandler.setListener(this);
        
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String selectedItem = choosePlayer.getSelectionModel().getSelectedItem();
        //System.out.println(selectedItem);
        if(selectedItem != null){
            
        }
        else{
            System.out.println("empty");
        }
        
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
                players.add((String) data.get(i + ""));
            }
            //JSONArray playersArray = (JSONArray) responseObject.get("data");
            ObservableList<String> options = FXCollections.observableArrayList(players);
            choosePlayer.setItems(options);

        }

    }
}
