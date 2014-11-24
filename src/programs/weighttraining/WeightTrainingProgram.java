package programs.weighttraining;

import programs.Program;
import java.util.ArrayList;

/*
    Holds the exercises and other training program information
 */
public class WeightTrainingProgram extends Program {
    private ArrayList<Exercise> exerciseList;

    /*
        Gives the program it's name and creates the
        exercise list for the user to add exercises to
     */
    public WeightTrainingProgram(String name){
        super(name);
        exerciseList = new ArrayList<Exercise>();
    }

    public boolean addExercise(Exercise exercise){
        for(Exercise existingExercise : exerciseList){
            if(exercise.getID() == existingExercise.getID()){
                return false;
            } //end if - duplicate found
        }//end for - all program exercises
        exerciseList.add(exercise);
        return true;
    }//end method - addExercise

    public void removeExercise(Exercise exercise){
        exerciseList.remove(exercise);
    }

    public ArrayList<Exercise> getExercises(){
        return exerciseList;
    }
}
