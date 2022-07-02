package comp3350.littlechef.business;

import java.util.ArrayList;
import java.util.Locale;

import comp3350.littlechef.objects.Recipe;

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
        return String.format(Locale.CANADA,"%02d",hours) + ":" + String.format(Locale.CANADA,"%02d",minutes) + ":" + String.format(Locale.CANADA,"%02d",seconds);
    }


    public static String recipeTimeFormat(int seconds, int minutes, int hours)
    {
        return String.format(Locale.CANADA,"%02d",hours) + "h " + String.format(Locale.CANADA,"%02d",minutes) + "m " + String.format(Locale.CANADA,"%02d",seconds) + "s";
    }
}