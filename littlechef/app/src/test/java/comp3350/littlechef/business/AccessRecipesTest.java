package comp3350.littlechef.business;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.application.Services;
import comp3350.littlechef.application.Main;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.persistence.DataAccess;
import comp3350.littlechef.persistence.DataAccessObject;
import comp3350.littlechef.persistence.DataAccessStub;

public class AccessRecipesTest extends TestCase
{
    private static String DB_NAME = Main.dbName;
    private AccessRecipes accessRecipes;

    public AccessRecipesTest(String arg0) {
        super(arg0);
    }

    @Before
    public void setUp()
    {
        System.out.println("\nStarting to test AccessRecipes.");

       Services.createDataAccess(new DataAccessStub(DB_NAME));
       accessRecipes = new AccessRecipes();
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
        System.out.println("Finished test AccessCourses");
    }

    @Test
    public void testTypicalCases()
    {
        Recipe recipe, returnedRecipe;
        List<Recipe> recipes = new ArrayList<Recipe>();

        // get all the recipes, should only be default ones
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(9, recipes.size());
//        assertEquals("Guacamole", recipes.get(0).getName());
//        assertEquals("Pancakes", recipes.get(1).getName());
//        assertEquals("Chili", recipes.get(2).getName());
//        assertEquals("Chicken Wrap", recipes.get(3).getName());
//        assertEquals("Pizza", recipes.get(4).getName());
//        assertEquals("Chocolate Chip Cookies", recipes.get(5).getName());
//        assertEquals("Perogies", recipes.get(6).getName());
//        assertEquals("Perogies", recipes.get(7).getName());
//        assertEquals("Grilled Halloumi Salad", recipes.get(8).getName());

        for(int i = 0; i < recipes.size(); i++)
        {
            System.out.println(">>>>>>>>>>>>>>>>>"+recipes.get(i).getName());
        }
        // get each recipe sequentially
        recipe = accessRecipes.getSequential();
        assertEquals("Guacamole", recipe.getName());

        recipe = accessRecipes.getSequential();
        assertEquals("Pancakes", recipe.getName());

        recipe = accessRecipes.getSequential();
        assertEquals("Chili", recipe.getName());

        recipe = accessRecipes.getSequential();
        assertEquals("Chicken Wrap", recipe.getName());

        recipe = accessRecipes.getSequential();
        assertEquals("Pizza", recipe.getName());

        recipe = accessRecipes.getSequential();
        assertEquals("Chocolate Chip Cookies", recipe.getName());

        recipe = accessRecipes.getSequential();
        assertEquals("Perogies", recipe.getName());

        recipe = accessRecipes.getSequential();
        assertEquals("Perogies", recipe.getName());

        recipe = accessRecipes.getSequential();
        assertEquals("Grilled Halloumi Salad", recipe.getName());

        recipe = null;

        // get random access to recipes with the ids
        recipe = accessRecipes.getRandom(2);
        assertEquals(2, recipe.getId());
        assertEquals("Chili", recipe.getName());

        recipe = accessRecipes.getRandom(6);
        assertEquals(6, recipe.getId());
        assertEquals("Perogies", recipe.getName());

        // id that does not exist
        assertNull(accessRecipes.getRandom(45));

        // adding a new recipe
        recipe = new Recipe("new recipe");
        assertNull(accessRecipes.insertRecipe(recipe));
        returnedRecipe = accessRecipes.getRandom(9);
        assertEquals(recipe.getName(), returnedRecipe.getName());

        // updating a recipe
        recipe = accessRecipes.getRandom(5);
        recipe.setName("updated recipe");
        assertNull(accessRecipes.updateRecipe(recipe));
        recipe = accessRecipes.getRandom(5);
        assertEquals("updated recipe", recipe.getName());

        // delete a recipe
        recipe = accessRecipes.getRandom(5);
        assertNull(accessRecipes.deleteRecipe(recipe));
        assertNull(accessRecipes.getRandom(5));

        accessRecipes.resetDatabase();
        accessRecipes.getRecipes(recipes);
        assertEquals(9,recipes.size());
    }

    public void testGetRecipeBoundaryCases()
    {
        Recipe recipe;
        List<Recipe> recipes = new ArrayList<Recipe>();
        assertNull(accessRecipes.getRecipes(recipes));

        // first recipe
        recipe = accessRecipes.getSequential();
        assertEquals("Guacamole", recipe.getName());

        // skip to the last recipe
        for(int i = 1; i < recipes.size(); i++)
        {
            accessRecipes.getSequential();
        }

        assertNull(accessRecipes.getSequential());

        // confirm that it wraps around to the start
        recipe = accessRecipes.getSequential();
        assertEquals("Guacamole", recipe.getName());
    }

