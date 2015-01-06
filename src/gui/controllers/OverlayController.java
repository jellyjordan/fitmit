package gui.controllers;

import account.Session;
import gui.viewport.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
    Controller for the the profile overlay which is provided
    upon log in.
 */
public class OverlayController {
    @FXML Label nameLabel;
    @FXML Label dateLabel;

    /*
        Opens view to the profile hub which allows editing
        of the user's profile
     */
    public void openProfile(){
        ViewManager.loadView(ViewManager.PROFILEHUB_PAGE);
        ViewManager.loadStyle(ViewManager.PROFILEHUB_STYLE);
    }

    /*
        Loads the profile username into the overlay. Used after
        a user logs in.
     */
    public void setProfileLabel(){
        nameLabel.setText(Session.getProfile().getUserName());
    }

    /*
        Gets the current date from the time instance
        and sets a formatted string as the label's text
     */
    public void setDateLabel(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMMMMM , dd");
        Calendar today = Calendar.getInstance();

        dateLabel.setText(dateFormat.format(today.getTime()));
    }
}
