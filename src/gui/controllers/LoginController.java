package gui.controllers;

import account.Profile;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
/*
    Controller used for the welcoming screen which allows
    users to select a profile or create a new one
 */
public class LoginController {
    @FXML HBox profileBox;

    /*
        Updates the main page with the profiles loaded
        during the initial boot up, and new profiles created
        during runtime
     */
    public void updatePage(ArrayList<Profile> profiles){
        profileBox.getChildren().clear();

        // Creates a button which links to each profile
        for(Profile profile : profiles){
            Button button = new Button(profile.getUserName());
            button.setPrefWidth(100);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                }
            });
        }
    }
}
