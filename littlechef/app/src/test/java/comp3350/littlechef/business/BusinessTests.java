package comp3350.littlechef.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(AccessRecipesTest.class);
        suite.addTestSuite(DecimalFractionConversionTest.class);
        suite.addTestSuite(ScaleRecipeTest.class);
        suite.addTestSuite(TimeRecipeTest.class);
        suite.addTestSuite(FilteredRecipesTest.class);

        return suite;
    }
}