package comp3350.littlechef.objects;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.persistence.PersistenceAccessDB;

public class UpdatedRecipeTest extends TestCase
{
    @Test
    public void testRecipeSimple()
    {
        String someRecipe = "something";

        Recipe recipe1 = new Recipe(someRecipe);
        assertEquals(someRecipe, recipe1.getName());

        Recipe recipe2 = new Recipe("food");
        assertEquals("food", recipe2.getName());

        Recipe recipe3 = new Recipe(123);
        assertEquals(123, recipe3.getId());
        assertNull(recipe3.getName());

        recipe3.setName("New Recipe");
        assertEquals("New Recipe", recipe3.getName());
    }

    @Test
    public void testGettingUninitializedRecipe()
    {

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

    @Test
    public void testLeadingSpacesInArgument()
    {
        Recipe recipe1 = new Recipe(" a recipe");
        assertEquals("a recipe", recipe1.getName());

        Recipe recipe2 = new Recipe("    a recipe");
        assertEquals("a recipe", recipe2.getName());

        recipe1.setName(" new name");
        assertEquals("new name", recipe1.getName());

        recipe2.setName("  a new name");
        assertEquals("a new name", recipe2.getName());
    }

    @Test
    public void testTrailingSpacesInArgument()
    {
        Recipe recipe1 = new Recipe("a recipe ");
        assertEquals("a recipe", recipe1.getName());

        Recipe recipe2 = new Recipe("a recipe     ");
        assertEquals("a recipe", recipe2.getName());

        recipe1.setName("new name ");
        assertEquals("new name", recipe1.getName());

        recipe2.setName("a new name  ");
        assertEquals("a new name", recipe2.getName());
    }

    @Test
    public void testLeadingAndTrailingSpacesInArgument()
    {
        Recipe recipe1 = new Recipe(" a recipe ");
        assertEquals("a recipe", recipe1.getName());

        Recipe recipe2 = new Recipe("       a recipe     ");
        assertEquals("a recipe", recipe2.getName());

        recipe1.setName(" new name ");
        assertEquals("new name", recipe1.getName());

        recipe2.setName("  a new name  ");
        assertEquals("a new name", recipe2.getName());
    }

    @Test
    public void testSymbolsInArgument()
    {
        Recipe recipe1 = new Recipe("a recipe!!!");
        assertEquals("a recipe!!!", recipe1.getName());

        Recipe recipe2 = new Recipe("~~** a recipe **~~");
        assertEquals("~~** a recipe **~~", recipe2.getName());
    }

    @Test
    public void testNumbersAsStringsInArguments()
    {
        Recipe recipe1 = new Recipe("pizza for 1");
        assertEquals("pizza for 1", recipe1.getName());

        Recipe recipe2 = new Recipe("675");
        assertEquals("675", recipe2.getName());
    }

    @Test
    public void testDuplicateRecipes()
    {

    }

}
