/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tick.tac.toe.game.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author mystore
 */
public class LoginScreenController implements Initializable {

    @FXML
    private AnchorPane Anchorpn;
    @FXML
    private VBox vbox;
    @FXML
    private GridPane gridpn;
    @FXML
    private ImageView nameicon;
    @FXML
    private Label namelabel;
    @FXML
    private Label passwordlabel;
    @FXML
    private ImageView passwordicon;
    @FXML
    private TextField passwordfield;
    @FXML
    private TextField namefield;
    @FXML
    private Button login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
