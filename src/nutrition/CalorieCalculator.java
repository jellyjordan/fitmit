package nutrition;

/*
    Calorie Calculator supplies the daily calorie intake
    for the user's nutrition logging system. For use with
    metric data.
 */
public class CalorieCalculator {

    /*
        Uses Mifflin - St Jeor Formula for BMR calculations
     */
    private static short calculateBMR(byte age , short height , short weight ,boolean isMale){
        if(isMale){
            return (short)(10 * weight + 6.25*height - 5 * age + 5);
        }
        return (short)(10 * weight + 6.25*height - 5 * age - 151);
    }//end method - Calculate BMR


    /*
        Calculates calorie maintenance level based on BMR
        and the given activity level which can be adjusted
        by the user
     */
    public static short calculateCalories(byte activityLevel , byte age , short height , short weight , boolean isMale){
        short TDEE = calculateBMR(age , height , weight , isMale);
        switch(activityLevel) {
            case 0:
                TDEE *= 1.20;
                break;
            case 1:
                TDEE *= 1.35;
                break;
            case 2:
                TDEE *= 1.55;
                break;
            case 3:
                TDEE *= 1.725;
                break;
            case 4:
                TDEE *= 1.9;
                break;
        }
        return TDEE;
    }//end method - Calculate TDEE

    /*
        Calculates the users' macronutrient daily requirements
        based on their calorie limits
     */
    public static short[] calculateMacros(short caloryLimit , short weight){
        short[] macros = new short[3];

        // Daily Protein Goal
        macros[0] = (short)(weight * 2.2);
        caloryLimit -= macros[0] * 4;

        // Daily Fat Goal
        macros[1] = (short)(weight * 1.1);
        caloryLimit -= macros[1] * 9;

        //carbohydrate goal
        macros[2] = (short)(caloryLimit / 4);

        return macros;
    }//end method - Calculate Macros
}
