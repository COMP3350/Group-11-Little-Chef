package comp3350.littlechef.business;

import static comp3350.littlechef.business.DecimalFractionConversion.convertDecimalToFraction;
import junit.framework.TestCase;
import org.junit.Test;

public class DecimalFractionConversionTest extends TestCase
{

    public DecimalFractionConversionTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testTypicalCases()
    {
        assertEquals("1/8", convertDecimalToFraction(0.125));
        assertEquals("3/5", convertDecimalToFraction(0.6));
        assertEquals("1/2", convertDecimalToFraction(0.5));
        assertEquals("9/10", convertDecimalToFraction(0.9));
    }
    @Test
    public void testFractionDecimalConversionContinuousDecimal()
    {
        assertEquals("2/3", convertDecimalToFraction(0.666666));
        assertEquals("2/7", convertDecimalToFraction(0.285714));
        assertEquals("8/9", convertDecimalToFraction(0.88888888));
    }
    @Test
    public void testFractionDecimalConversionRoundingDown()
    {
        assertEquals("1/2", convertDecimalToFraction(0.51));
        assertEquals("1/3", convertDecimalToFraction(0.339));
    }
    @Test
    public void testFractionDecimalConversionRoundingUp()
    {
        assertEquals("1/2", convertDecimalToFraction(0.49));
        assertEquals("1/3", convertDecimalToFraction(0.330));
    }
    @Test
    public void testFractionDecimalConversionTooLargeInput()
    {
        assertEquals("error", convertDecimalToFraction(123456));
        assertEquals("error", convertDecimalToFraction(2));
        assertEquals("error", convertDecimalToFraction(1.00001));
    }
    @Test
    public void testFractionDecimalConversionTooSmallInput()
    {
        assertEquals("error", convertDecimalToFraction(-0.5));
        assertEquals("error", convertDecimalToFraction(-0.000001));
        assertEquals("error", convertDecimalToFraction(-123));
    }
    @Test
    public void testFractionDecimalConversionEdgeCases()
    {
        assertEquals("error", convertDecimalToFraction(0.0));
        assertEquals("error", convertDecimalToFraction(1.0));
    }
    @Test
    public void testFractionDecimalConversionLengthyInput()
    {
        assertEquals("7/9", convertDecimalToFraction(0.7747874175971296507612950543298130874095478913409543));
        assertEquals("1/8", convertDecimalToFraction(0.1323432432412765754643565465464324546547425324543543));
        assertEquals("error", convertDecimalToFraction(1234567890.1234567890123456789012345678901234567890));
        assertEquals("error", convertDecimalToFraction(Math.PI));
    }
    @Test
    public void testFractionDecimalConversionDifferingDecimalLengths()
    {
        assertEquals("1/2", convertDecimalToFraction(0.5));
        assertEquals("1/2", convertDecimalToFraction(0.50));
        assertEquals("1/2", convertDecimalToFraction(0.500));
        assertEquals("3/10", convertDecimalToFraction(0.3));
        assertEquals("3/10", convertDecimalToFraction(0.30));
        assertEquals("3/10", convertDecimalToFraction(0.300));
    }
    @Test
    public void testFractionDecimalConversionNullInput()
    {
        try
        {
            Double d = null;
            convertDecimalToFraction(d);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

    }

    @Test
    public void testFractionDecimalConversionStringInput()
    {
        try
        {
            String d = "test";
            convertDecimalToFraction(Double.parseDouble(d));
            fail("Expected a NumberFormatException");
        } catch (NumberFormatException unused) {
        }

    }
}
