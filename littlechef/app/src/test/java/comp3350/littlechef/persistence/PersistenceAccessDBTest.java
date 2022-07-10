package comp3350.littlechef.persistence;

import static comp3350.littlechef.persistence.PersistenceAccessDB.*;
import static comp3350.littlechef.persistence.DataAccessStub.*;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

// might not need
import comp3350.littlechef.application.Main;
import comp3350.littlechef.application.Services;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

public class PersistenceAccessDBTest extends TestCase
{

    //private DataAccessStub dbAccess;
    private String result;
    private PersistenceAccessDB dbAccess;

    // create an empty db for the empty tests

    public void setUp()
    {
        System.out.println("\nStarting Persistence test DataAccess.");

        // Use the following statements to run with the stub database:
        // dbAccess = new DataAccessStub();
        // dbAccess.open(Main.dbName);

        //or switch to the real database:
        dbAccess = new PersistenceAccessDB(Main.dbName);
        dbAccess.open(Main.getDBPathName());
    }

    public void tearDown()
    {
        System.out.println("Finished Persistence test DataAccess.");
        dbAccess.close();
    }

    @Test
    public void testSimpleCases()
    {
        Recipe recipe1 = createRecipe("recipe1");

        // add a regular recipe
        result = dbAccess.insertRecipe(recipe1);
        System.out.println("RESULT" + result);
        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(recipe1);
        dbAccess.getRecipeSequential(recipes);
        dbAccess.getRecipeRandom(recipe1);
        dbAccess.deleteRecipe(recipe1);
        //dbAccess.resetDatabase();
    }

    @Test
    public void testEmptyArgument()
    {
        PersistenceAccessDB dataAccessWithEmptyString;

        // open db by sending empty string
        try
        {
            dataAccessWithEmptyString = new PersistenceAccessDB(Main.dbName);
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
        PersistenceAccessDB dataAccessWithInccorectPath;


        // sending incorrect path // Kajal: does this count as an edge case?
        /*

        try
        {
            dataAccessWithInccorectPath = new PersistenceAccessDB(Main.dbName);
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
    public void testSendingNullArgument()
    {
        // open db by sending null as the path
        PersistenceAccessDB dataAccessWithNull;

        try
        {
            dataAccessWithNull = new PersistenceAccessDB(Main.dbName);
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
