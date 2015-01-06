package gui.controllers;

import account.Session;
import database.DatabaseCommunicator;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import nutrition.DailyLog;
import nutrition.FoodEntry;

import java.util.ArrayList;

/*
    Controller used for all functions in the daily log which
    add/remove foods from the profile's associated daily log
 */
public class DailyLogController {
    @FXML GridPane rootPane;
    @FXML TableView nutritionTableView;
    @FXML ComboBox foodSearch;
    @FXML TextField weightField;
    @FXML Label calGoalLabel;
    @FXML Label calCurrentLabel;
    @FXML Label carbGoalLabel;
    @FXML Label carbCurrentLabel;
    @FXML Label fatGoalLabel;
    @FXML Label fatCurrentLabel;
    @FXML Label proGoalLabel;
    @FXML Label proCurrentLabel;

    /*
        Uses the foodSearch textfield's current input when a key is pressed
        to suggest a full item name containing the text.
     */
    public void suggestItem(ActionEvent ev){
        String currentInput = foodSearch.getEditor().getText();
        if(currentInput.length() > 4){
            DatabaseCommunicator.openConnection();

            // Returns a list no greater than size 5 of possible food items
            ArrayList<String> foodList = DatabaseCommunicator.findSimilarFoods(currentInput);

            /*
                Clearing the observablelist causes an exception when an
                item is selected. Running the clear "later" provides time
                for the selection model to make it's changes.
             */
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ObservableList<String> comboList = foodSearch.getItems();
                    comboList.setAll(foodList);
                }
            });


            DatabaseCommunicator.closeConnection();
        }

    }
    /*
        Adds the food item retrieved from the query using the
        food name in the foodSearch textfield
     */
    public void addItem(){
        // Verifies the weight is numeric
        String weightInput = weightField.getText();
        if(!isNumber(weightInput) || weightInput.length() == 0){
            return;
        }
        // Opens connection to database
        DatabaseCommunicator.openConnection();

        int weight = Integer.parseInt(weightField.getText());
        String foodName = foodSearch.getEditor().getText();

        // Adds item if database found an item
        FoodEntry foodEntry = DatabaseCommunicator.createFoodEntry(foodName ,weight);
        if(foodEntry != null){
            Session.getProfile().getDailyLog().addEntry(foodEntry);

            // Updates the gui
            updateCurrentLabels();
            refreshList();
        }
        // Closes connection to database
        DatabaseCommunicator.closeConnection();
    }

    /*
        Removes an item selected in the tableview and updates
        the daily nutritional intake to match the update
     */
    public void removeItem(){
        // Gets the clicked entry from the tableview
        FoodEntry clickedEntry = (FoodEntry)nutritionTableView.getSelectionModel().getSelectedItem();

        // Removes the item from the user's daily log
        Session.getProfile().getDailyLog().removeEntry(clickedEntry);

        // Updates the gui
        updateCurrentLabels();
        refreshList();
    }

    /*
        Updates the current labels with the totals from the user's daily log
     */
    public void updateCurrentLabels(){
        // Gets the reference to the current dailyLog
        DailyLog dailyLog = Session.getProfile().getDailyLog();

        // Updates the current total labels with the foods in the dailyLog
        calCurrentLabel.setText(Short.toString(dailyLog.getCurrentCalories()));
        carbCurrentLabel.setText(Short.toString(dailyLog.getCurrentCarbohydrates()));
        fatCurrentLabel.setText(Short.toString(dailyLog.getCurrentFats()));
        proCurrentLabel.setText(Short.toString(dailyLog.getCurrentProtein()));
    }

    public void updateTotalLables(){
        // Gets the reference to the current dailyLog
        DailyLog dailyLog = Session.getProfile().getDailyLog();

        // Updates the current total labels with the foods in the dailyLog
        calGoalLabel.setText(Short.toString(dailyLog.getCalorieLimit()));
        carbGoalLabel.setText(Short.toString(dailyLog.getCarbohydrateLimit()));
        fatGoalLabel.setText(Short.toString(dailyLog.getFatLimit()));
        proGoalLabel.setText(Short.toString(dailyLog.getProteinLimit()));
    }

    /*
        Refreshes the listview when a food entry is addedd
        or deleted from the daily log
     */
    protected void refreshList(){
        /*
            Gets the list of FoodEntries from the daily log, and fetches the tableview's
            list of objects
         */
        ArrayList<FoodEntry> foodEntries = Session.getProfile().getDailyLog().getFoodEntries();
        ObservableList<FoodEntry> tableEntries = nutritionTableView.getItems();

        tableEntries.clear();
        tableEntries.addAll(foodEntries);
    }

    /*
        Checks if the input is a number
     */
    private boolean isNumber(String input){
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) < '0' || input.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }
}
