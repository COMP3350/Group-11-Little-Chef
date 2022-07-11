package comp3350.littlechef.persistence;

import android.provider.ContactsContract;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// might not need
import comp3350.littlechef.application.Main;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

public class DataAccessTest extends TestCase
{
    private static final boolean TEST_ON_STUB = true; // CHANGE THIS TO FALSE TO TEST ON THE REAL DB
    private static String DB_NAME = Main.dbName;
    
    private static DataAccess dataAccess;
    
    // create an empty db for the empty tests
    public DataAccessTest(String arg0)
    {
        super(arg0);
    }

    @Before
    public void setUp()
    {
        System.out.println("\nStarting Persistence test DataAccess.");

        if(TEST_ON_STUB)
        {
            dataAccess = new DataAccessStub();
            dataAccess.open(DB_NAME);
            System.out.println("Used the stub db.");
        }
        else
        {
            dataAccess = new DataAccessObject(DB_NAME);
            dataAccess.open(Main.getDBPathName());
        }
    }

    @After
    public void tearDown()
    {
        System.out.println("Finished Persistence test DataAccess.");
        dataAccess.resetDatabase();
        dataAccess.close();
    }

    @Test
    public void testSimpleCases()
    {
        DataAccess access1 = createAccess();
        assertTrue(access1.open(DB_NAME));
        assertTrue(access1.close());

        dataAccess.insertRecipe(new Recipe("recipe"));
    }

    @Test
    public void testEmptyArgument()
    {
        DataAccess dataAccessWithEmptyString = createAccess();
        // open db by sending empty string

        try
        {
            dataAccessWithEmptyString.open("");
            fail("Wanted an exception for illegal argument.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

        try
        {
            dataAccessWithEmptyString.open(" ");
            fail("Wanted an exception for illegal argument.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

        try
        {
            dataAccessWithEmptyString.open("  ");
            fail("Wanted an exception for illegal argument.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

    }

    @Test
    public void testNullArgument()
    {
        DataAccess dataAccessWithNull = createAccess();

        try
        {
            dataAccessWithNull.open(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }
    }

    @Test
    public void testLeadingSpacesInArgument()
    {
        DataAccess access1 = createAccess();
        assertTrue(access1.open(" "+DB_NAME)); // once default data is added you can check that this opened our db

        DataAccess access2 = createAccess();
        assertTrue(access2.open("  "+DB_NAME));
    }

    @Test
    public void testTrailingSpacesInArgument()
    {
        DataAccess access1 = createAccess();
        assertTrue(access1.open(DB_NAME+" "));

        DataAccess access2 = createAccess();
        assertTrue(access2.open(DB_NAME+"  "));
    }

    @Test
    public void testLeadingAndTrailingSpacesInArgument()
    {
        DataAccess access = createAccess();
        assertTrue(access.open("  "+DB_NAME+" "));
    }

    @Test
    public void testSendingInvalidArgument()
    {
        // try to close a database that has not been opened yet.  // needed?

        // send a string instead of a recipe when adding recipe
    }

    @Test
    public void testDoingThingsWithoutOpeningDBConnection()
    {
        DataAccess access = createAccess();
        assertFalse(access.close());
    }

    private Recipe createRecipe(String name)
    {
        Recipe recipe = new Recipe(name);
        recipe.addIngredient(new Ingredient("All-Purpose Flour", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 1));
        recipe.addInstructions("Add salt and flour", "2 minutes");

        return recipe;
    }

    private DataAccess createAccess(){
        DataAccess access;

        if(TEST_ON_STUB)
        {
            access = new DataAccessStub(DB_NAME);
        }
        else
        {
            access = new DataAccessObject(DB_NAME);
        }

        return access;
    }

}
