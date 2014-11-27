package nutrition;

import java.io.Serializable;

/*
    Individual entry which is added to the user's
    daily log to track nutrition.
 */
public class FoodEntry implements Serializable {
    private String name;
    private short calories;
    private short proteins;
    private short fats;
    private short carbohydrates;

    public FoodEntry(String name, short calories){
        this.name = name;
        this.calories = calories;
    }

    public FoodEntry(String name, short calories , short proteins , short fats , short carbohydrates){
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public void setCalories(short calories) {
        this.calories = calories;
    }

    public void setCarbohydrates(short carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setFats(short fats) {
        this.fats = fats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProteins(short proteins) {
        this.proteins = proteins;
    }

    public short getCalories() {
        return calories;
    }

    public short getCarbohydrates() {
        return carbohydrates;
    }

    public short getFats() {
        return fats;
    }
    public short getProteins(){
        return proteins;
    }

    public String getName() {
        return name;
    }
}
