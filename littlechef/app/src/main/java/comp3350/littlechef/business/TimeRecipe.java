package comp3350.littlechef.business;

import java.util.Locale;

public class TimeRecipe
{
    private static final int SECONDS_IN_DAY = 86400;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;

    public static String totalSecondsToString(int totSeconds, boolean recipeTime)
    {
        String result = null;

        if(totSeconds >= 0) {
            //getting the seconds, minutes and hours from total seconds
            int seconds = ((totSeconds % SECONDS_IN_DAY) % SECONDS_IN_HOUR) % SECONDS_IN_MINUTE;
            int minutes = ((totSeconds % SECONDS_IN_DAY) % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE;
            int hours = ((totSeconds % SECONDS_IN_DAY) / SECONDS_IN_HOUR);

            if (recipeTime) {
                result = recipeTimeFormat(seconds, minutes, hours);
            } else {
                result = timerTimeFormat(seconds, minutes, hours);
            }
        }

        return result;
    }

    public static String timerTimeFormat(int seconds, int minutes, int hours)
    {
        return String.format(Locale.CANADA,"%02d:%02d:%02d",hours,minutes,seconds);
    }


    public static String recipeTimeFormat(int seconds, int minutes, int hours)
    {
        return String.format(Locale.CANADA,"%02dh %02dm %02ds",hours,minutes,seconds);
    }
}
