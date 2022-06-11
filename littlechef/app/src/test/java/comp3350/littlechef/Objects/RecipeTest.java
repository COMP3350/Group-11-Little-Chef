package comp3350.littlechef.Objects;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;

public class RecipeTest extends TestCase {
    public RecipeTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testSimple() {
        //test the file could run or not
        assertEquals(1, 1);
    }

    @Test
    public void testRecipeCreation() {
        //constructor1
        Recipe recipe1 = new Recipe(00);
        assertNotNull(recipe1);

        Recipe recipe2 = new Recipe(00);

        ArrayList<Recipe> inList = new ArrayList<>();
        inList.add(recipe1);
        inList.add(recipe2);

        assertEquals(inList.size(), 2);
    }

    @Test
    public void testRecipeCreation2() {
        //constructor2
        Recipe recipe1 = new Recipe("a1", 111, 123);
        assertNotNull(recipe1);

        Recipe recipe2 = new Recipe("b1", 666, 777);

        ArrayList<Recipe> inList = new ArrayList<>();
        inList.add(recipe1);
        inList.add(recipe2);

        assertEquals(inList.size(), 2);
    }

    @Test
    public void testRecipeGetName() {
        Recipe recipe1 = new Recipe("Paste", 60, 70);
        assertNotNull(recipe1);

        assertEquals("Paste", recipe1.getName());
    }

    @Test
    public void testIngredientSetName(){
        Recipe recipe1 = new Recipe("juice", 100, 200);
        recipe1.setName("aa");
        assertEquals("aa", recipe1.getName());
    }


}



