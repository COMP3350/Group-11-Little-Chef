package comp3350.littlechef.business;

import java.util.Locale;

public class TimeRecipe
{
    public static String totalSecondsToString(int totSeconds, boolean recipeTime)
    {
        //getting the seconds, minutes and hours from total seconds
        int seconds = ((totSeconds % 86400) % 3600) % 60;
        int minutes = ((totSeconds % 86400) % 3600) / 60;
        int hours = ((totSeconds % 86400) / 3600);
        String result;

        if(recipeTime)
        {
            result = recipeTimeFormat(seconds, minutes, hours);
        }

        else
        {
            result = timerTimeFormat(seconds, minutes, hours);;
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
