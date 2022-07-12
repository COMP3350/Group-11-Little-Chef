package comp3350.littlechef.business;

import static comp3350.littlechef.business.ScaleRecipe.scaleIngredients;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

public class ScaleRecipeTest extends TestCase
{

    public ScaleRecipeTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testTypicalCases()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.G, 0.25));
        recipe.addIngredient(new Ingredient("ingr1", Unit.TSP, 0.5));
        recipe.addIngredient(new Ingredient("ingr2", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient("ingr3", Unit.QUANTITY, 2));
        recipe.addIngredient(new Ingredient("ingr4", Unit.CUP, 100));

        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 1);
        assertEquals(0.25,ingredients.get(0).getAmount());
        assertEquals(0.5,ingredients.get(1).getAmount());
        assertEquals(1.0,ingredients.get(2).getAmount());
        assertEquals(2.0,ingredients.get(3).getAmount());
        assertEquals(100.0,ingredients.get(4).getAmount());
        assertEquals(Unit.G,ingredients.get(0).getMeasurement());
        assertEquals(Unit.TSP,ingredients.get(1).getMeasurement());
        assertEquals(Unit.TSP,ingredients.get(2).getMeasurement());
        assertEquals(Unit.QUANTITY,ingredients.get(3).getMeasurement());
        assertEquals(Unit.CUP,ingredients.get(4).getMeasurement());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, 2);
        assertEquals(0.5,ingredients2.get(0).getAmount());
        assertEquals(1.0,ingredients2.get(1).getAmount());
        assertEquals(2.0,ingredients2.get(2).getAmount());
        assertEquals(4.0,ingredients2.get(3).getAmount());
        assertEquals(200.0,ingredients2.get(4).getAmount());

        assertEquals(Unit.G,ingredients.get(0).getMeasurement());
        assertEquals(Unit.TSP,ingredients.get(1).getMeasurement());
        assertEquals(Unit.TSP,ingredients.get(2).getMeasurement());
        assertEquals(Unit.QUANTITY,ingredients.get(3).getMeasurement());
        assertEquals(Unit.CUP,ingredients.get(4).getMeasurement());
    }

    @Test
    public void testScaleRecipeZeroServingSize()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 2));
        recipe.addIngredient(new Ingredient("ingr1", Unit.CUP, 0.5));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 0);
        assertEquals(2.0,ingredients.get(0).getAmount());
        assertEquals(0.5,ingredients.get(1).getAmount());
        assertEquals(Unit.PINCH,ingredients.get(0).getMeasurement());
        assertEquals(Unit.CUP,ingredients.get(1).getMeasurement());

    }

    @Test
    public void testScaleRecipeNegativeServingSize()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 2));
        recipe.addIngredient(new Ingredient("ingr1", Unit.CUP, 0.5));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, -1);
        assertEquals(2.0,ingredients.get(0).getAmount());
        assertEquals(0.5,ingredients.get(1).getAmount());
        assertEquals(Unit.PINCH,ingredients.get(0).getMeasurement());
        assertEquals(Unit.CUP,ingredients.get(1).getMeasurement());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, -5);
        assertEquals(2.0,ingredients2.get(0).getAmount());
        assertEquals(0.5,ingredients2.get(1).getAmount());
        assertEquals(Unit.PINCH,ingredients2.get(0).getMeasurement());
        assertEquals(Unit.CUP,ingredients2.get(1).getMeasurement());

    }

    @Test
    public void testScaleRecipeDifferentServingSizes()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 4));
        recipe.addIngredient(new Ingredient("ingr1", Unit.CUP, 0.5));
        recipe.addIngredient(new Ingredient("ingr1", Unit.TSP, 3));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 1);
        assertEquals(4.0,ingredients.get(0).getAmount());
        assertEquals(0.5,ingredients.get(1).getAmount());
        assertEquals(3.0,ingredients.get(2).getAmount());
        assertEquals(Unit.PINCH,ingredients.get(0).getMeasurement());
        assertEquals(Unit.CUP,ingredients.get(1).getMeasurement());
        assertEquals(Unit.TSP,ingredients.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, 2);
        assertEquals(8.0,ingredients2.get(0).getAmount());
        assertEquals(1.0,ingredients2.get(1).getAmount());
        assertEquals(2.0,ingredients2.get(2).getAmount());
        assertEquals(Unit.PINCH,ingredients2.get(0).getMeasurement());
        assertEquals(Unit.CUP,ingredients2.get(1).getMeasurement());
        assertEquals(Unit.TBSP,ingredients2.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients3 = scaleIngredients(recipe, 3);
        assertEquals(12.0,ingredients3.get(0).getAmount());
        assertEquals(1.5,ingredients3.get(1).getAmount());
        assertEquals(3.0,ingredients3.get(2).getAmount());
        assertEquals(Unit.PINCH,ingredients3.get(0).getMeasurement());
        assertEquals(Unit.CUP,ingredients3.get(1).getMeasurement());
        assertEquals(Unit.TBSP,ingredients3.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients4 = scaleIngredients(recipe, 8);
        assertEquals(2.0,ingredients4.get(0).getAmount());
        assertEquals(4.0,ingredients4.get(1).getAmount());
        assertEquals(0.5,ingredients4.get(2).getAmount());
        assertEquals(Unit.TSP,ingredients4.get(0).getMeasurement());
        assertEquals(Unit.CUP,ingredients4.get(1).getMeasurement());
        assertEquals(Unit.CUP,ingredients4.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients5 = scaleIngredients(recipe, 400);
        assertEquals(2.0,ingredients5.get(0).getAmount());
        assertEquals(200.0,ingredients5.get(1).getAmount());
        assertEquals(25.0,ingredients5.get(2).getAmount());
        assertEquals(Unit.CUP,ingredients5.get(0).getMeasurement());
        assertEquals(Unit.CUP,ingredients5.get(1).getMeasurement());
        assertEquals(Unit.CUP,ingredients5.get(2).getMeasurement());

    }

    @Test
    public void testScaleRecipeConvertMeasurements()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 8));
        recipe.addIngredient(new Ingredient("ingr1", Unit.TSP, 1.5));
        recipe.addIngredient(new Ingredient("ingr2", Unit.TBSP, 8));
        recipe.addIngredient(new Ingredient("ingr3", Unit.MG, 500));
        recipe.addIngredient(new Ingredient("ingr4", Unit.G, 500));
        recipe.addIngredient(new Ingredient("ingr5", Unit.MM, 5));
        recipe.addIngredient(new Ingredient("ingr6", Unit.CM, 50));
        recipe.addIngredient(new Ingredient("ingr7", Unit.ML, 500));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(1.0,ingredients.get(0).getAmount());
        assertEquals(1.0,ingredients.get(1).getAmount());
        assertEquals(1.0,ingredients.get(2).getAmount());
        assertEquals(1.0,ingredients.get(3).getAmount());
        assertEquals(1.0,ingredients.get(4).getAmount());
        assertEquals(1.0,ingredients.get(5).getAmount());
        assertEquals(1.0,ingredients.get(6).getAmount());
        assertEquals(1.0,ingredients.get(7).getAmount());

        assertEquals(Unit.TSP,ingredients.get(0).getMeasurement());
        assertEquals(Unit.TBSP,ingredients.get(1).getMeasurement());
        assertEquals(Unit.CUP,ingredients.get(2).getMeasurement());
        assertEquals(Unit.G,ingredients.get(3).getMeasurement());
        assertEquals("kg",ingredients.get(4).getMeasurement());
        assertEquals(Unit.CM,ingredients.get(5).getMeasurement());
        assertEquals("m",ingredients.get(6).getMeasurement());
        assertEquals("l",ingredients.get(7).getMeasurement());
    }

    @Test
    public void testScaleRecipeOnlyConvertWithMultipleServings()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 16));
        recipe.addIngredient(new Ingredient("ingr1", Unit.TSP, 3));
        recipe.addIngredient(new Ingredient("ingr2", Unit.TSP, 4.5));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 1);
        assertEquals(16.0,ingredients.get(0).getAmount());
        assertEquals(3.0,ingredients.get(1).getAmount());
        assertEquals(4.5,ingredients.get(2).getAmount());

        assertEquals(Unit.PINCH,ingredients.get(0).getMeasurement());
        assertEquals(Unit.TSP,ingredients.get(1).getMeasurement());
        assertEquals(Unit.TSP,ingredients.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, 2);
        assertEquals(2.0,ingredients2.get(0).getAmount());
        assertEquals(2.0,ingredients2.get(1).getAmount());
        assertEquals(3.0,ingredients2.get(2).getAmount());

        assertEquals(Unit.TSP,ingredients2.get(0).getMeasurement());
        assertEquals(Unit.TBSP,ingredients2.get(1).getMeasurement());
        assertEquals(Unit.TBSP,ingredients2.get(2).getMeasurement());
    }

    @Test
    public void testScaleRecipeSkippingMeasurements()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 768));
        recipe.addIngredient(new Ingredient("ingr1", Unit.MM, 1000));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(2.0,ingredients.get(0).getAmount());
        assertEquals(2.0,ingredients.get(1).getAmount());

        assertEquals(Unit.CUP,ingredients.get(0).getMeasurement());
        assertEquals("m",ingredients.get(1).getMeasurement());

    }

    @Test
    public void testScaleRecipeManyDecimalRounding()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 12.1234));
        recipe.addIngredient(new Ingredient("ingr1", Unit.MM, 5.0000001));
        recipe.addIngredient(new Ingredient("ingr2", Unit.TSP, 3.000003));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(1.5,ingredients.get(0).getAmount());
        assertEquals(1.0,ingredients.get(1).getAmount());
        assertEquals(2.0,ingredients.get(2).getAmount());

        assertEquals(Unit.TSP,ingredients.get(0).getMeasurement());
        assertEquals(Unit.CM,ingredients.get(1).getMeasurement());
        assertEquals(Unit.TBSP,ingredients.get(2).getMeasurement());

    }

    @Test
    public void testScaleRecipeConversionSlightlyUnderThreshold()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 7.99));
        recipe.addIngredient(new Ingredient("ingr1", Unit.TSP, 1.49));
        recipe.addIngredient(new Ingredient("ingr2", Unit.TBSP, 1.99));
        recipe.addIngredient(new Ingredient("ingr3", Unit.MG, 499));
        recipe.addIngredient(new Ingredient("ingr4", Unit.G, 499.9999));
        recipe.addIngredient(new Ingredient("ingr5", Unit.MM, 4.9));
        recipe.addIngredient(new Ingredient("ingr6", Unit.CM, 49.9));
        recipe.addIngredient(new Ingredient("ingr7", Unit.ML, 249));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(16.0,ingredients.get(0).getAmount());
        assertEquals(3.0,ingredients.get(1).getAmount());
        assertEquals(4.0,ingredients.get(2).getAmount());
        assertEquals(998.0,ingredients.get(3).getAmount());
        assertEquals(1000.0,ingredients.get(4).getAmount());
        assertEquals(9.75,ingredients.get(5).getAmount());
        assertEquals(99.75,ingredients.get(6).getAmount());
        assertEquals(498.0,ingredients.get(7).getAmount());

        assertEquals(Unit.PINCH,ingredients.get(0).getMeasurement());
        assertEquals(Unit.TSP,ingredients.get(1).getMeasurement());
        assertEquals(Unit.TBSP,ingredients.get(2).getMeasurement());
        assertEquals(Unit.MG,ingredients.get(3).getMeasurement());
        assertEquals(Unit.G,ingredients.get(4).getMeasurement());
        assertEquals(Unit.MM,ingredients.get(5).getMeasurement());
        assertEquals(Unit.CM,ingredients.get(6).getMeasurement());
        assertEquals(Unit.ML,ingredients.get(7).getMeasurement());

    }

    @Test
    public void testScaleRecipeConversionSlightlyOverThreshold()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 8.01));
        recipe.addIngredient(new Ingredient("ingr1", Unit.TSP, 1.51));
        recipe.addIngredient(new Ingredient("ingr2", Unit.TBSP, 2.01));
        recipe.addIngredient(new Ingredient("ingr3", Unit.MG, 501));
        recipe.addIngredient(new Ingredient("ingr4", Unit.G, 500.0001));
        recipe.addIngredient(new Ingredient("ingr5", Unit.MM, 5.1));
        recipe.addIngredient(new Ingredient("ingr6", Unit.CM, 50.1));
        recipe.addIngredient(new Ingredient("ingr7", Unit.ML, 251));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(1.0,ingredients.get(0).getAmount());
        assertEquals(1.0,ingredients.get(1).getAmount());
        assertEquals(0.25,ingredients.get(2).getAmount());
        assertEquals(1.0,ingredients.get(3).getAmount());
        assertEquals(1.0,ingredients.get(4).getAmount());
        assertEquals(1.0,ingredients.get(5).getAmount());
        assertEquals(1.0,ingredients.get(6).getAmount());
        assertEquals(0.5,ingredients.get(7).getAmount());

        assertEquals(Unit.TSP,ingredients.get(0).getMeasurement());
        assertEquals(Unit.TBSP,ingredients.get(1).getMeasurement());
        assertEquals(Unit.CUP,ingredients.get(2).getMeasurement());
        assertEquals(Unit.G,ingredients.get(3).getMeasurement());
        assertEquals("kg",ingredients.get(4).getMeasurement());
        assertEquals(Unit.CM,ingredients.get(5).getMeasurement());
        assertEquals("m",ingredients.get(6).getMeasurement());
        assertEquals("l",ingredients.get(7).getMeasurement());

    }

    @Test
    public void testScaleRecipeZeroAmountDefaultsToOne()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", Unit.PINCH, 0));
        recipe.addIngredient(new Ingredient("ingr1", Unit.TSP, 0));
        recipe.addIngredient(new Ingredient("ingr2", Unit.TBSP, 0));
        recipe.addIngredient(new Ingredient("ingr3", Unit.MG, 0));
        recipe.addIngredient(new Ingredient("ingr4", Unit.G, 0));
        recipe.addIngredient(new Ingredient("ingr5", Unit.MM, 0));
        recipe.addIngredient(new Ingredient("ingr6", Unit.CM, 0));
        recipe.addIngredient(new Ingredient("ingr7", Unit.ML, 0));

        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 1);
        assertEquals(1.0,ingredients.get(0).getAmount());
        assertEquals(1.0,ingredients.get(1).getAmount());
        assertEquals(1.0,ingredients.get(2).getAmount());
        assertEquals(1.0,ingredients.get(3).getAmount());
        assertEquals(1.0,ingredients.get(4).getAmount());
        assertEquals(1.0,ingredients.get(5).getAmount());
        assertEquals(1.0,ingredients.get(6).getAmount());
        assertEquals(1.0,ingredients.get(7).getAmount());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, 3);
        assertEquals(3.0,ingredients2.get(0).getAmount());
        assertEquals(1.0,ingredients2.get(1).getAmount());
        assertEquals(Unit.TBSP,ingredients2.get(1).getMeasurement());
        assertEquals(3.0,ingredients2.get(2).getAmount());
        assertEquals(3.0,ingredients2.get(3).getAmount());
        assertEquals(3.0,ingredients2.get(4).getAmount());
        assertEquals(3.0,ingredients2.get(5).getAmount());
        assertEquals(3.0,ingredients2.get(6).getAmount());
        assertEquals(3.0,ingredients2.get(7).getAmount());
    }

    @Test
    public void testScaleRecipeNullIngredients()
    {
        try
        {
            Recipe recipe = new Recipe(1000);
            recipe.addIngredient(new Ingredient(null, null, 0));
            ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        }

        catch (NullPointerException unused)
        {

        }

    }

    @Test
    public void testScaleRecipeNullRecipe()
    {
        try
        {
            ArrayList<Ingredient> ingredients = scaleIngredients(null, 2);
        }

        catch (NullPointerException unused)
        {

        }

    }
}
