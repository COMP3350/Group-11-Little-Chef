package comp3350.littlechef.business;

import static comp3350.littlechef.business.FractionDecimalConversion.convertDecimalToFraction;
import static comp3350.littlechef.business.ScaleRecipe.scaleIngredients;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;

public class ScaleRecipeTest extends TestCase
{

    @Test
    public void testScaleRecipeSimple()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "g", 0.25));
        recipe.addIngredient(new Ingredient("ingr1", "tsp", 0.5));
        recipe.addIngredient(new Ingredient("ingr2", "tsp", 1));
        recipe.addIngredient(new Ingredient("ingr3", "quantity", 2));
        recipe.addIngredient(new Ingredient("ingr4", "cup", 100));

        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 1);
        assertEquals(0.25,ingredients.get(0).getAmount());
        assertEquals(0.5,ingredients.get(1).getAmount());
        assertEquals(1.0,ingredients.get(2).getAmount());
        assertEquals(2.0,ingredients.get(3).getAmount());
        assertEquals(100.0,ingredients.get(4).getAmount());
        assertEquals("g",ingredients.get(0).getMeasurement());
        assertEquals("tsp",ingredients.get(1).getMeasurement());
        assertEquals("tsp",ingredients.get(2).getMeasurement());
        assertEquals("quantity",ingredients.get(3).getMeasurement());
        assertEquals("cup",ingredients.get(4).getMeasurement());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, 2);
        assertEquals(0.5,ingredients2.get(0).getAmount());
        assertEquals(1.0,ingredients2.get(1).getAmount());
        assertEquals(2.0,ingredients2.get(2).getAmount());
        assertEquals(4.0,ingredients2.get(3).getAmount());
        assertEquals(200.0,ingredients2.get(4).getAmount());

        assertEquals("g",ingredients.get(0).getMeasurement());
        assertEquals("tsp",ingredients.get(1).getMeasurement());
        assertEquals("tsp",ingredients.get(2).getMeasurement());
        assertEquals("quantity",ingredients.get(3).getMeasurement());
        assertEquals("cup",ingredients.get(4).getMeasurement());
    }

    @Test
    public void testScaleRecipeZeroServingSize()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 2));
        recipe.addIngredient(new Ingredient("ingr1", "cup", 0.5));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 0);
        assertEquals(2.0,ingredients.get(0).getAmount());
        assertEquals(0.5,ingredients.get(1).getAmount());
        assertEquals("pinch",ingredients.get(0).getMeasurement());
        assertEquals("cup",ingredients.get(1).getMeasurement());

    }

    @Test
    public void testScaleRecipeNegativeServingSize()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 2));
        recipe.addIngredient(new Ingredient("ingr1", "cup", 0.5));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, -1);
        assertEquals(2.0,ingredients.get(0).getAmount());
        assertEquals(0.5,ingredients.get(1).getAmount());
        assertEquals("pinch",ingredients.get(0).getMeasurement());
        assertEquals("cup",ingredients.get(1).getMeasurement());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, -5);
        assertEquals(2.0,ingredients2.get(0).getAmount());
        assertEquals(0.5,ingredients2.get(1).getAmount());
        assertEquals("pinch",ingredients2.get(0).getMeasurement());
        assertEquals("cup",ingredients2.get(1).getMeasurement());

    }

    @Test
    public void testScaleRecipeDifferentServingSizes()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 4));
        recipe.addIngredient(new Ingredient("ingr1", "cup", 0.5));
        recipe.addIngredient(new Ingredient("ingr1", "tsp", 3));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 1);
        assertEquals(4.0,ingredients.get(0).getAmount());
        assertEquals(0.5,ingredients.get(1).getAmount());
        assertEquals(3.0,ingredients.get(2).getAmount());
        assertEquals("pinch",ingredients.get(0).getMeasurement());
        assertEquals("cup",ingredients.get(1).getMeasurement());
        assertEquals("tsp",ingredients.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, 2);
        assertEquals(8.0,ingredients2.get(0).getAmount());
        assertEquals(1.0,ingredients2.get(1).getAmount());
        assertEquals(2.0,ingredients2.get(2).getAmount());
        assertEquals("pinch",ingredients2.get(0).getMeasurement());
        assertEquals("cup",ingredients2.get(1).getMeasurement());
        assertEquals("tbsp",ingredients2.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients3 = scaleIngredients(recipe, 3);
        assertEquals(12.0,ingredients3.get(0).getAmount());
        assertEquals(1.5,ingredients3.get(1).getAmount());
        assertEquals(3.0,ingredients3.get(2).getAmount());
        assertEquals("pinch",ingredients3.get(0).getMeasurement());
        assertEquals("cup",ingredients3.get(1).getMeasurement());
        assertEquals("tbsp",ingredients3.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients4 = scaleIngredients(recipe, 8);
        assertEquals(2.0,ingredients4.get(0).getAmount());
        assertEquals(4.0,ingredients4.get(1).getAmount());
        assertEquals(0.5,ingredients4.get(2).getAmount());
        assertEquals("tsp",ingredients4.get(0).getMeasurement());
        assertEquals("cup",ingredients4.get(1).getMeasurement());
        assertEquals("cup",ingredients4.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients5 = scaleIngredients(recipe, 400);
        assertEquals(2.0,ingredients5.get(0).getAmount());
        assertEquals(200.0,ingredients5.get(1).getAmount());
        assertEquals(25.0,ingredients5.get(2).getAmount());
        assertEquals("cup",ingredients5.get(0).getMeasurement());
        assertEquals("cup",ingredients5.get(1).getMeasurement());
        assertEquals("cup",ingredients5.get(2).getMeasurement());

    }

    @Test
    public void testScaleRecipeConvertMeasurements()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 8));
        recipe.addIngredient(new Ingredient("ingr1", "tsp", 1.5));
        recipe.addIngredient(new Ingredient("ingr2", "tbsp", 8));
        recipe.addIngredient(new Ingredient("ingr3", "mg", 500));
        recipe.addIngredient(new Ingredient("ingr4", "g", 500));
        recipe.addIngredient(new Ingredient("ingr5", "mm", 5));
        recipe.addIngredient(new Ingredient("ingr6", "cm", 50));
        recipe.addIngredient(new Ingredient("ingr7", "ml", 500));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(1.0,ingredients.get(0).getAmount());
        assertEquals(1.0,ingredients.get(1).getAmount());
        assertEquals(1.0,ingredients.get(2).getAmount());
        assertEquals(1.0,ingredients.get(3).getAmount());
        assertEquals(1.0,ingredients.get(4).getAmount());
        assertEquals(1.0,ingredients.get(5).getAmount());
        assertEquals(1.0,ingredients.get(6).getAmount());
        assertEquals(1.0,ingredients.get(7).getAmount());

        assertEquals("tsp",ingredients.get(0).getMeasurement());
        assertEquals("tbsp",ingredients.get(1).getMeasurement());
        assertEquals("cup",ingredients.get(2).getMeasurement());
        assertEquals("g",ingredients.get(3).getMeasurement());
        assertEquals("kg",ingredients.get(4).getMeasurement());
        assertEquals("cm",ingredients.get(5).getMeasurement());
        assertEquals("m",ingredients.get(6).getMeasurement());
        assertEquals("l",ingredients.get(7).getMeasurement());
    }

    @Test
    public void testScaleRecipeOnlyConvertWithMultipleServings()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 16));
        recipe.addIngredient(new Ingredient("ingr1", "tsp", 3));
        recipe.addIngredient(new Ingredient("ingr2", "tsp", 4.5));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 1);
        assertEquals(16.0,ingredients.get(0).getAmount());
        assertEquals(3.0,ingredients.get(1).getAmount());
        assertEquals(4.5,ingredients.get(2).getAmount());

        assertEquals("pinch",ingredients.get(0).getMeasurement());
        assertEquals("tsp",ingredients.get(1).getMeasurement());
        assertEquals("tsp",ingredients.get(2).getMeasurement());

        ArrayList<Ingredient> ingredients2 = scaleIngredients(recipe, 2);
        assertEquals(2.0,ingredients2.get(0).getAmount());
        assertEquals(2.0,ingredients2.get(1).getAmount());
        assertEquals(3.0,ingredients2.get(2).getAmount());

        assertEquals("tsp",ingredients2.get(0).getMeasurement());
        assertEquals("tbsp",ingredients2.get(1).getMeasurement());
        assertEquals("tbsp",ingredients2.get(2).getMeasurement());
    }

    @Test
    public void testScaleRecipeSkippingMeasurements()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 768));
        recipe.addIngredient(new Ingredient("ingr1", "mm", 1000));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(2.0,ingredients.get(0).getAmount());
        assertEquals(2.0,ingredients.get(1).getAmount());

        assertEquals("cup",ingredients.get(0).getMeasurement());
        assertEquals("m",ingredients.get(1).getMeasurement());

    }

    @Test
    public void testScaleRecipeManyDecimalRounding()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 12.1234));
        recipe.addIngredient(new Ingredient("ingr1", "mm", 5.0000001));
        recipe.addIngredient(new Ingredient("ingr2", "tsp", 3.000003));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(1.5,ingredients.get(0).getAmount());
        assertEquals(1.0,ingredients.get(1).getAmount());
        assertEquals(2.0,ingredients.get(2).getAmount());

        assertEquals("tsp",ingredients.get(0).getMeasurement());
        assertEquals("cm",ingredients.get(1).getMeasurement());
        assertEquals("tbsp",ingredients.get(2).getMeasurement());

    }

    @Test
    public void testScaleRecipeConversionSlightlyUnderThreshold()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 7.99));
        recipe.addIngredient(new Ingredient("ingr1", "tsp", 1.49));
        recipe.addIngredient(new Ingredient("ingr2", "tbsp", 1.99));
        recipe.addIngredient(new Ingredient("ingr3", "mg", 499));
        recipe.addIngredient(new Ingredient("ingr4", "g", 499.9999));
        recipe.addIngredient(new Ingredient("ingr5", "mm", 4.9));
        recipe.addIngredient(new Ingredient("ingr6", "cm", 49.9));
        recipe.addIngredient(new Ingredient("ingr7", "ml", 249));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(16.0,ingredients.get(0).getAmount());
        assertEquals(3.0,ingredients.get(1).getAmount());
        assertEquals(4.0,ingredients.get(2).getAmount());
        assertEquals(998.0,ingredients.get(3).getAmount());
        assertEquals(1000.0,ingredients.get(4).getAmount());
        assertEquals(9.75,ingredients.get(5).getAmount());
        assertEquals(99.75,ingredients.get(6).getAmount());
        assertEquals(498.0,ingredients.get(7).getAmount());

        assertEquals("pinch",ingredients.get(0).getMeasurement());
        assertEquals("tsp",ingredients.get(1).getMeasurement());
        assertEquals("tbsp",ingredients.get(2).getMeasurement());
        assertEquals("mg",ingredients.get(3).getMeasurement());
        assertEquals("g",ingredients.get(4).getMeasurement());
        assertEquals("mm",ingredients.get(5).getMeasurement());
        assertEquals("cm",ingredients.get(6).getMeasurement());
        assertEquals("ml",ingredients.get(7).getMeasurement());

    }

    @Test
    public void testScaleRecipeConversionSlightlyOverThreshold()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 8.01));
        recipe.addIngredient(new Ingredient("ingr1", "tsp", 1.51));
        recipe.addIngredient(new Ingredient("ingr2", "tbsp", 2.01));
        recipe.addIngredient(new Ingredient("ingr3", "mg", 501));
        recipe.addIngredient(new Ingredient("ingr4", "g", 500.0001));
        recipe.addIngredient(new Ingredient("ingr5", "mm", 5.1));
        recipe.addIngredient(new Ingredient("ingr6", "cm", 50.1));
        recipe.addIngredient(new Ingredient("ingr7", "ml", 251));


        ArrayList<Ingredient> ingredients = scaleIngredients(recipe, 2);
        assertEquals(1.0,ingredients.get(0).getAmount());
        assertEquals(1.0,ingredients.get(1).getAmount());
        assertEquals(0.25,ingredients.get(2).getAmount());
        assertEquals(1.0,ingredients.get(3).getAmount());
        assertEquals(1.0,ingredients.get(4).getAmount());
        assertEquals(1.0,ingredients.get(5).getAmount());
        assertEquals(1.0,ingredients.get(6).getAmount());
        assertEquals(0.5,ingredients.get(7).getAmount());

        assertEquals("tsp",ingredients.get(0).getMeasurement());
        assertEquals("tbsp",ingredients.get(1).getMeasurement());
        assertEquals("cup",ingredients.get(2).getMeasurement());
        assertEquals("g",ingredients.get(3).getMeasurement());
        assertEquals("kg",ingredients.get(4).getMeasurement());
        assertEquals("cm",ingredients.get(5).getMeasurement());
        assertEquals("m",ingredients.get(6).getMeasurement());
        assertEquals("l",ingredients.get(7).getMeasurement());

    }

    @Test
    public void testScaleRecipeZeroAmountDefaultsToOne()
    {
        Recipe recipe = new Recipe(1000);
        recipe.addIngredient(new Ingredient("ingr0", "pinch", 0));
        recipe.addIngredient(new Ingredient("ingr1", "tsp", 0));
        recipe.addIngredient(new Ingredient("ingr2", "tbsp", 0));
        recipe.addIngredient(new Ingredient("ingr3", "mg", 0));
        recipe.addIngredient(new Ingredient("ingr4", "g", 0));
        recipe.addIngredient(new Ingredient("ingr5", "mm", 0));
        recipe.addIngredient(new Ingredient("ingr6", "cm", 0));
        recipe.addIngredient(new Ingredient("ingr7", "ml", 0));

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
        assertEquals("tbsp",ingredients2.get(1).getMeasurement());
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
