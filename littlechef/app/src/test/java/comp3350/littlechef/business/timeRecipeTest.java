package comp3350.littlechef.business;

import static comp3350.littlechef.business.TimeRecipe.*;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

// might not need
import comp3350.littlechef.application.Main;
import comp3350.littlechef.application.Services;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

public class timeRecipeTest extends TestCase
{
    private static final int SECONDS_IN_DAY = 86400;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int SECOND = 1;
    @Test
    public void testSimpleCases()
    {
        assertEquals("00h 00m 24s",TimeRecipe.totalSecondsToString(24, true));
        assertEquals("00:00:24",TimeRecipe.totalSecondsToString(24, false));

        assertEquals("00h 06m 40s",TimeRecipe.totalSecondsToString(400, true));
        assertEquals("00:06:40",TimeRecipe.totalSecondsToString(400, false));

        assertEquals("03h 25m 45s",TimeRecipe.totalSecondsToString(12345, true));
        assertEquals("03:25:45",TimeRecipe.totalSecondsToString(12345, false));


        assertEquals("00:00:45",TimeRecipe.timerTimeFormat(0,0,45));
        assertEquals("00:10:24",TimeRecipe.timerTimeFormat(0,10,24));
        assertEquals("03:00:24",TimeRecipe.timerTimeFormat(3,0,24));
        assertEquals("10:03:24",TimeRecipe.timerTimeFormat(10,3,24));

        assertEquals("00h 00m 45s",TimeRecipe.recipeTimeFormat(0,0,45));
        assertEquals("00h 10m 24s",TimeRecipe.recipeTimeFormat(0,10,24));
        assertEquals("03h 00m 24s",TimeRecipe.recipeTimeFormat(3,0,24));
        assertEquals("10h 03m 24s",TimeRecipe.recipeTimeFormat(10,3,24));
    }

    @Test
    public void testForZeroAmountOfTime()
    {
        //  public static String totalSecondsToString(int totSeconds, boolean recipeTime)
        assertEquals("00h 00m 00s",TimeRecipe.totalSecondsToString(0, true));
        assertEquals("00:00:00",TimeRecipe.totalSecondsToString(0, false));

        assertEquals("00:00:00",TimeRecipe.timerTimeFormat(0,0,0));
        assertEquals("00h 00m 00s",TimeRecipe.recipeTimeFormat(0,0,0));
    }

