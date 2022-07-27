package comp3350.littlechef.integration;

import android.text.method.HideReturnsTransformationMethod;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.application.Services;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.persistence.DataAccessStub;


public class BusinessPersistenceSeamTest extends TestCase
{

    private static String DB_NAME = Main.dbName;
    private AccessRecipes accessRecipes;

    @Before
    public void setUp()
    {
        System.out.println("\nStarting to test the seam between the business and persistence.");

        Services.createDataAccess(new DataAccessStub(DB_NAME));
        accessRecipes = new AccessRecipes();
        accessRecipes.resetDatabase();
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
        System.out.println("Finished tesing the seam between the business and persistence.");
    }

    @Test
    public void testTypicalCase()
    {
        Recipe recipe, returnedRecipe;
        List<Recipe> allRecipes = new ArrayList<Recipe>();
        int listSize;

        // create a recipe
        recipe = new Recipe("testRecipe");

        // insert it
        assertNull(accessRecipes.insertRecipe(recipe));

        // get random access to it
        returnedRecipe = accessRecipes.getRandom(recipe.getId());
        assertEquals(recipe, returnedRecipe);

        // update the recipe
        recipe.setName("New name");
        assertNull(accessRecipes.updateRecipe(recipe));
        returnedRecipe = accessRecipes.getRandom(recipe.getId());
        assertEquals(recipe, returnedRecipe);
        assertEquals("New name", returnedRecipe.getName());

        // get the entire list of recipes and then get the recipe we just added
        allRecipes.clear();
        accessRecipes.getRecipes(allRecipes);
        listSize = allRecipes.size();
        assertEquals(10, listSize);
        assertEquals(recipe, allRecipes.get(9));

        // delete the recipe
        assertNull(accessRecipes.deleteRecipe(recipe));
        assertNull(accessRecipes.getRandom(recipe.getId()));
    }

    @Test
    public void testRetrievingDeletedRecipe()
    {
        Recipe recipe;
        Recipe returnedRecipe;
        List<Recipe> allRecipes = new ArrayList<Recipe>();
        int listSize;

        // create a recipe
        recipe = new Recipe("testRecipe");

        // insert it
        assertNull(accessRecipes.insertRecipe(recipe));

        // get random access to it
        returnedRecipe = accessRecipes.getRandom(recipe.getId());
        assertEquals(recipe, returnedRecipe);

        // delete the recipe
        assertNull(accessRecipes.deleteRecipe(recipe));

        // try to update deleted recipe
        recipe.setName("new name");
        assertNull(accessRecipes.updateRecipe(recipe));

        // try to access deleted recipe
        assertNull(accessRecipes.getRandom(recipe.getId()));

        // now get a list of all the recipes and try to get the recipe that way
        allRecipes.clear();
        accessRecipes.getRecipes(allRecipes);
        assertEquals(9, allRecipes.size());
        assertFalse(allRecipes.contains(recipe));

        // sequentially iterate through all the recipes to see if we can find the deleted one
        returnedRecipe = null;
        returnedRecipe = accessRecipes.getSequential();

        while(returnedRecipe != null)
        {
            returnedRecipe = accessRecipes.getSequential();

            if(returnedRecipe.equals(recipe))
            {
                // should never happen
                fail();
                break;
            }
        }
    }

    @Test
    public void testRetrievingNonDefaultRecipeAfterDBReset()
    {
        Recipe recipe;
        Recipe returnedRecipe;
        List<Recipe> allRecipes = new ArrayList<Recipe>();
        int listSize;

        // create a recipe
        recipe = new Recipe("testRecipe");

        // insert it
        assertNull(accessRecipes.insertRecipe(recipe));

        // get random access to it
        returnedRecipe = accessRecipes.getRandom(recipe.getId());
        assertEquals(recipe, returnedRecipe);

        // reset the recipe
       accessRecipes.resetDatabase();

        // try to update deleted recipe
        recipe.setName("new name");
        assertNull(accessRecipes.updateRecipe(recipe));

        // try to access deleted recipe
        assertNull(accessRecipes.getRandom(recipe.getId()));

        // now get a list of all the recipes and try to get the recipe that way
        allRecipes.clear();
        accessRecipes.getRecipes(allRecipes);
        assertEquals(9, allRecipes.size());
        assertFalse(allRecipes.contains(recipe));

        // sequentially iterate through all the recipes to see if we can find the deleted one
        returnedRecipe = accessRecipes.getSequential();

        while(returnedRecipe != null)
        {
            returnedRecipe = accessRecipes.getSequential();

            if(returnedRecipe.equals(recipe))
            {
                // should never happen
                fail();
                break;
            }
        }
    }

    @Test
    public void testCRUDStartingWithEmptyDB()
    {
        Recipe recipe, returnedRecipe;
        List<Recipe> allRecipes = new ArrayList<Recipe>();
        int listSize;

        deleteAllRecipes();

        // create a recipe
        recipe = new Recipe("testRecipe");

        // insert it
        assertNull(accessRecipes.insertRecipe(recipe));

        // get random access to it
        returnedRecipe = accessRecipes.getRandom(recipe.getId());
        assertEquals(recipe, returnedRecipe);

        // update the recipe
        recipe.setName("New name");
        assertNull(accessRecipes.updateRecipe(recipe));
        returnedRecipe = accessRecipes.getRandom(recipe.getId());
        assertEquals(recipe, returnedRecipe);
        assertEquals("New name", returnedRecipe.getName());

        // get the entire list of recipes and then get the recipe we just added
        allRecipes.clear();
        accessRecipes.getRecipes(allRecipes);
        listSize = allRecipes.size();
        assertEquals(1, listSize);
        assertEquals(recipe, allRecipes.get(0));

        // delete the recipe
        assertNull(accessRecipes.deleteRecipe(recipe));
        assertNull(accessRecipes.getRandom(recipe.getId()));
    }

    private void deleteAllRecipes()
    {
        Recipe returnedRecipe;

        returnedRecipe = accessRecipes.getSequential();

        while(returnedRecipe != null)
        {
            accessRecipes.deleteRecipe(returnedRecipe);
            returnedRecipe = accessRecipes.getSequential();
        }
    }

}
