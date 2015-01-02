package account;

import nutrition.DailyLog;
import programs.Program;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


/*
    User profile stores all data for nutritional logging and program
    planning.
 */
public class Profile implements Serializable{
    private final String userName;          // Unique profile identifier
    private short userWeight;               // Weight stored in metric
    private short userHeight;               // Height stored in metric
    private byte userAge;                   // Age used for calorie conversion
    private boolean userIsMale;             // Gender used for calorie conversion
    private byte goalFlag;                  // 0 Lose , 1 Maintain , 2 Gain
    private Calendar lastLogin;             // Timestamp of last login
    private ArrayList<Program> programList; // Exercise Programs the user created
    private DailyLog dailyLog;              // A daily food log created dynamically, used for data gathering

    /*
        Used in the condition that the user wants to get
        dive straight into the app.
     */
    public Profile(String userName){
        this.userName = userName;
        userWeight = 0;
        userHeight = 160;
        userAge = 20;
        userIsMale = true;
        goalFlag = 1;
        lastLogin = Calendar.getInstance();
    }

    //User set up constructor
    public Profile(String userName ,short userWeight , short userHeight , byte userAge , boolean userIsMale , byte goalFlag){
        this.userName = userName;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
        this.userAge = userAge;
        this.userIsMale = userIsMale;
        this.goalFlag = goalFlag;
        lastLogin = Calendar.getInstance();
        programList = new ArrayList<Program>();
        dailyLog = new DailyLog(this);
    }

    // Setter Methods
    public void setGoalFlag(byte goalFlag) {
        this.goalFlag = goalFlag;
    }

    public void setWeight(short weight) {
        userWeight = weight;
    }

    public void setAge(byte age) {
        userAge = age;
    }

    public void setHeight(short height) {
        userHeight = height;
    }

    public void setIsMale(boolean isMale) {
        userIsMale = isMale;
    }

    // Getter Methods
    public byte getGoalFlag() {
        return goalFlag;
    }

    public boolean getIsMale() {
        return userIsMale;
    }

    public short getHeight() {
        return userHeight;
    }

    public short getWeight() {
        return userWeight;
    }

    public byte getAge() {
        return userAge;
    }

    public String getUserName() {
        return userName;
    }

    public DailyLog getDailyLog(){
        return dailyLog;
    }

    /*
        Checks the first date in the sorted program list to see if the day
        matches the current day. Used for nutritional assignment
     */
    public boolean hasWorkoutDay(){
        Calendar nextDate = programList.get(0).getProgramSchedule().getScheduledDate();
        Calendar todaysDate = Calendar.getInstance();
        if(nextDate.DATE == todaysDate.DATE){
            return true;
        }
        return false;
    }
    /*
        If it is a new day compared to the last login date than
        the user is assigned their planned program and nutritional
        log for the day
     */
    public void syncProfile(){
        Calendar todaysDate = Calendar.getInstance();
        // It is a new day!
        if(todaysDate.DATE != lastLogin.DATE){
            // Updates the programs' schedules
            if(userWeight == 0){
                dailyLog = new DailyLog();
            }
            else{
                for(Program program : programList){
                    program.getProgramSchedule().updateSchedule();
                }
                // Assigns a new food log using profile settings
                dailyLog = new DailyLog(this);
            }
        }
    }
}