    @Test
    public void testRandomAccessBoundaryCases()
    {
        Recipe recipe;
        List<Recipe> recipes = new ArrayList<Recipe>();
        accessRecipes.getRecipes(recipes);
        assertNull(accessRecipes.getRandom(-1));

        // lowest id
        recipe = accessRecipes.getRandom(0);
        assertEquals(0, recipe.getId());
        assertEquals("Guacamole", recipe.getName());

        // highest id
        recipe = accessRecipes.getRandom(8);
        assertEquals(8, recipe.getId());
        assertEquals("Grilled Halloumi Salad", recipe.getName());

        // id that doesn't exist
        assertNull(accessRecipes.getRandom(9));
    }

    @Test
    public void testUpdatingRecipeBoundaryCases()
    {
        Recipe recipe, returnedRecipe;
        int lastIndex;
        List<Recipe> recipes = new ArrayList<Recipe>();

        accessRecipes.getRecipes(recipes);
        lastIndex = recipes.size()-1;

        // update first recipe
        recipe = recipes.get(0);
        recipe.setName("New first recipe");
        accessRecipes.updateRecipe(recipe);
        returnedRecipe = accessRecipes.getRandom(0);
        assertEquals("New first recipe", returnedRecipe.getName());

        // update the last recipe
        recipe = recipes.get(lastIndex);
        recipe.setName("New last recipe");
        accessRecipes.updateRecipe(recipe);
        returnedRecipe = accessRecipes.getRandom(lastIndex);
        assertEquals("New last recipe", returnedRecipe.getName());
    }

    @Test
    public void testDeletingRecipeBoundaryCases()
    {
        Recipe recipe, returnedRecipe;
        int lastIndex;
        List<Recipe> recipes = new ArrayList<Recipe>();

        accessRecipes.getRecipes(recipes);
        lastIndex = recipes.size()-1;

        // update first recipe
        recipe = recipes.get(0);
        assertNull(accessRecipes.deleteRecipe(recipe));
        assertNull(accessRecipes.getRandom(0));

        // update the last recipe
        recipe = recipes.get(lastIndex);
        assertNull(accessRecipes.deleteRecipe(recipe));
        assertNull(accessRecipes.getRandom(lastIndex));
    }

    @Test
    public void testUpdatingRecipeNotInDB()
    {
        Recipe recipe = new Recipe("not in DB");
        assertNull(accessRecipes.updateRecipe(recipe));
    }

    @Test
    public void testDeletingRecipeNotInDB()
    {
        Recipe recipe = new Recipe("not in DB");
        assertNull(accessRecipes.deleteRecipe(recipe));
    }


    @Test
    public void testRandomAccessWithNegativeID()
    {
        assertNull(accessRecipes.getRandom(-1));
    }

    @Test
    public void testGettingListOfRecipeTwice()
    {
        List<Recipe> recipes = new ArrayList<Recipe>();
        assertNull(accessRecipes.getRecipes(recipes));
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(9, recipes.size());
        assertEquals("Guacamole", recipes.get(0).getName());
        assertEquals("Pancakes", recipes.get(1).getName());
        assertEquals("Perogies", recipes.get(7).getName());
        assertEquals("Grilled Halloumi Salad", recipes.get(8).getName());
    }

    @Test
    public void testResettingDBTwice()
    {
        List<Recipe> recipes = new ArrayList<Recipe>();
        accessRecipes.resetDatabase();
        accessRecipes.resetDatabase();
        accessRecipes.getRecipes(recipes);
        assertEquals(9,recipes.size());
    }

    @Test
    public void testDoingThingsWithoutOpeningDBConnection()
    {
        Recipe recipe = new Recipe("not in db");

        Services.closeDataAccess();
        List<Recipe> recipes = new ArrayList<Recipe>();
        assertEquals("connection is not open.",accessRecipes.getRecipes(recipes));
        assertNull(accessRecipes.getSequential());
        assertNull(accessRecipes.getRandom(0));
        assertEquals("connection is not open.",accessRecipes.insertRecipe(recipe));
        assertEquals("connection is not open.",accessRecipes.updateRecipe(recipe));
        assertEquals("connection is not open.",accessRecipes.deleteRecipe(recipe));
        assertEquals("connection is not open.",accessRecipes.deleteRecipe(recipe));
    }

