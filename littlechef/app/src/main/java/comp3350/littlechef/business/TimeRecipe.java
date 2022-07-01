package comp3350.littlechef.business;

import java.util.Locale;

public class TimeRecipe
{
    public static String totSecondsIntoTimer(int totSeconds)
    {
        //getting the seconds, minutes and hours from total seconds
        int seconds = ((totSeconds % 86400) % 3600) % 60;
        int minutes = ((totSeconds % 86400) % 3600) / 60;
        int hours = ((totSeconds % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    public static String formatTime(int seconds, int minutes, int hours)
    {
        return String.format(Locale.CANADA,"%02d",hours) + ":" + String.format(Locale.CANADA,"%02d",minutes) + ":" + String.format(Locale.CANADA,"%02d",seconds);
    }
}
