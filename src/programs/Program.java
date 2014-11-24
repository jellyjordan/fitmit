package programs;

/*
    Abstract class for all user programs. Relies on the schedule
    for GUI sorting and to adjust daily nutritional requirements
 */
public abstract class Program implements Comparable<Program> {

    private String name;
    private Schedule programSchedule;

    public Program(String name){
        this.name = name;
        programSchedule = new Schedule();
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public Schedule getProgramSchedule() {
        return programSchedule;
    }

    /*
        Sorts and updates the program's schedule
     */
    @Override
    public int compareTo(Program program) {
        return getProgramSchedule().getScheduledDate().compareTo(program.getProgramSchedule().getScheduledDate());
    }//end method - compare scheduled date
}