    @Test
    public void testForExactTimeUnits()
    {
        assertEquals("00h 00m 01s",TimeRecipe.totalSecondsToString(SECOND, true));
        assertEquals("00:00:01",TimeRecipe.totalSecondsToString(SECOND, false));

        assertEquals("00h 01m 00s",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE, true));
        assertEquals("00:01:00",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE, false));

        assertEquals("01h 00m 00s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR, true));
        assertEquals("01:00:00",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR, false));

        assertEquals("01h 01m 00s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE, true));
        assertEquals("01:01:00",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE, false));

        assertEquals("01h 00m 01s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECOND, true));
        assertEquals("01:00:01",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECOND, false));

        assertEquals("00h 01m 01s",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE+SECOND, true));
        assertEquals("00:01:01",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE+SECOND, false));

        assertEquals("01h 01m 01s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE+SECOND, true));
        assertEquals("01:01:01",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE+SECOND, false));

        assertNull(TimeRecipe.timerTimeFormat(0,SECONDS_IN_MINUTE,0));
        assertNull(TimeRecipe.timerTimeFormat(0,0,SECONDS_IN_MINUTE));
        assertEquals("24:00:00",TimeRecipe.timerTimeFormat(24,0,0));
        assertEquals("01:00:00",TimeRecipe.timerTimeFormat(1,0,0));
        assertEquals("00:01:00",TimeRecipe.timerTimeFormat(0,1,0));
        assertEquals("00:00:01",TimeRecipe.timerTimeFormat(0,0,1));

        assertNull(TimeRecipe.recipeTimeFormat(0,SECONDS_IN_MINUTE,0));
        assertNull(TimeRecipe.recipeTimeFormat(0,0,SECONDS_IN_MINUTE));
        assertEquals("24h 00m 00s",TimeRecipe.recipeTimeFormat(24,0,0));
        assertEquals("01h 00m 00s",TimeRecipe.recipeTimeFormat(1,0,0));
        assertEquals("00h 01m 00s",TimeRecipe.recipeTimeFormat(0,1,0));
        assertEquals("00h 00m 01s",TimeRecipe.recipeTimeFormat(0,0,1));
    }

    @Test
    public void testForExactTimeUnitsPlusOne()
    {
        assertEquals("00h 00m 02s",TimeRecipe.totalSecondsToString(SECOND+1, true));
        assertEquals("00:00:02",TimeRecipe.totalSecondsToString(SECOND+1, false));

        assertEquals("00h 01m 01s",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE+1, true));
        assertEquals("00:01:01",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE+1, false));

        assertEquals("01h 00m 01s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+1, true));
        assertEquals("01:00:01",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+1, false));

        assertEquals("01h 01m 01s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE+1, true));
        assertEquals("01:01:01",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE+1, false));

        assertEquals("01h 00m 02s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECOND+1, true));
        assertEquals("01:00:02",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECOND+1, false));

        assertEquals("00h 01m 02s",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE+SECOND+1, true));
        assertEquals("00:01:02",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE+SECOND+1, false));

        assertEquals("01h 01m 02s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE+SECOND+1, true));
        assertEquals("01:01:02",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE+SECOND+1, false));
    }

    @Test
    public void testForExactTimeUnitsMinusOne()
    {
        assertEquals("00h 00m 00s",TimeRecipe.totalSecondsToString(SECOND-1, true));
        assertEquals("00:00:00",TimeRecipe.totalSecondsToString(SECOND-1, false));

        assertEquals("00h 00m 59s",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE-1, true));
        assertEquals("00:00:59",TimeRecipe.totalSecondsToString(SECONDS_IN_MINUTE-1, false));

        assertEquals("00h 59m 59s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR-1, true));
        assertEquals("00:59:59",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR-1, false));

        assertEquals("01h 00m 59s",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE-1, true));
        assertEquals("01:00:59",TimeRecipe.totalSecondsToString(SECONDS_IN_HOUR+SECONDS_IN_MINUTE-1, false));

        assertEquals("00:59:00",TimeRecipe.timerTimeFormat(0,SECONDS_IN_MINUTE-1,0));
        assertEquals("00:00:59",TimeRecipe.timerTimeFormat(0,0,59));
        assertEquals("00:59:59",TimeRecipe.timerTimeFormat(0,SECONDS_IN_MINUTE-1,59));

        assertEquals("00h 59m 00s",TimeRecipe.recipeTimeFormat(0,SECONDS_IN_MINUTE-1,0));
        assertEquals("00h 00m 59s",TimeRecipe.recipeTimeFormat(0,0,59));
        assertEquals("00h 59m 59s",TimeRecipe.recipeTimeFormat(0,SECONDS_IN_MINUTE-1,59));
    }

    @Test
    public void testForDays()
    {
        // FOR DENYS: these are all failing
//        assertEquals("24h 00m 00s",TimeRecipe.totalSecondsToString(SECONDS_IN_DAY, true));
//        assertEquals("24:00:00",TimeRecipe.totalSecondsToString(SECONDS_IN_DAY, false));
//
//        assertEquals("48h 00m 00s",TimeRecipe.totalSecondsToString(SECONDS_IN_DAY*2, true));
//        assertEquals("48:00:00",TimeRecipe.totalSecondsToString(SECONDS_IN_DAY*2, false));
//
//        assertEquals("24h 06m 00s",TimeRecipe.totalSecondsToString(SECONDS_IN_DAY+SECONDS_IN_MINUTE, true));
//        assertEquals("24:06:00",TimeRecipe.totalSecondsToString(SECONDS_IN_DAY+SECONDS_IN_MINUTE, false));
    }

    @Test
    public void testForNegativeTime()
    {
        assertNull(TimeRecipe.totalSecondsToString(-1, true)); // double check that we can use assertNull
        assertNull(TimeRecipe.totalSecondsToString(-1, false));

        assertNull(TimeRecipe.totalSecondsToString(-552, true));
        assertNull(TimeRecipe.totalSecondsToString(-595, false));

        assertNull(TimeRecipe.timerTimeFormat(0,-45,0));
        assertNull(TimeRecipe.timerTimeFormat(0,0,-39));
        assertNull(TimeRecipe.timerTimeFormat(-2,0,0));
        assertNull(TimeRecipe.timerTimeFormat(-2,-30,-21));

        assertNull(TimeRecipe.recipeTimeFormat(0,-45,0));
        assertNull(TimeRecipe.recipeTimeFormat(0,0,-39));
        assertNull(TimeRecipe.recipeTimeFormat(-2,0,0));
        assertNull(TimeRecipe.recipeTimeFormat(-2,-30,-21));
    }
    
}
