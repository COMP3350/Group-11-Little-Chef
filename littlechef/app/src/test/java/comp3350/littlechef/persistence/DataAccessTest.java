package comp3350.littlechef.persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.objects.Recipe;

public class DataAccessTest extends TestCase
{
    private static final boolean TEST_ON_STUB = false; // CHANGE THIS TO FALSE TO TEST ON THE REAL DB
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
        dataAccess.resetDatabase();
    }

    @After
    public void tearDown()
    {
        System.out.println("Finished Persistence test DataAccess.");
        dataAccess.resetDatabase();
        dataAccess.close();
    }

    public static void dataAccessTest(DataAccess dataAccess) {
        DataAccessTest dataAccessTest = new DataAccessTest("");
        dataAccessTest.dataAccess = dataAccess;
        //dataAccessTest.test1(); just do the tests here?
    }

    @Test
    public void testTypicalCases()
    {
        Recipe recipe1, recipe2, recipe3, recipe4, recipe5;
        List<Recipe> returnedList = new ArrayList<>();
        List<Recipe> listOfRecipes = new ArrayList<>();

        // creating and closing db
        DataAccess access1 = createAccess();
        assertTrue(access1.open(DB_NAME));
        assertTrue(access1.close());

        // inserting recipe
        assertNull(dataAccess.insertRecipe(new Recipe("recipe")));

        // updating recipe
        recipe1 = new Recipe("a recipe");
        dataAccess.insertRecipe(recipe1);

        recipe2 = new Recipe("Updated recipe");
        recipe1 = recipe2;

        assertNull(dataAccess.updateRecipe(recipe1));
        assertEquals(recipe2, recipe1);

        // getting all the recipes
        assertNull(dataAccess.getRecipeSequential(listOfRecipes));
        assertTrue(listOfRecipes.size() > 0);
        assertEquals(2, listOfRecipes.size());
        assertEquals("recipe", listOfRecipes.get(0).getName());
        assertEquals("a recipe", listOfRecipes.get(1).getName());


        // getting random access
        recipe3 = new Recipe("Recipe 3");
        assertNull(dataAccess.insertRecipe(recipe3));
        returnedList.addAll(dataAccess.getRecipeRandom(recipe3));
        assertEquals(1, returnedList.size());
        assertEquals(recipe3, returnedList.get(0));

        returnedList.clear();
        recipe4 = new Recipe("not in db");
        returnedList = dataAccess.getRecipeRandom(recipe4);
        assertEquals(0, returnedList.size());

        // deleting a recipe
        returnedList.clear();
        recipe5 = new Recipe("to delete");
        assertNull(dataAccess.insertRecipe(recipe5));
        returnedList = dataAccess.getRecipeRandom(recipe5);
        assertEquals(recipe5, returnedList.get(0));
        returnedList.clear();
        assertNull(dataAccess.deleteRecipe(recipe5));
        returnedList = dataAccess.getRecipeRandom(recipe5);
        assertEquals(0, returnedList.size());
    }

    @Test
    public void testGettingRandomAccessBoundaryCases()
    {
        List<Recipe> listOfRecipes = new ArrayList<>();
        List<Recipe> returnedList;
        int lastRecipesIndex;

        dataAccess.insertRecipe(new Recipe("Guacamole"));
        dataAccess.insertRecipe(new Recipe("Grilled Halloumi Salad"));

        // get all the recipes
        assertNull(dataAccess.getRecipeSequential(listOfRecipes));
        lastRecipesIndex = listOfRecipes.size()-1;

        // get random access to the first item
        returnedList = dataAccess.getRecipeRandom(listOfRecipes.get(0));
        assertEquals(1, returnedList.size());
        assertEquals("Guacamole", returnedList.get(0).getName());

        // get random access to the last item
        returnedList = dataAccess.getRecipeRandom(listOfRecipes.get(lastRecipesIndex));
        assertEquals(1, returnedList.size());
        assertEquals("Grilled Halloumi Salad", returnedList.get(0).getName());
    }

    @Test
    public void testGettingRandomAccessToRecipeNotInDB()
    {
        Recipe notAddedRecipe = new Recipe("nothing");
        List<Recipe> returnedList;

        returnedList = dataAccess.getRecipeRandom(notAddedRecipe);
        assertEquals(0,returnedList.size());

        deleteEverything();
        returnedList = dataAccess.getRecipeRandom(notAddedRecipe);
        assertEquals(0,returnedList.size());
    }

    @Test
    public void testDoingThingsWithoutOpeningDBConnection()
    {
        DataAccess access;
        List<Recipe> listOfRecipes = new ArrayList<Recipe>();
        Recipe recipe = new Recipe("Potato");

        access = createAccess();
        assertFalse(access.close());

        assertEquals("connection is not open.", access.insertRecipe(new Recipe("Some Recipe")));
        assertEquals("connection is not open.", access.updateRecipe(recipe));
        assertEquals("connection is not open.", access.getRecipeSequential(listOfRecipes));
        assertNull(access.getRecipeRandom(recipe));
        assertEquals("connection is not open.", access.deleteRecipe(recipe));
        assertEquals("connection is not open.", access.resetDatabase());
    }

    @Test
    public void testEmptyDBPath()
    {
        DataAccess dataAccessWithEmptyString;
        dataAccessWithEmptyString = createAccess();
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
    public void testLeadingSpacesDBPath()
    {
        DataAccess access1 = createAccess();
        assertTrue(access1.open(" "+DB_NAME)); // once default data is added you can check that this opened our db

        DataAccess access2 = createAccess();
        assertTrue(access2.open("  "+DB_NAME));
    }

    @Test
    public void testTrailingSpacesInPath()
    {
        DataAccess access1 = createAccess();
        assertTrue(access1.open(DB_NAME+" "));

        DataAccess access2 = createAccess();
        assertTrue(access2.open(DB_NAME+"  "));
    }

    @Test
    public void testLeadingAndTrailingSpacesInDBPath()
    {
        DataAccess access = createAccess();
        assertTrue(access.open("  "+DB_NAME+" "));
    }

    @Test
    public void testSendingArrayListToGetSequental()
    {
        Recipe recipe;
        ArrayList recipes = new ArrayList();
        assertNull(dataAccess.insertRecipe(new Recipe("Guacamole")));
        assertNull(dataAccess.insertRecipe(new Recipe("Pancakes")));
        assertNull(dataAccess.insertRecipe(new Recipe("Perogies")));
        assertNull(dataAccess.insertRecipe(new Recipe("Grilled Halloumi Salad")));
        assertNull(dataAccess.getRecipeSequential(recipes));

        assertEquals(4, recipes.size());
        recipe = (Recipe) recipes.get(0);
        assertEquals("Guacamole", recipe.getName());
        recipe = (Recipe) recipes.get(1);
        assertEquals("Pancakes", recipe.getName());
        recipe = (Recipe) recipes.get(2);
        assertEquals("Perogies", recipe.getName());
        recipe = (Recipe) recipes.get(3);
        assertEquals("Grilled Halloumi Salad", recipe.getName());
    }


    @Test
    public void testNullArgument()
    {
        DataAccess dataAccessWithNull;
        List<Recipe> listOfRecipes = null;
        Recipe nullRecipe = null;
        Recipe recipe1 = new Recipe("Recipe 1");
        Recipe recipe2 = null;
        dataAccessWithNull = createAccess();

        try
        {
            dataAccessWithNull.open(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        try
        {
            dataAccess.insertRecipe(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        dataAccess.insertRecipe(recipe1);
        recipe1 = nullRecipe;

        try
        {
            dataAccess.updateRecipe(recipe1);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        try
        {
            dataAccess.getRecipeSequential(listOfRecipes);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        try
        {
            dataAccess.getRecipeRandom(recipe2);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }


        try
        {
            dataAccess.deleteRecipe(recipe2);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }
    }

    @Test
    public void testResetEmptyDB()
    {
        List<Recipe> oldList = new ArrayList<>();
        List<Recipe> newList = new ArrayList<>();

        dataAccess.getRecipeSequential(oldList);
        deleteEverything();
        assertNull(dataAccess.resetDatabase());
        dataAccess.getRecipeSequential(newList);
        assertEquals(0, newList.size());
    }

    @Test
    public void testSizeZeroDB()
    {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe1 = new Recipe("Potato Salad");

        deleteEverything();
        dataAccess.getRecipeSequential(recipes);
        assertEquals(0, recipes.size());

        recipes.clear();
        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        dataAccess.getRecipeSequential(recipes);
        assertEquals(1, recipes.size());

        recipes.clear();
        deleteEverything();
        assertNull(dataAccess.updateRecipe(recipe1));
        dataAccess.getRecipeSequential(recipes);
        assertEquals(0, recipes.size());

        recipes.clear();
        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        dataAccess.getRecipeSequential(recipes);
        assertEquals(1, recipes.size());
        recipe1.setName("My potato salad");
        assertNull(dataAccess.updateRecipe(recipe1));
        assertEquals(1, recipes.size());

        recipes.clear();
        deleteEverything();
        recipes = dataAccess.getRecipeRandom(recipe1);
        assertEquals(0, recipes.size());

        recipes.clear();
        deleteEverything();
        dataAccess.deleteRecipe(recipe1);
        dataAccess.getRecipeSequential(recipes);
        assertEquals(0, recipes.size());
    }

    @Test
    public void testSizeOneDB()
    {
        List<Recipe> recipes = new ArrayList<>();
        List<Recipe> recipes2 = new ArrayList<>();
        Recipe recipe1 = new Recipe("Potato Salad");

        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        dataAccess.getRecipeSequential(recipes);
        assertEquals(1, recipes.size());

        recipes.clear();
        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        recipe1.setName("New potato salad");
        assertNull(dataAccess.updateRecipe(recipe1));
        dataAccess.getRecipeSequential(recipes);
        assertEquals(1, recipes.size());

        recipes.clear();
        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        recipes = dataAccess.getRecipeRandom(recipe1);
        assertEquals(1, recipes.size());
        assertEquals(recipe1, recipes.get(0));

        recipes.clear();
        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        dataAccess.getRecipeSequential(recipes);
        assertEquals(1, recipes.size());
        assertNull(dataAccess.deleteRecipe(recipe1));
        dataAccess.getRecipeSequential(recipes2);
        assertEquals(0, recipes2.size());
    }

    @Test
    public void testSizeTwoDB()
    {
        List<Recipe> recipes = new ArrayList<>();
        List<Recipe> recipes2 = new ArrayList<>();
        Recipe recipe1 = new Recipe("Potato Salad");
        Recipe recipe2 = new Recipe("Salad");

        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        dataAccess.getRecipeSequential(recipes);
        assertEquals(1, recipes.size());
        dataAccess.insertRecipe(recipe2);
        dataAccess.getRecipeSequential(recipes2);
        assertEquals(2, recipes2.size());

        recipes.clear();
        recipes2.clear();
        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        dataAccess.insertRecipe(recipe2);
        recipe1.setName("My potato salad");
        recipe2.setName("My favorite salad");
        assertNull(dataAccess.updateRecipe(recipe1));
        assertNull(dataAccess.updateRecipe(recipe2));
        dataAccess.getRecipeSequential(recipes);
        assertEquals(2, recipes.size());
        assertEquals("My potato salad", recipes.get(0).getName());
        assertEquals("My favorite salad", recipes.get(1).getName());

        recipes.clear();
        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        dataAccess.insertRecipe(recipe2);
        dataAccess.getRecipeSequential(recipes);
        assertEquals(2, recipes.size());

        recipes.clear();
        recipes = dataAccess.getRecipeRandom(recipe1);
        assertEquals(recipe1, recipes.get(0));

        recipes.clear();
        recipes = dataAccess.getRecipeRandom(recipe2);
        assertEquals(recipe2, recipes.get(0));

        recipes.clear();
        deleteEverything();
        dataAccess.insertRecipe(recipe1);
        dataAccess.insertRecipe(recipe2);

        dataAccess.getRecipeSequential(recipes);
        assertEquals(2, recipes.size());

        recipes.clear();
        assertNull(dataAccess.deleteRecipe(recipe1));
        dataAccess.getRecipeSequential(recipes);
        assertEquals(1, recipes.size());

        recipes.clear();
        assertNull(dataAccess.deleteRecipe(recipe2));
        dataAccess.getRecipeSequential(recipes);
        assertEquals(0, recipes.size());
    }

    private DataAccess createAccess()
    {
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

    private void deleteEverything()
    {
        List<Recipe> currRecipes = new ArrayList<>();
        List<Recipe> postDeleteRecipes = new ArrayList<>();
        dataAccess.getRecipeSequential(currRecipes);
        assertTrue(currRecipes.size() >= 0);

        // delete everything in db
        for (int i = 0; i < currRecipes.size(); i++) {
            assertNull(dataAccess.deleteRecipe(currRecipes.get(i)));
        }

        dataAccess.getRecipeSequential(postDeleteRecipes);
        assertEquals(0, postDeleteRecipes.size());
    }

}
