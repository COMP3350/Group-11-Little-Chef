package comp3350.littlechef.objects;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

public class RecipeTest extends TestCase
{

    public RecipeTest(String arg0)
    {
        super(arg0);
    }//end RecipeTest

    @Test
    public void testTypicalCases()
    {
        //add recipe ID number
        Recipe recipeID1 = new Recipe(0);
        Recipe recipeID2 = new Recipe(1);

        ArrayList<Recipe> inList = new ArrayList<>();
        inList.add(recipeID1);
        inList.add(recipeID2);
        assertEquals(2, inList.size());

        //add recipe name
        Recipe recipeName1 = new Recipe("a1");
        Recipe recipeName2 = new Recipe("b1");

        inList.add(recipeName1);
        inList.add(recipeName2);
        assertEquals(4, inList.size());

        Recipe recipe = new Recipe("recipe");
        assertEquals(0.0, recipe.getRating());
        assertEquals("-/5", recipe.getRatingString());
        recipe.addDifficultyRating(5);
        recipe.addTasteRating(5);
        assertEquals(5.0, recipe.getRating());
        assertEquals("5.00/5", recipe.getRatingString());

    }

    @Test
    public void testRecipeName()
    {
        Recipe recipe = new Recipe("Paste");
        assertEquals("Paste", recipe.getName());
        assertEquals("Paste, -/5, Taste: -, Difficulty: -", recipe.toString());

        recipe.setName("123");
        assertEquals("123", recipe.getName());
        assertEquals("123, -/5, Taste: -, Difficulty: -", recipe.toString());
        recipe.setName("%paste1^&");
        assertEquals("%paste1^&", recipe.getName());
        assertEquals("%paste1^&, -/5, Taste: -, Difficulty: -", recipe.toString());

        Recipe recipeSetNewName = new Recipe("juice");
        recipeSetNewName.setName("aa");
        assertEquals("aa", recipeSetNewName.getName());
        assertEquals("aa, -/5, Taste: -, Difficulty: -", recipeSetNewName.toString());

        recipeSetNewName.setName("%paste1");
        assertEquals("%paste1", recipeSetNewName.getName());
        assertEquals("%paste1, -/5, Taste: -, Difficulty: -", recipeSetNewName.toString());
    }//end testRecipeName

    @Test
    public void testRecipeNameNull()
    {
        try
        {
            Recipe recipe = new Recipe(null);
        }
        catch (NullPointerException unused)
        {
            //catch error in create recipe name
        }
    }//end testRecipeNameNull

    @Test
    public void testRecipeIngredients()
    {
        Recipe recipe = new Recipe("aaa");

        Ingredient ingredient1 = new Ingredient("Noodle", null, 0.1);
        Ingredient ingredient2 = new Ingredient("Rice", null, 0.2);
        Ingredient ingredient3 = new Ingredient("Cake",null, 0.3);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        assertEquals(3, recipe.getIngredients().size());

        ingredient1.setName("");
        assertEquals("" , ingredient1.getName());

        ingredient1.setMeasurement(Unit.MM);
        assertEquals(Unit.MM , ingredient1.getMeasurement());

        ingredient1.setMeasurement(null);
        assertEquals(Unit.MM , ingredient1.getMeasurement());

        ingredient1.setAmount(0.0);
        assertEquals(0.1 , ingredient1.getAmount());

        ingredient1.setAmount(-0.1);
        assertEquals(0.1 , ingredient1.getAmount());
    }//testRecipeIngredients

    @Test
    public void testAddIngredientNull()
    {
        try
        {
            Recipe recipe = new Recipe(1);
            Ingredient ingredient1 = new Ingredient("Noodle", null, 0.1);

            recipe.addIngredient(ingredient1);
            ingredient1.setName(null);
        }
        catch (NullPointerException unused)
        {
            //catch error in add ingredient
        }
    }//end testAddIngredientNull

    @Test
    public void testLeadingAndTrailingSpacesInArgument()
    {
        Recipe recipe = new Recipe(" a recipe ");
        assertEquals("a recipe", recipe.getName());

        recipe.setName(" new name ");
        assertEquals("new name", recipe.getName());
    }//end testLeadingAndTrailingSpacesInArgument

    @Test
    public void testSymbolsInArgument()
    {
        Recipe recipe1 = new Recipe("a recipe!!!");
        assertEquals("a recipe!!!", recipe1.getName());

        Recipe recipe2 = new Recipe("~~** a recipe **~~");
        assertEquals("~~** a recipe **~~", recipe2.getName());
    }//end testSymbolsInArgument

    @Test
    public void testRecipeInstruction() {
        Recipe recipe = new Recipe("salad");
        assertEquals(true, recipe.getInstructions().isEmpty()); //EmptyList

        recipe.addInstructions("add vegetable", "add salt");
        assertEquals(1, recipe.getInstructions().size());

        //add same instructions at once will be an invalid input
        recipe.addInstructions("add sugar", "add sugar");
        assertEquals(1, recipe.getInstructions().size());
    }//end testRecipeInstruction