    @Test
    public void testEmptyDBPath()
    {
        try
        {
            Services.createDataAccess("");
            fail("Wanted an exception for illegal argument.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }
    }


    @Test
    public void testSendingArrayListToGetRecipes()
    {
        ArrayList recipes = new ArrayList();
        Recipe recipe;
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(9, recipes.size());
        recipe = (Recipe) recipes.get(0);
        assertEquals("Guacamole", recipe.getName());
        recipe = (Recipe) recipes.get(1);
        assertEquals("Pancakes", recipe.getName());
        recipe = (Recipe) recipes.get(7);
        assertEquals("Perogies", recipe.getName());
        recipe = (Recipe) recipes.get(8);
        assertEquals("Grilled Halloumi Salad", recipe.getName());
    }

    @Test
    public void testNullArgument()
    {
        DataAccess access = null;
        String db = null;
        List<Recipe> nullList = null;
        Recipe nullRecipe = null;

        try
        {
            Services.createDataAccess(access);
            fail("Wanted an exception for null argument.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        try
        {
            Services.createDataAccess(db);
            fail("Wanted an exception for null argument.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        try
        {
            accessRecipes.getRecipes(nullList);
            fail("Wanted an exception for null argument.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        try
        {
            accessRecipes.updateRecipe(nullRecipe);
            fail("Wanted an exception for null argument.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        try
        {
            accessRecipes.deleteRecipe(nullRecipe);
            fail("Wanted an exception for null argument.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }
    }

    @Test
    public void testSizeZeroDB()
    {
        List<Recipe> recipes = new ArrayList<Recipe>();
        Recipe recipe = new Recipe("recipe not in db");

        // delete all recipes, then try getRecipes
        deleteEverything();
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(0, recipes.size());

        assertNull(accessRecipes.getSequential());
        assertNull(accessRecipes.getRandom(0));
        assertNull(accessRecipes.updateRecipe(recipe));
        assertNull(accessRecipes.deleteRecipe(recipe));

        accessRecipes.resetDatabase();
        accessRecipes.getRecipes(recipes);
        assertEquals(9,recipes.size());
    }

    @Test
    public void testSizeOneDB()
    {
        Recipe recipe, returnedRecipe;
        List<Recipe> recipes = new ArrayList<Recipe>();

        // add one recipe to an empty db, then try getRecipes
        deleteEverything();
        accessRecipes.insertRecipe(new Recipe("a recipe"));
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(1, recipes.size());
        recipe = recipes.get(0);
        assertEquals("a recipe", recipe.getName());

        // test getting sequential with only one recipe
        deleteEverything();
        recipe = new Recipe("a new recipe");
        accessRecipes.insertRecipe(recipe);

        // get the recipe
        returnedRecipe = accessRecipes.getSequential();
        assertEquals(recipe.getName(), returnedRecipe.getName());

        // get the recipe we just added with id
        recipe = accessRecipes.getRandom(10);
        assertEquals(10, recipe.getId());

        // update the only recipe
        recipe.setName("Updated recipe");
        assertNull(accessRecipes.updateRecipe(recipe));
        assertEquals("Updated recipe", accessRecipes.getRandom(10).getName());

        // delete the only recipe
        assertNull(accessRecipes.deleteRecipe(recipe));
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(0, recipes.size());

        // reset db
        accessRecipes.resetDatabase();
        accessRecipes.getRecipes(recipes);
        assertEquals(9,recipes.size());
    }

    @Test
    public void testGetRecipeBoundaryCasesDBSizeOne()
    {
        Recipe recipe, returnedRecipe;
        List<Recipe> recipes = new ArrayList<Recipe>();

        // test getting sequential with only one recipe
        deleteEverything();
        recipe = new Recipe("a new recipe");

        accessRecipes.insertRecipe(recipe);

        // get the recipe
        returnedRecipe = accessRecipes.getSequential();
        assertEquals(recipe.getName(), returnedRecipe.getName());
        returnedRecipe = null;

        // get null because its the oly recipe in the db
        assertNull( accessRecipes.getSequential());

        // get the recipe again because it wraps around
        returnedRecipe = accessRecipes.getSequential();
        assertEquals(recipe.getName(), returnedRecipe.getName());

        // delete the only recipe
        assertNull(accessRecipes.deleteRecipe(returnedRecipe));
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(0, recipes.size());
    }

    @Test
    public void testSizeTwoDB()
    {
        Recipe recipe1, recipe2, returnedRecipe;
        Recipe recipe;
        List<Recipe> recipes = new ArrayList<Recipe>();

        // add two recipes to an empty db, the try getRecipes
        deleteEverything();
        accessRecipes.insertRecipe(new Recipe("recipe 1"));
        accessRecipes.insertRecipe(new Recipe("recipe 2"));
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(2, recipes.size());
        recipe1 = recipes.get(0);
        recipe2 = recipes.get(1);
        assertEquals("recipe 1", recipe1.getName());
        assertEquals("recipe 2", recipe2.getName());

        // add two recipes to an empty db, the try getRecipes
        deleteEverything();
        recipe1 = new Recipe("Recipe 1");
        recipe2 = new Recipe("Recipe 2");
        accessRecipes.insertRecipe(recipe1);
        accessRecipes.insertRecipe(recipe2);

        returnedRecipe = accessRecipes.getSequential();
        assertEquals(recipe1.getName(), returnedRecipe.getName());
        returnedRecipe = null;

        returnedRecipe = accessRecipes.getSequential();
        assertEquals(recipe2.getName(), returnedRecipe.getName());

        // get the recipe we just added with id
        recipe = accessRecipes.getRandom(11);
        assertEquals(11, recipe.getId());
        assertEquals(recipe1.getName(), recipe.getName());

        recipe = accessRecipes.getRandom(12);
        assertEquals(12, recipe.getId());
        assertEquals(recipe2.getName(), recipe.getName());

        // update the both recipes
        recipe1 = accessRecipes.getRandom(11);
        recipe1.setName("updated recipe 1");
        accessRecipes.updateRecipe(recipe1);
        recipe = accessRecipes.getRandom(11);
        assertEquals("updated recipe 1", recipe.getName());

        recipe2 = accessRecipes.getRandom(12);
        recipe2.setName("updated recipe 2");
        accessRecipes.updateRecipe(recipe2);
        recipe = accessRecipes.getRandom(12);
        assertEquals("updated recipe 2", recipe.getName());

        // delete the first recipe only
        recipe1 = accessRecipes.getRandom(11);
        assertNull(accessRecipes.deleteRecipe(recipe1));
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(1, recipes.size());
        returnedRecipe = recipes.get(0);
        assertEquals(recipe2.getName(), returnedRecipe.getName());

        // delete the second recipe only
        recipe1 = new Recipe("recipe 1 again");
        accessRecipes.insertRecipe(recipe1);
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(2, recipes.size());
        assertNull(accessRecipes.deleteRecipe(recipe1));
        assertNull(accessRecipes.getRecipes(recipes));
        assertEquals(1, recipes.size());
        returnedRecipe = recipes.get(0);
        assertEquals(recipe2.getName(), returnedRecipe.getName());

        // reset the db
        deleteEverything();
        recipe1 = new Recipe("Recipe 1");
        recipe2 = new Recipe("Recipe 2");
        accessRecipes.insertRecipe(recipe1);
        accessRecipes.insertRecipe(recipe2);
        accessRecipes.resetDatabase();
        accessRecipes.getRecipes(recipes);
        assertEquals(9,recipes.size());
    }

    @Test
    public void testGetRecipeBoundaryCasesDBSizeTwo()
    {
        Recipe recipe1, recipe2, returnedRecipe;

        // add two recipes to an empty db, the try getRecipes
        deleteEverything();
        recipe1 = new Recipe("Recipe 1");
        recipe2 = new Recipe("Recipe 2");
        accessRecipes.insertRecipe(recipe1);
        accessRecipes.insertRecipe(recipe2);

        returnedRecipe = accessRecipes.getSequential();
        assertEquals(recipe1.getName(), returnedRecipe.getName());
        returnedRecipe = null;

        returnedRecipe = accessRecipes.getSequential();
        assertEquals(recipe2.getName(), returnedRecipe.getName());
        returnedRecipe = null;

        // get null because there are no more recipes
        assertNull(accessRecipes.getSequential());

        // get the first recipe again because it wraps around
        returnedRecipe = accessRecipes.getSequential();
        assertEquals(recipe1.getName(), returnedRecipe.getName());
    }

    private void deleteEverything()
    {
        Recipe recipe;
        List<Recipe> recipes = new ArrayList<Recipe>();
        assertNull(accessRecipes.getRecipes(recipes));

        for(int i =0; i <recipes.size(); i++)
        {
            recipe = recipes.get(i);
            accessRecipes.deleteRecipe(recipe);
        }
    }

}
