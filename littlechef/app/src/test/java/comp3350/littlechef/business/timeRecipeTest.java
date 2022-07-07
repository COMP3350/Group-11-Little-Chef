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

    @Test
    public void testSimpleCases()
    {
        assertEquals("00h 00m 24s",TimeRecipe.totalSecondsToString(24, true));
        assertEquals("00:00:24",TimeRecipe.totalSecondsToString(24, false));

        assertEquals("00h 06m 40s",TimeRecipe.totalSecondsToString(400, true));
        assertEquals("00:06:40",TimeRecipe.totalSecondsToString(400, false));

        assertEquals("03h 25m 45s",TimeRecipe.totalSecondsToString(12345, true));
        assertEquals("03:25:45",TimeRecipe.totalSecondsToString(12345, false));
    }

    @Test
    public void testForZeroAmountOfTime()
    {
        //  public static String totalSecondsToString(int totSeconds, boolean recipeTime)
        assertEquals("00h 00m 00s",TimeRecipe.totalSecondsToString(0, true));
        assertEquals("00:00:00",TimeRecipe.totalSecondsToString(0, false));
    }

    @Test
    public void testForExactTimeUnits()
    {
        assertEquals("00h 00m 01s",TimeRecipe.totalSecondsToString(1, true));
        assertEquals("00:00:01",TimeRecipe.totalSecondsToString(1, false));

        assertEquals("00h 01m 00s",TimeRecipe.totalSecondsToString(60, true));
        assertEquals("00:01:00",TimeRecipe.totalSecondsToString(60, false));

        assertEquals("01h 00m 00s",TimeRecipe.totalSecondsToString(3600, true));
        assertEquals("01:00:00",TimeRecipe.totalSecondsToString(3600, false));

        assertEquals("01h 01m 00s",TimeRecipe.totalSecondsToString(3660, true));
        assertEquals("01:01:00",TimeRecipe.totalSecondsToString(3660, false));

        assertEquals("01h 00m 01s",TimeRecipe.totalSecondsToString(3601, true));
        assertEquals("01:00:01",TimeRecipe.totalSecondsToString(3601, false));

        assertEquals("00h 01m 01s",TimeRecipe.totalSecondsToString(61, true));
        assertEquals("00:01:01",TimeRecipe.totalSecondsToString(61, false));

        assertEquals("01h 01m 01s",TimeRecipe.totalSecondsToString(3661, true));
        assertEquals("01:01:01",TimeRecipe.totalSecondsToString(3661, false));
        // send exactly the amount of seconds in one hour
        // send exactly the amount of seconds in a day
    }

    @Test
    public void testForExactTimeUnitsPlusOne()
    {
        assertEquals("00h 00m 02s",TimeRecipe.totalSecondsToString(2, true));
        assertEquals("00:00:02",TimeRecipe.totalSecondsToString(2, false));

        assertEquals("00h 01m 01s",TimeRecipe.totalSecondsToString(61, true));
        assertEquals("00:01:01",TimeRecipe.totalSecondsToString(61, false));

        assertEquals("01h 00m 01s",TimeRecipe.totalSecondsToString(3601, true));
        assertEquals("01:00:01",TimeRecipe.totalSecondsToString(3601, false));

        assertEquals("01h 01m 01s",TimeRecipe.totalSecondsToString(3661, true));
        assertEquals("01:01:01",TimeRecipe.totalSecondsToString(3661, false));

        assertEquals("01h 00m 02s",TimeRecipe.totalSecondsToString(3602, true));
        assertEquals("01:00:02",TimeRecipe.totalSecondsToString(3602, false));

        assertEquals("00h 01m 02s",TimeRecipe.totalSecondsToString(62, true));
        assertEquals("00:01:02",TimeRecipe.totalSecondsToString(62, false));

        assertEquals("01h 01m 02s",TimeRecipe.totalSecondsToString(3662, true));
        assertEquals("01:01:02",TimeRecipe.totalSecondsToString(3662, false));
    }

    @Test
    public void testForExactTimeUnitsMinusOne()
    {
        assertEquals("00h 00m 00s",TimeRecipe.totalSecondsToString(0, true));
        assertEquals("00:00:00",TimeRecipe.totalSecondsToString(0, false));

        assertEquals("00h 00m 59s",TimeRecipe.totalSecondsToString(59, true));
        assertEquals("00:00:59",TimeRecipe.totalSecondsToString(59, false));

        assertEquals("00h 59m 59s",TimeRecipe.totalSecondsToString(3599, true));
        assertEquals("00:59:59",TimeRecipe.totalSecondsToString(3599, false));

        assertEquals("01h 00m 59s",TimeRecipe.totalSecondsToString(3659, true));
        assertEquals("01:00:59",TimeRecipe.totalSecondsToString(3659, false));

        assertEquals("01h 00m 00s",TimeRecipe.totalSecondsToString(3600, true));
        assertEquals("01:00:00",TimeRecipe.totalSecondsToString(3600, false));

        assertEquals("00h 01m 00s",TimeRecipe.totalSecondsToString(60, true));
        assertEquals("00:01:00",TimeRecipe.totalSecondsToString(60, false));

        assertEquals("01h 01m 00s",TimeRecipe.totalSecondsToString(3660, true));
        assertEquals("01:01:00",TimeRecipe.totalSecondsToString(3660, false));
    }

    @Test
    public void testForNegativeTime()
    {
        assertNull(null,TimeRecipe.totalSecondsToString(-1, true)); // double check that we can use assertNull
        assertNull(null,TimeRecipe.totalSecondsToString(-1, false));

        assertEquals(null,TimeRecipe.totalSecondsToString(-552, true));
        assertEquals(null,TimeRecipe.totalSecondsToString(-595, false));
    }



}
