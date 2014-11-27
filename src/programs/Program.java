package programs;

import java.io.Serializable;

/*
    Class for all user programs. Relies on the schedule
    for sorting and to adjust daily nutritional requirements
 */
public class Program implements Comparable<Program> , Serializable {

    private String name;
    private Schedule programSchedule;

    public Program(String name){
        this.name = name;
        programSchedule = new Schedule();
    }

    public String toString() {
        return name;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    // Getters
    public Schedule getProgramSchedule() {
        return programSchedule;
    }

    /*
        Sorts and updates the program's schedule by their
        next scheduled date
     */
    @Override
    public int compareTo(Program program) {
        return getProgramSchedule().getScheduledDate().compareTo(program.getProgramSchedule().getScheduledDate());
    }
}
