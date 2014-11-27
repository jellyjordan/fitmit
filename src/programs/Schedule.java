package programs;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/*
    Schedules programs, acts as a sorting mechanism
    and can be utilized by the nutrition client to
    adjust settings.
 */
public class Schedule implements Serializable {
    private byte repeatCycle;           // Number of days the program repeats
    private Calendar scheduledDate;     // Date of the next planned activity

    /*
        Creates a calendar with the instance set to the very end last second of
        the date. This way when we update the schedule we will be guaranteed to
        not update if the days are the same.
    */
    public Schedule(){
        scheduledDate = Calendar.getInstance();
        scheduledDate.set(Calendar.YEAR , Calendar.MONTH , Calendar.DATE , 23 , 59 , 59);
        scheduledDate.set(Calendar.MILLISECOND , 999);
    }


    // Setters
    public void setRepeatCycle(byte repeatCycle) {
        this.repeatCycle = repeatCycle;
    }

    // Getters
    public byte getRepeatCycle() {
        return repeatCycle;
    }

    public Calendar getScheduledDate(){
        return scheduledDate;
    }

    /*
      Updates the scheduled date by checking the time than adding the repeatCycle to the days
      until the time is equal to today or greater.
     */
    public void updateSchedule(){
        while(Calendar.getInstance().getTimeInMillis() > scheduledDate.getTimeInMillis()){
            scheduledDate.add(Calendar.DATE , repeatCycle);
        }
    }

}
