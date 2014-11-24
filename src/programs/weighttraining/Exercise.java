package programs.weighttraining;

import java.util.ArrayList;

/*
    Holds information used by WeightTrainingProgram to log each program
    and holds id reference to database description
 */
public class Exercise {
    private String name;                //Displayed in workout
    private ArrayList<Set> setList;     //Number of times exercise is performed
    private final int ID;             //Database identification to provide descriptions and pictures
    private short record;                 //Users highest recorded performance

    public Exercise(String name , int databaseID , short record){
        this.name = name;
        this.ID = databaseID;
        this.record = record;
        setList = new ArrayList<Set>();
    }//end constructor - Exercise

    public void setName(String name) {
        this.name = name;
    }

    public void setRecord(short record) {
        if(this.record < record) {
            this.record = record;
        }
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getRecord() {
        return record;
    }
    public void addSet(){
        setList.add(new Set());
    }
    public void removeSet(){
        setList.remove(setList.size() - 1);
    }
    public Set getSet(int index){
        return setList.get(index);
    }
}
