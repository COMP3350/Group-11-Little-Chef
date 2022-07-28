package comp3350.littlechef.business;

import java.util.Locale;

// CLASS: TimeRecipe.java
// REMARKS: This class has covert time to some appropriate format.
//-----------------------------------------
public class TimeRecipe
{
    private static final int SECONDS_IN_DAY = 86400;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;
    private static String result;

    public static String totalSecondsToString(int totSeconds, boolean recipeTime)
    {
        result = null;

        if(totSeconds >= 0)
        {
            //getting the seconds, minutes and hours from total seconds
            int seconds = ((totSeconds % SECONDS_IN_DAY) % SECONDS_IN_HOUR) % SECONDS_IN_MINUTE;
            int minutes = ((totSeconds % SECONDS_IN_DAY) % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE;
            int hours = ((totSeconds % SECONDS_IN_DAY) / SECONDS_IN_HOUR);

            if (recipeTime)
            {
                result = recipeTimeFormat(hours, minutes, seconds);
            } else
            {
                result = timerTimeFormat(hours, minutes, seconds);
            }
        }

        return result;
    }

    public static String timerTimeFormat(int hours, int minutes, int seconds)
    {
        result = null;

        if(hours >= 0 && minutes >= 0 && minutes < 60 && seconds >= 0 && seconds < 60)
        {
            result = String.format(Locale.CANADA,"%02d:%02d:%02d",hours,minutes,seconds);
        }

        return result;
    }


    public static String recipeTimeFormat(int hours, int minutes, int seconds)
    {
        result = null;

        if(hours >= 0 && minutes >= 0 && minutes < 60 && seconds >= 0 && seconds < 60)
        {
            result = String.format(Locale.CANADA, "%02dh %02dm %02ds", hours, minutes, seconds);
        }

        return result;
    }
}
