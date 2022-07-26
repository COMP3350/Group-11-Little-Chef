package comp3350.littlechef;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import comp3350.littlechef.business.AccessRecipesTest;
import comp3350.littlechef.business.DecimalFractionConversionTest;
import comp3350.littlechef.business.ScaleRecipeTest;
import comp3350.littlechef.business.TimeRecipeTest;
import comp3350.littlechef.objects.IngredientTest;
import comp3350.littlechef.objects.RecipeTest;
import comp3350.littlechef.persistence.DataAccessTest;

public class AllTests extends TestCase
{
    public static TestSuite suite;

    public static Test suite()
    {
        System.out.println("Launching Test Suite.");
        suite = new TestSuite("All tests");

        tBusiness();
        tObjects();
        tPersistence();

        return suite;
    }

    private static void tBusiness()
    {
        suite.addTestSuite(AccessRecipesTest.class);
        suite.addTestSuite(DecimalFractionConversionTest.class);
        suite.addTestSuite(ScaleRecipeTest.class);
        suite.addTestSuite(TimeRecipeTest.class);
    }

    private static void tObjects()
    {
        suite.addTestSuite(IngredientTest.class);
        suite.addTestSuite(RecipeTest.class);
    }

    private static void tPersistence()
    {
        suite.addTestSuite(DataAccessTest.class);
    }
}

