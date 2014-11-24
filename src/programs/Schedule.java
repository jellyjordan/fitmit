package programs;

import java.util.Calendar;
import java.util.Date;

/*
    Schedules programs, acts as a sorting mechanism
    and can be utilized by the nutrition client to
    adjust settings.
 */
public class Schedule {
    private byte repeatCycle;       //numbers of days before program repeats
    private Calendar scheduledDate;     //date of the programs next scheduled instance date=>today

    //creates Calendar object based on current day
    public Schedule(){
        scheduledDate = Calendar.getInstance();
        scheduledDate.set(Calendar.YEAR , Calendar.MONTH , Calendar.DATE , 23 , 59 , 59);
        scheduledDate.set(Calendar.MILLISECOND , 999);
    }

    //creates Calendar object based on current time and
    //sets up repeating cycle
    public Schedule(byte repeatCycle){
        this.repeatCycle = repeatCycle;
        scheduledDate = Calendar.getInstance();
    }

    //returns schedule
    public Calendar getScheduledDate(){
        return scheduledDate;
    }


    public void setRepeatCycle(byte repeatCycle) {
        this.repeatCycle = repeatCycle;
    }

    public byte getRepeatCycle() {
        return repeatCycle;
    }

    /*Updates the scheduled date by checking
      the time than adding the repeatCycle to the days
      until the time is equal to today or greater
     */
    public void updateSchedule(){
        while(Calendar.getInstance().getTimeInMillis() > scheduledDate.getTimeInMillis()){
            scheduledDate.add(Calendar.DATE , repeatCycle);

        }
    }//end update
}
