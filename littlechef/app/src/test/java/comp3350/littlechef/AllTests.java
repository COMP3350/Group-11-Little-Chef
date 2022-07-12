package comp3350.littlechef;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import comp3350.littlechef.business.DecimalFractionConversionTest;
import comp3350.littlechef.business.ScaleRecipeTest;
import comp3350.littlechef.objects.IngredientTest;
import comp3350.littlechef.objects.RecipeTest;

public class AllTests extends TestCase
{
    public static TestSuite suite;

    public static Test suite()
    {
        System.out.println("Launching Test Suite.");
        suite = new TestSuite("All tests");
        tObjects();
        tBusiness();
        return suite;
    }

    private static void tObjects()
    {
        suite.addTestSuite(IngredientTest.class);
        suite.addTestSuite(RecipeTest.class);
    }

    private static void tBusiness()
    {
        suite.addTestSuite(DecimalFractionConversionTest.class);
        suite.addTestSuite(ScaleRecipeTest.class);
    }
}