    @Test
    public void testRecipeInstructionNull()
    {
        try
        {
            Recipe recipe = new Recipe("salad");
            recipe.addInstructions(null,null);
        }
        catch (NullPointerException unused)
        {
            //catch error in create instruction
        }
    }//end testRecipeInstructionNull

    @Test
    public void testAverageCookingTime()
    {
        //when there is no cooking time added
        Recipe recipe = new Recipe("smoothie");
        //testEmptyList
        assertEquals("Time: Not cooked", recipe.getAverageCookingTime());

        recipe.addCookingTime(10);
        assertEquals("Time: 00h 00m 10s", recipe.getAverageCookingTime());
        recipe.addCookingTime(100);
        assertEquals("Time: 00h 00m 55s", recipe.getAverageCookingTime());
        recipe.addCookingTime(-10);
        assertEquals("Time: 00h 00m 55s", recipe.getAverageCookingTime());
        recipe.addCookingTime(0);
        assertEquals("Time: 00h 00m 36s", recipe.getAverageCookingTime());
    }//end testAverageCookingTime

    @Test
    public void testDifficultyRating() {
        Recipe recipe = new Recipe("fish and chips");
        //testEmptyList
        assertEquals("Difficulty: -", recipe.getDifficultyRating());

        recipe.addDifficultyRating(1.0);
        assertEquals("Difficulty: 1.00", recipe.getDifficultyRating());
        recipe.addDifficultyRating(6.0);
        assertEquals("Difficulty: 3.50", recipe.getDifficultyRating());
        recipe.addDifficultyRating(-1.0);
        assertEquals("Difficulty: 3.50", recipe.getDifficultyRating());
        recipe.addDifficultyRating(0);
        assertEquals("Difficulty: 2.33", recipe.getDifficultyRating());
    }//end testDifficultyRating

    @Test
    public void testTasteRating() {
        Recipe recipe = new Recipe("fish taco");
        //testEmptyList
        assertEquals("Taste: -", recipe.getTasteRating());

        recipe.addTasteRating(1.0);
        assertEquals("Taste: 1.00", recipe.getTasteRating());
        recipe.addTasteRating(6.0);
        assertEquals("Taste: 3.50", recipe.getTasteRating());
        recipe.addTasteRating(-1.0);
        assertEquals("Taste: 3.50", recipe.getTasteRating());
        recipe.addTasteRating(0);
        assertEquals("Taste: 2.33", recipe.getTasteRating());
    }//end testTasteRating

    @Test
    public void testRating()
    {
        Recipe recipe = new Recipe("fish taco");
        //testEmptyList
        assertEquals(0.0, recipe.getRating());

        recipe.addTasteRating(1.0);
        recipe.addDifficultyRating(3.0);
        assertEquals(2.0, recipe.getRating());

        recipe.addTasteRating(0.2);
        recipe.addDifficultyRating(0.4);
        assertEquals(1.15, recipe.getRating());

        //reserve two decimal places
        recipe.addTasteRating(1.222);
        recipe.addDifficultyRating(3.444);
        assertEquals(1.54, recipe.getRating());
    }//end testRating

    @Test
    public void testObjectEquals()
    {
        Recipe recipe = new Recipe("beef noodle");
        Recipe recipeNew = new Recipe("chicken soup");
        Recipe recipeSameID = new Recipe(recipe.getId());

        //boolean equals
        assertTrue(recipe.equals(recipeSameID));
        assertTrue(!recipe.equals(recipeNew));
    }
    @Test
    public void testNullArgument()
    {
        Recipe recipe1;
        Recipe recipe2 = new Recipe("recipe");

        try
        {
            recipe1 = new Recipe(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

        try
        {
            recipe2.setName(null);
            fail("Wanted an exception for null input.");
        }
        catch (NullPointerException e)
        {
            // this is expected
        }

    }

    @Test
    public void testEmptyArgument()
    {
        Recipe recipe1;
        Recipe recipe2 = new Recipe("recipe");
        try
        {
            recipe1 = new Recipe("");
            fail("Wanted an illegal argument exception for empty input.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

        try
        {
            recipe1 = new Recipe(" ");
            fail("Wanted an illegal argument exception for empty input.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

        try
        {
            recipe1 = new Recipe("       ");
            fail("Wanted an illegal argument exception for empty input.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

        try
        {
            recipe2.setName("");
            fail("Wanted an exception for illegal input.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

        try
        {
            recipe2.setName(" ");
            fail("Wanted an exception for illegal input.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }

        try
        {
            recipe2.setName("   ");
            fail("Wanted an exception for illegal input.");
        }
        catch (IllegalArgumentException e)
        {
            // this is expected
        }
    }

}