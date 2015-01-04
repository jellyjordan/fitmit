package database;

import nutrition.FoodEntry;

import java.sql.*;
import java.util.ArrayList;

/*
    Class handles drivers, connections and all queries to
    sqlite databases
 */
public class DatabaseCommunicator {
    private static final String DRIVER_PATH = "org.sqlite.JDBC";
    private static final String DATABASE_PATH = "jdbc:sqlite:src/res/fitmit.sqlite";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    // Selects all values needed to create a food entry
    private static final String SELECT_ALL =    "SELECT description , calories , protein , lipid , carbohydrate " +
                                                "FROM foodData";

    // Selects the names of a food item
    private static final String SELECT_NAME =   "SELECT description " +
                                                "FROM foodData";

    private static Connection dbConnection;


    /*
        Finds food items containing the input string
     */
    public static ArrayList<String> findSimilarFoods(String input){
        ArrayList<String> possibleEntries = new ArrayList<String>();

        // Query will pattern match input
        String query = SELECT_NAME + "WHERE description LIKE '%" + input + "%'";
        try{
            Statement queryEx = dbConnection.createStatement();
            ResultSet results = queryEx.executeQuery(query);
            /*
                Stops after result set is empty or we find more than 5
                in order to keep operation time down
             */
            while (results.next() || possibleEntries.size() > 5){
                possibleEntries.add(results.getString("description"));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return possibleEntries;
    }

    /*
        Queries the database and creates a food entry using the
        foodName to get the matching nutritional information and the
        weight of the food to scale the nutrients to the correct values
     */
    public static FoodEntry createFoodEntry(String foodName , int weight){
        FoodEntry foodEntry;
        // Query to send to database.
        String query = SELECT_ALL + "WHERE description = '" + foodName +"'";

        // Database stores nutritional content of item weighing 100 grams.
        double scale = weight / 100;
        try{
            Statement queryEx = dbConnection.createStatement();
            ResultSet results = queryEx.executeQuery(query);

            short calories = (short) (results.getInt("calories") * scale);
            short carbs = (short) (results.getInt("carbohydrate") * scale);
            short fats = (short) (results.getInt("lipid") * scale);
            short proteins = (short) (results.getInt("protein") * scale);

            results.close();
            foodEntry = new FoodEntry(foodName , calories , carbs , fats , proteins);
            return foodEntry;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    /*
        Loads the driver and opens the database
     */
    public static void openConnection(){
        try{
            Class.forName(DRIVER_PATH); //loads driver
            dbConnection = DriverManager.getConnection(DATABASE_PATH , USERNAME , PASSWORD);
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
            System.err.println("Driver not found!");
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.err.println("Could not connect to database!");
        }
    }

    /*
        Closes the database connection. If this method
        is called after a failed openConnection it will throw
        the nullpointer
     */
    public static void closeConnection(){
        try{
            dbConnection.close();
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
            System.err.println("Attempted to close non-existant connection");
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.err.println("Connection failed to close");
        }
    }
}
