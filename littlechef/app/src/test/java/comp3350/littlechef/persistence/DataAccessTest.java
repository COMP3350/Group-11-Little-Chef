package comp3350.littlechef.persistence;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

// might not need
import comp3350.littlechef.application.Main;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

public class DataAccessTest extends TestCase
{

    private DataAccess dataAccess;

    // create an empty db for the empty tests
    public DataAccessTest(String arg0)
    {
        super(arg0);
    }

    @BeforeClass
    public void setUp()
    {
        System.out.println("\nStarting Persistence test DataAccess");

        // Use the following statements to run with the stub database:
        //dataAccess = new DataAccessStub();
        //dataAccess.open(Main.dbName);
        System.out.print(" (using the stub db). \n");

        // or switch to the real database:
        dataAccess = new DataAccessObject(Main.dbName);
        dataAccess.open(Main.getDBPathName());
        System.out.print(".\n)");
        // note: this will increase the test execution time.
    }

    @AfterClass
    public void tearDown()
    {
        System.out.println("Finished Persistence test DataAccess.");
        dataAccess.resetDatabase();
        dataAccess.close();
    }

    @Test
    public void testSimpleCases()
    {
        Recipe recipe1 = createRecipe("recipe1");

        // add a regular recipe
        //private DataAccessStub dataAccess;
        String result = dataAccess.insertRecipe(recipe1);
        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(recipe1);
        dataAccess.getRecipeSequential(recipes);
        dataAccess.getRecipeRandom(recipe1);
        dataAccess.deleteRecipe(recipe1);
        //dataAccess.resetDatabase();
    }

    @Test
    public void testEmptyArgument()
    {
        DataAccessObject dataAccessWithEmptyString;

        // open db by sending empty string
        try
        {
            dataAccessWithEmptyString = new DataAccessObject(Main.dbName);
            dataAccessWithEmptyString.open("");
            fail("Wanted an exception for illegal argument.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

    }
    @Test
    public void testEdgeCases()
    {
        DataAccessObject dataAccessWithInccorectPath;


        // sending incorrect path // Kajal: does this count as an edge case?
        /*

        try
        {
            dataAccessWithInccorectPath = new DataAccessObject(Main.dbName);
            dataAccessWithInccorectPath.open("database/");
            fail("Wanted an exception for incorrect path."); // Ask Dylan: why doesn't this throw an excpetion? or maybe we should handle it soe other way?
        }
        catch (Exception se)
        {
            // this is expected
        }
        */


        // add a regular recipe but instantiated
    }

    @Test
    public void testNullArgument()
    {
        // open db by sending null as the path
        DataAccessObject dataAccessWithNull;

        try
        {
            dataAccessWithNull = new DataAccessObject(Main.dbName);
            dataAccessWithNull.open(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        // send null when adding a recipe
    }

    @Test
    public void testSendingInvalidArgument()
    {

        // try to close a database that has not been opened yet.  // needed?

        // send a string instead of a recipe when adding recipe
    }

    @Test
    public void testDbOfSizeOne()
    {
        // test adding the first recipe to the db
    }

    @Test
    public void testDbOfSizeTwo()
    {
        // test adding two recipes to the db
    }

    private Recipe createRecipe(String name)
    {
        Recipe recipe = new Recipe(name);
        recipe.addIngredient(new Ingredient("All-Purpose Flour", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 1));
        recipe.addInstructions("Add salt and flour", "2 minutes");

        return recipe;
    }

}
