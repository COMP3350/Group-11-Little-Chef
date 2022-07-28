package comp3350.littlechef.integration;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.application.Services;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;
import comp3350.littlechef.persistence.DataAccess;

public class DataAccessHSQLDBTest extends TestCase
{
    private static String dbName = Main.dbName;
    private static DataAccess dataAccess;

    public DataAccessHSQLDBTest(String arg0)
    {
        super(arg0);
    }

    @Before
    public void setUp()
    {
        System.out.println("\nStarting to test the seam between the Persistence and DB.");

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        // Use the following two statements to run with the real database
        Services.createDataAccess(dbName);
        dataAccess = Services.getDataAccess(dbName);
        dataAccess.resetDatabase();
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
        System.out.println("Finished testing the seam between the persistence and DB.");
    }

    public void testAllDBAccessObjectMethods()
    {
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Recipe recipe = new Recipe("recipe");
        assertNull(dataAccess.insertRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));
        assertEquals("recipe",(recipeList).get(0).getName());

        recipeList.clear();
        recipe.setName("updatedRecipe");
        recipe.addIngredient(new Ingredient("testIngredient", Unit.CUP, 1.0));
        recipe.addInstructions("instructions", "sub-instructions");

        assertNull(dataAccess.updateRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));

        assertEquals("updatedRecipe",(recipeList).get(0).getName());
        assertEquals("testIngredient",(recipeList).get(0).getIngredients().get(0).getName());
        assertEquals("CUP",(recipeList).get(0).getIngredients().get(0).getUnit());
        assertEquals(1.0,(recipeList).get(0).getIngredients().get(0).getAmount());
        assertEquals("instructions",(recipeList).get(0).getInstructions().get(0)[0]);
        assertEquals("sub-instructions",(recipeList).get(0).getInstructions().get(0)[1]);

        recipeList.clear();
        recipeList = dataAccess.getRecipeRandom(recipe);

        assertEquals("updatedRecipe",(recipeList).get(0).getName());
        assertEquals("testIngredient",(recipeList).get(0).getIngredients().get(0).getName());
        assertEquals("CUP",(recipeList).get(0).getIngredients().get(0).getUnit());
        assertEquals(1.0,(recipeList).get(0).getIngredients().get(0).getAmount());
        assertEquals("instructions",(recipeList).get(0).getInstructions().get(0)[0]);
        assertEquals("sub-instructions",(recipeList).get(0).getInstructions().get(0)[1]);

        recipeList.clear();
        assertNull(dataAccess.deleteRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));

        recipeList.clear();
        recipeList = dataAccess.getRecipeRandom(recipe);
        assertTrue(recipeList.isEmpty());

    }

    public void testDBAfterNullParameterCalls()
    {
        try
        {
            dataAccess.insertRecipe(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }
        try
        {
            dataAccess.getRecipeSequential(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }
        try
        {
            dataAccess.updateRecipe(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }
        try
        {
            dataAccess.getRecipeRandom(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }
        try
        {
            dataAccess.deleteRecipe(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Recipe recipe = new Recipe("recipeName");
        assertNull(dataAccess.insertRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));
        assertEquals("recipeName",(recipeList).get(0).getName());

        recipeList.clear();
        recipe.setName("newRecipeName");
        recipe.addIngredient(new Ingredient("firstIngredient", Unit.MM, 55.0));
        recipe.addInstructions("instruction1", "instruction2");

        assertNull(dataAccess.updateRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));

        assertEquals("newRecipeName",(recipeList).get(0).getName());
        assertEquals("firstIngredient",(recipeList).get(0).getIngredients().get(0).getName());
        assertEquals("MM",(recipeList).get(0).getIngredients().get(0).getUnit());
        assertEquals(55.0,(recipeList).get(0).getIngredients().get(0).getAmount());
        assertEquals("instruction1",(recipeList).get(0).getInstructions().get(0)[0]);
        assertEquals("instruction2",(recipeList).get(0).getInstructions().get(0)[1]);

        recipeList.clear();
        recipeList = dataAccess.getRecipeRandom(recipe);

        assertEquals("newRecipeName",(recipeList).get(0).getName());
        assertEquals("firstIngredient",(recipeList).get(0).getIngredients().get(0).getName());
        assertEquals("MM",(recipeList).get(0).getIngredients().get(0).getUnit());
        assertEquals(55.0,(recipeList).get(0).getIngredients().get(0).getAmount());
        assertEquals("instruction1",(recipeList).get(0).getInstructions().get(0)[0]);
        assertEquals("instruction2",(recipeList).get(0).getInstructions().get(0)[1]);

        recipeList.clear();
        assertNull(dataAccess.deleteRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));

        recipeList.clear();
        recipeList = dataAccess.getRecipeRandom(recipe);
        assertTrue(recipeList.isEmpty());

    }

    public void testDBAfterClosedDBCalls()
    {
        Services.closeDataAccess();

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Recipe recipe = new Recipe("recipe");
        assertEquals("connection is not open.", dataAccess.insertRecipe(recipe));
        assertEquals("connection is not open.", dataAccess.updateRecipe(recipe));
        assertEquals("connection is not open.", dataAccess.getRecipeSequential(recipeList));
        assertNull(dataAccess.getRecipeRandom(recipe));
        assertEquals("connection is not open.", dataAccess.deleteRecipe(recipe));
        
        Services.createDataAccess(dbName);
        dataAccess = Services.getDataAccess(dbName);

        assertNull(dataAccess.insertRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));
        assertEquals("recipe",(recipeList).get(0).getName());

        recipeList.clear();
        recipe.setName("newName");
        recipe.addIngredient(new Ingredient("addedIngredient", Unit.QUANTITY, 3.0));
        recipe.addInstructions("addedInstructions", "addedSubInstructions");

        assertNull(dataAccess.updateRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));

        assertEquals("newName",(recipeList).get(0).getName());
        assertEquals("addedIngredient",(recipeList).get(0).getIngredients().get(0).getName());
        assertEquals("QUANTITY",(recipeList).get(0).getIngredients().get(0).getUnit());
        assertEquals(3.0,(recipeList).get(0).getIngredients().get(0).getAmount());
        assertEquals("addedInstructions",(recipeList).get(0).getInstructions().get(0)[0]);
        assertEquals("addedSubInstructions",(recipeList).get(0).getInstructions().get(0)[1]);

        recipeList.clear();
        recipeList = dataAccess.getRecipeRandom(recipe);

        assertEquals("newName",(recipeList).get(0).getName());
        assertEquals("addedIngredient",(recipeList).get(0).getIngredients().get(0).getName());
        assertEquals("QUANTITY",(recipeList).get(0).getIngredients().get(0).getUnit());
        assertEquals(3.0,(recipeList).get(0).getIngredients().get(0).getAmount());
        assertEquals("addedInstructions",(recipeList).get(0).getInstructions().get(0)[0]);
        assertEquals("addedSubInstructions",(recipeList).get(0).getInstructions().get(0)[1]);

        recipeList.clear();
        assertNull(dataAccess.deleteRecipe(recipe));
        assertNull(dataAccess.getRecipeSequential(recipeList));

        recipeList.clear();
        recipeList = dataAccess.getRecipeRandom(recipe);
        assertTrue(recipeList.isEmpty());
    }

}
