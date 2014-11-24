package account;

import nutrition.DailyLog;
import programs.Program;

import java.util.ArrayList;
import java.util.Calendar;

/*
    U
 */
public class Profile {
    private short userWeight;
    private short userHeight;
    private byte userAge;
    private boolean userIsMale;
    private boolean userIsMetric;
    private byte goalFlag; //0 =lose weight
                                  //1 = maintain
                                  //2 = gain weight
    private ArrayList<Program> programList;
    private DailyLog dailyLog;

    public Profile(){
        userIsMetric = true;
        goalFlag = 1;
    }

    //User set up constructor
    public Profile(short userWeight , short userHeight , byte userAge , boolean userIsMale , boolean userIsMetric , byte goalFlag){
        this.userWeight = userWeight;
        this.userHeight = userHeight;
        this.userAge = userAge;
        this.userIsMale = userIsMale;
        this.userIsMetric = userIsMetric;
        this.goalFlag = goalFlag;
        programList = new ArrayList<Program>();
        dailyLog = new DailyLog(this);
    }

    //Checks program list and date to see if workout is today
    public boolean hasWorkoutDay(){
        Calendar today = Calendar.getInstance();
        for(Program program : programList){
            if(program.getProgramSchedule().getScheduledDate().DATE == today.DATE){
                return true;
            }//end if- today is workout day
        }//end for - check for workout day
        return false;
    }//end method

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

    public void setIsMetric(boolean isMetric){userIsMetric = isMetric;}

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

    public boolean isMetric() {
        return userIsMetric;
    }
}
