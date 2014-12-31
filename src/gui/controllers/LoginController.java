package gui.controllers;

import account.Profile;
import account.ProfileParser;
import account.Session;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
/*
    Controller used for the welcoming screen which allows
    users to select a profile or create a new one
 */
public class LoginController {
    @FXML VBox profileBox;
    @FXML HBox newUserBox;
    @FXML TextField newUserField;

    /*
        Opens options which allows the user to create
        a new profile
     */
    public void createUser(){
        newUserBox.setVisible(true);
    }

    /*
        Creates the profile with the supplied username if
        it does note exist, than updates the profiles listed
     */
    public void submitUser(){
        newUserBox.setVisible(false);
        Profile newUser = new Profile(newUserField.getText());
        ProfileParser.saveProfile(newUser);
        updatePage(ProfileParser.loadProfiles());
    }

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
                    // Gets the reference of the button causing the event
                    Button pressedButton = (Button) mouseEvent.getSource();

                    // Loads the profile with the username which is the button's text
                    Profile clickedProfile = ProfileParser.getProfile(pressedButton.getText());

                    // Binds the profile to the session, so long as no other profile is assigned
                    Session.getSession().setProfile(clickedProfile);
                    System.out.println(clickedProfile.getUserName());
                }
            });
            profileBox.getChildren().add(button);
        }
    }
}
