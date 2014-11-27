package nutrition;

import account.Profile;

import java.io.Serializable;
import java.util.ArrayList;

/*
  Daily Log object is created in initialization process
  and can be configured to scale with the users' program
  schedule and their goals.
 */
public class DailyLog implements Serializable{
    private short calorieLimit;
    private short proteinLimit;
    private short carbohydrateLimit;
    private short fatLimit;
    private short currentCalories;
    private short currentProtein;
    private short currentFats;
    private short currentCarbohydrates;
    transient private ArrayList<FoodEntry> foodEntries;

    public DailyLog(){
        foodEntries = new ArrayList<FoodEntry>();
        calorieLimit = 2000;
    }

    /*
        Syncs with user profile and program schedule
        to adjust daily limits
     */

    public DailyLog(Profile user){
        foodEntries = new ArrayList<FoodEntry>();
        assignCalories(user);
        assignMacros(user);
    }//end constructor - Daily Log user sync

    /*
        Allows for users to assign their own
        nutritional plan
     */
    public DailyLog(short calories){
        foodEntries = new ArrayList<FoodEntry>();
        calorieLimit = calories;
    }// end constructor - user assigns only calories

    public DailyLog(short calories , short protein , short fat , short carbohydrate){
        foodEntries = new ArrayList<FoodEntry>();
        calorieLimit = calories;
        proteinLimit = protein;
        fatLimit = fat;
        carbohydrateLimit = carbohydrate;
    }//end constructor - user assigns values

    // Setters
    public void setCarbohydrateLimit(short carbohydrateLimit) {
        this.carbohydrateLimit = carbohydrateLimit;
    }

    public void setFatLimit(short fatLimit) {
        this.fatLimit = fatLimit;
    }

    public void setProteinLimit(short proteinLimit) {
        this.proteinLimit = proteinLimit;
    }

    public void setCalorieLimit(short calorieLimit) {
        this.calorieLimit = calorieLimit;
    }

    public void setCurrentCalories(short currentCalories) {
        this.currentCalories = currentCalories;
    }

    public void setCurrentCarbohydrates(short currentCarbohydrates) {
        this.currentCarbohydrates = currentCarbohydrates;
    }

    public void setCurrentFats(short currentFats) {
        this.currentFats = currentFats;
    }

    public void setCurrentProtein(short currentProtein) {
        this.currentProtein = currentProtein;
    }

    // Getters
    public short getCalorieLimit() {
        return calorieLimit;
    }

    public short getCurrentCalories() {
        return currentCalories;
    }

    public short getCurrentCarbohydrates() {
        return currentCarbohydrates;
    }

    public short getCurrentFats() {
        return currentFats;
    }

    public short getCurrentProtein() {
        return currentProtein;
    }

    public short getCarbohydrateLimit() {
        return carbohydrateLimit;
    }

    public short getFatLimit() {
        return fatLimit;
    }

    public short getProteinLimit() {
        return proteinLimit;
    }

    public short getRemainingCalories(){
        return(short)((calorieLimit - currentCalories));
    }

    public void addEntry(FoodEntry entry){
        foodEntries.add(entry);
        currentCalories += entry.getCalories();
        currentCarbohydrates += entry.getCarbohydrates();
        currentFats += entry.getFats();
        currentProtein += entry.getProteins();
    }

    public void removeEntry(FoodEntry entry){
        foodEntries.remove(entry);
        currentCalories -= entry.getCalories();
        currentCarbohydrates -= entry.getCarbohydrates();
        currentFats -= entry.getFats();
        currentProtein -= entry.getProteins();
    }

    /*
        Updates all the food entries information into
        the log, for when we are loading logs
     */
    private void updateCurrents(){
        for(FoodEntry entry : foodEntries){
            currentCalories += entry.getCalories();
            currentCarbohydrates += entry.getCarbohydrates();
            currentFats += entry.getFats();
            currentProtein += entry.getProteins();
        }//end for - add each entry
    }//end method - update nutrition totals

    private void assignCalories(Profile user){
        calorieLimit = CalorieCalculator.calculateCalories((byte)0, user.getAge() , user.getHeight() , user.getWeight() , user.getIsMale());
        if(user.hasWorkoutDay()==true){
            switch (user.getGoalFlag()){
                case 0:
                    calorieLimit -= 200;
                    break;
                case 1:
                    break;
                case 2:
                    calorieLimit += 500;
                    break;
            }//end switch - user goals factored
        }//end if - user has program today
        else{
            switch (user.getGoalFlag()){
                case 0:
                    calorieLimit -= 500;
                    break;
                case 1:
                    break;
                case 2:
                    calorieLimit += 200;
                    break;
            }//end switch - user goals factored
        }//end else - user has no program today
    }//end method - calculate daily calorie limit

    private void assignMacros(Profile user){
        short[] macros = CalorieCalculator.calculateMacros(calorieLimit , user.getHeight());
        proteinLimit = macros[0];
        fatLimit = macros[1];
        carbohydrateLimit = macros[2];
    }//end method - assigns macronutrients user calculator
}
