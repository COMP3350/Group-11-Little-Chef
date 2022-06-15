package comp3350.littlechef;

import org.junit.Test;

import static org.junit.Assert.*;
import static comp3350.littlechef.business.RecipeUnitConversion.convertDecimalToFraction;

public class AllTests {
    @Test
    public void convertDecimalToFractionTests() {
        //Simple tests
        assertEquals("1/8", convertDecimalToFraction(0.125));
        assertEquals("1/2", convertDecimalToFraction(0.5));
        assertEquals("9/10", convertDecimalToFraction(0.9));

        //continuous decimals
        assertEquals("2/3", convertDecimalToFraction(0.666666));
        assertEquals("2/7", convertDecimalToFraction(0.285714));
        assertEquals("8/9", convertDecimalToFraction(0.88888888));

        //rounding error
        assertEquals("1/2", convertDecimalToFraction(0.51));
        assertEquals("1/3", convertDecimalToFraction(0.34));

        assertThrows()

    }

}