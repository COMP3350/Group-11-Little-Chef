package comp3350.littlechef;

import static comp3350.littlechef.business.FractionDecimalConversion.convertDecimalToFraction;
import static comp3350.littlechef.business.ScaleRecipe.scaleIngredients;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.littlechef.objects.Difficulty;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Quality;
import comp3350.littlechef.objects.Recipe;

public class AllTests extends TestCase
{
    @Test
    public void testFractionDecimalConversionSimple()
    {
        assertEquals("1/8", convertDecimalToFraction(0.125));
        assertEquals("3/5", convertDecimalToFraction(0.6));
        assertEquals("1/2", convertDecimalToFraction(0.5));
        assertEquals("9/10", convertDecimalToFraction(0.9));
    }
    @Test
    public void testFractionDecimalConversionContinuousDecimal()
    {
        assertEquals("2/3", convertDecimalToFraction(0.666666));
        assertEquals("2/7", convertDecimalToFraction(0.285714));
        assertEquals("8/9", convertDecimalToFraction(0.88888888));
    }
    @Test
    public void testFractionDecimalConversionRoundingDown()
    {
        assertEquals("1/2", convertDecimalToFraction(0.51));
        assertEquals("1/3", convertDecimalToFraction(0.339));
    }
    @Test
    public void testFractionDecimalConversionRoundingUp()
    {
        assertEquals("1/2", convertDecimalToFraction(0.49));
        assertEquals("1/3", convertDecimalToFraction(0.330));
    }
    @Test
    public void testFractionDecimalConversionTooLargeInput()
    {
        assertEquals("error", convertDecimalToFraction(123456));
        assertEquals("error", convertDecimalToFraction(2));
        assertEquals("error", convertDecimalToFraction(1.00001));
    }
    @Test
    public void testFractionDecimalConversionTooSmallInput()
    {
        assertEquals("error", convertDecimalToFraction(-0.5));
        assertEquals("error", convertDecimalToFraction(-0.000001));
        assertEquals("error", convertDecimalToFraction(-123));
    }
    @Test
    public void testFractionDecimalConversionEdgeCases()
    {
        assertEquals("error", convertDecimalToFraction(0.0));
        assertEquals("error", convertDecimalToFraction(1.0));
    }
    @Test
    public void testFractionDecimalConversionLengthyInput()
    {
        assertEquals("7/9", convertDecimalToFraction(0.7747874175971296507612950543298130874095478913409543));
        assertEquals("1/8", convertDecimalToFraction(0.1323432432412765754643565465464324546547425324543543));
        assertEquals("error", convertDecimalToFraction(1234567890.1234567890123456789012345678901234567890));
        assertEquals("error", convertDecimalToFraction(Math.PI));
    }
    @Test
    public void testFractionDecimalConversionDifferingDecimalLengths()
    {
        assertEquals("1/2", convertDecimalToFraction(0.5));
        assertEquals("1/2", convertDecimalToFraction(0.50));
        assertEquals("1/2", convertDecimalToFraction(0.500));
        assertEquals("3/10", convertDecimalToFraction(0.3));
        assertEquals("3/10", convertDecimalToFraction(0.30));
        assertEquals("3/10", convertDecimalToFraction(0.300));
    }
    @Test
    public void testFractionDecimalConversionNullInput()
    {
        try
        {
            Double d = null;
            convertDecimalToFraction(d);
            fail("Expected a NullPointerException");
        } catch (NullPointerException unused) {
        }

    }

    @Test
    public void testFractionDecimalConversionStringInput()
    {
        try
        {
            String d = "test";
            convertDecimalToFraction(Double.parseDouble(d));
            fail("Expected a NumberFormatException");
        } catch (NumberFormatException unused) {
        }

    }

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
        } catch (NullPointerException unused) {
        }

    }

    @Test
    public void testScaleRecipeNullRecipe()
    {
        try
        {
            ArrayList<Ingredient> ingredients = scaleIngredients(null, 2);
        } catch (NullPointerException unused) {
        }

    }

    /**Ingredient Test**/
    @Test
    public void testIngredientSimple()
    {
        //test the file could run or not
        assertEquals(1, 1);
    }//end testSimple

    @Test
    public void testIngredientCreation()
    {
        ArrayList<Ingredient>inList = new ArrayList<>();
        Ingredient in1 = new Ingredient("a1", "aa1", 0.1d);
        assertEquals("a1" , in1.getName());
        inList.add(in1);

        Ingredient in2 = new Ingredient("b1", "bb1", 0.2d);
        inList.add(in2);
        assertEquals(2,inList.size());

        Ingredient in3 = new Ingredient("","",0.3d);
        inList.add(in3);
        assertEquals(3,inList.size());

        Ingredient in4 = new Ingredient(null,"mm",0.4d);
        inList.add(in4);
        assertEquals(4,inList.size());

        Ingredient in5 = new Ingredient("e1","ee1",5);
        inList.add(in5);
        assertEquals(5,inList.size());

        Ingredient in6 = new Ingredient("f1","mm", Double.MAX_VALUE);
        inList.add(in6);
        assertEquals(6,inList.size());

        Ingredient in7 = new Ingredient("f1","mm", Double.MAX_VALUE+1);
        inList.add(in7);
        assertEquals(7,inList.size());

    }//end testIngredientCreation

    @Test
    public void testIngredientGetName()
    {
        Ingredient in1 = new Ingredient("a1", "bb", 0.1d);
        assertEquals("a1" , in1.getName());

        Ingredient in2= new Ingredient(null,"b2",0.2d);
        assertNull(in2.getName());

        Ingredient in3 = new Ingredient("","c3",0.3d);
        assertEquals("",in3.getName());
    }//end testIngredientGetName

    @Test
    public void testIngredientSetName()
    {
        //assertNotNull
        Ingredient i1 = new Ingredient("a", "b", 666);
        i1.setName("aa");
        assertEquals("aa", i1.getName());

        i1.setName("");
        assertEquals("",i1.getName());

        i1.setName(null);
        assertNull(i1.getName());
    }//end testIngredientSetName

    @Test
    public void testIngredientGetMeasurement()
    {
        Ingredient mea1 = new Ingredient("a1", "MM", 123);
        assertEquals("mm", mea1.getMeasurement());

        Ingredient mea2 = new Ingredient(null, "MM", 123);
        assertNull(mea2.getName());
        assertEquals("mm", mea2.getMeasurement());
    }//end testIngredientGetMeasurement

    @Test
    public void testIngredientSetMeasurement()
    {
        //assertNotNull
        Ingredient mea2 = new Ingredient("a", "MM", 888);
        assertEquals("mm", mea2.getMeasurement());

        mea2.setMeasurement("PINCH");
        assertEquals("pinch", mea2.getMeasurement());

        mea2.setMeasurement("abcde");
        assertEquals("pinch", mea2.getMeasurement());

        mea2.setMeasurement("");
        assertEquals("pinch", mea2.getMeasurement());
    }//end testIngredientSetMeasurement

    @Test
    public void testIngredientGetAmount()
    {
        Ingredient amo1 = new Ingredient("a1", "b1", 0.1);
        assertEquals(0.1, amo1.getAmount());

        amo1.setAmount(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE,amo1.getAmount());

        amo1.setAmount(-0.1d);
        assertEquals(Double.MAX_VALUE , amo1.getAmount());

        amo1.setAmount(0.0);
        assertEquals(Double.MAX_VALUE , amo1.getAmount());

        amo1.setAmount(1);
        assertEquals(1.0 , amo1.getAmount());
    }//end testIngredientGetAmount

    @Test
    public void testIngredientSetAmount()
    {
        //assertNotNull
        Ingredient amo2 = new Ingredient("a", "MM", 888.0);
        assertEquals(888.0, amo2.getAmount());

        amo2.setAmount(111);
        assertEquals(111.0, amo2.getAmount());

        amo2.setAmount(222);
        assertEquals(222.0 , amo2.getAmount());

        amo2.setAmount(-0.1d);
        assertEquals(222.0 , amo2.getAmount());

        amo2.setAmount(0.0);
        assertEquals(222.0,amo2.getAmount());

        amo2.setAmount(Integer.MAX_VALUE);
        assertEquals((double) Integer.MAX_VALUE, amo2.getAmount());

        amo2.setAmount(Integer.MIN_VALUE);
        assertEquals(Math.abs((double)(Integer.MIN_VALUE)+1), amo2.getAmount());
    }//end testIngredientSetMeasurement


    @Test
    public void testIngredientGetUnitType()
    {
        Ingredient unittype1 = new Ingredient("a", "MM", 0.1);
        assertEquals("SIZE", unittype1.getUnitType());

        unittype1.setMeasurement("PINCH");
        assertEquals("VOLUME", unittype1.getUnitType());

        unittype1.setMeasurement("MG");
        assertEquals("WEIGHT", unittype1.getUnitType());

        unittype1.setMeasurement("MG");
        assertEquals("WEIGHT" , unittype1.getUnitType());

        unittype1.setMeasurement("MM");
        assertEquals("SIZE", unittype1.getUnitType());

        unittype1.setMeasurement("PINCH");
        assertEquals("VOLUME", unittype1.getUnitType());

        unittype1.setMeasurement("");
        assertEquals("VOLUME",unittype1.getUnitType());

        unittype1.setMeasurement(" ");
        assertEquals("VOLUME" ,unittype1.getUnitType());
    }//end testIngredientGetUnitType

    @Test
    public void testIngredientGetDisplayMeasurement()
    {
        Ingredient disp1 = new Ingredient("a", "MM", 0.1);
        assertEquals("0.10 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.22);
        assertEquals("0.22 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.222);
        assertEquals("0.22 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.229);
        assertEquals("0.23 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.2222);
        assertEquals("0.22 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.2229);
        assertEquals("0.22 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.0);
        assertEquals("0.22 mm" , disp1.getDisplayMeasurement());

        disp1.setAmount(-0.1d);
        assertEquals("0.22 mm" , disp1.getDisplayMeasurement());

        disp1.setAmount(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE + ".00 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(Integer.MIN_VALUE);
        assertEquals((Integer.MAX_VALUE) + ".00 mm" , disp1.getDisplayMeasurement());
    }//end testIngredientGetDisplayMeasurement

    @Test
    public void testsetUnitInformation()
    {
        Ingredient unitinfo1 = new Ingredient("a", "MM", 0.1);
        assertEquals("SIZE", unitinfo1.getUnitType());

        unitinfo1.setMeasurement("PINCH");
        assertEquals("VOLUME", unitinfo1.getUnitType());

        unitinfo1.setMeasurement("MG");
        assertEquals("WEIGHT", unitinfo1.getUnitType());

        unitinfo1.setMeasurement("MM");
        assertEquals("SIZE" , unitinfo1.getUnitType());

        Ingredient unitinfo2 = new Ingredient(null, "ff", 0.1);
        assertEquals(unitinfo2.getUnitType() , "DEFAULT");

        Ingredient unitinfo3 = new Ingredient(null, "", 0.1);
        assertEquals(unitinfo3.getUnitType() , "DEFAULT");

        Ingredient unitinfo4 = new Ingredient("", "null", 0.1);
        assertEquals(unitinfo4.getUnitType() , "DEFAULT");

        Ingredient unitinfo5 = new Ingredient(null, "", 0.0);
        assertEquals(unitinfo5.getUnitType() , "DEFAULT");
    }//end testsetUnitInformation

    /**Recipe Test**/
    @Test
    public void testRecipeSimple()
    {
        //test the file could run or not
        assertEquals(1, 1);
    }//end testSimple

    @Test
    public void testRecipeCreation()
    {
        //constructor1
        Recipe recipe1 = new Recipe(0);
        assertNotNull(recipe1);

        Recipe recipe2 = new Recipe(0);

        ArrayList<Recipe> inList = new ArrayList<>();
        inList.add(recipe1);
        inList.add(recipe2);

        assertEquals(2, inList.size());
    }//end testRecipeCreation

    @Test
    public void testRecipeCreation2()
    {
        //constructor2
        Recipe recipe1 = new Recipe("a1", 1, 123);
        assertEquals("a1",recipe1.getName());

        Recipe recipe2 = new Recipe("b1", 666, 777);

        ArrayList<Recipe> inList = new ArrayList<>();
        inList.add(recipe1);
        inList.add(recipe2);

        assertEquals("b1", recipe2.getName());

        assertEquals(2, inList.size());

        //retest - constructor 1 & 2 combination

        Recipe recipe3 = new Recipe(123);
        inList.add(recipe3);

        assertEquals("null",recipe3.getName());
        assertEquals(3, inList.size());
    }//end testRecipeCreation2

    @Test
    public void testRecipeGetName()
    {
        Recipe recipe1 = new Recipe("Paste", 60, 70);
        assertEquals("Paste", recipe1.getName());

        recipe1.setName("");
        assertEquals("", recipe1.getName());

        recipe1.setName(" ");
        assertEquals(" ", recipe1.getName());

        recipe1.setName(null);
        assertNull(recipe1.getName());

        recipe1.setName("%paste1");
        assertEquals("%paste1", recipe1.getName());
    }//testRecipeGetName

    @Test
    public void testRecipeSetName()
    {
        Recipe recipe1 = new Recipe("juice", 100, 200);
        recipe1.setName("aa");
        assertEquals("aa", recipe1.getName());

        recipe1.setName("");
        assertEquals("", recipe1.getName());

        recipe1.setName(" ");
        assertEquals(" ", recipe1.getName());

        recipe1.setName(null);
        assertNull( recipe1.getName());

        recipe1.setName("%paste1");
        assertEquals("%paste1", recipe1.getName());
    }//testRecipeSetName

    @Test
    public void testRecipeGetIngredients()
    {
        Recipe recipe1 = new Recipe("aaa",1,2);

        Ingredient ing1 = new Ingredient("Noodle", "aaa", 0.1);
        Ingredient ing2 = new Ingredient("Rice", "bbb", 0.2);
        Ingredient ing3 = new Ingredient("Cake","ccc", 0.3);

        recipe1.addIngredient(ing1);
        recipe1.addIngredient(ing2);
        recipe1.addIngredient(ing3);

        assertEquals(3, recipe1.getIngredients().size());

        ing1.setName("");
        assertEquals("" , ing1.getName());

        ing1.setName(null);
        assertNull(ing1.getName());

        ing1.setMeasurement("MM");
        assertEquals("mm" , ing1.getMeasurement());

        ing1.setMeasurement("MMM");
        assertEquals("mm" , ing1.getMeasurement());

        ing1.setAmount(0.0);
        assertEquals(0.1 , ing1.getAmount());

        ing1.setAmount(-0.1);
        assertEquals(0.1 , ing1.getAmount());
    }//testRecipeGetIngredients

    @Test
    public void testRecipeAddIngredients()
    {
        Recipe recipe1 = new Recipe("aaa",1,2);

        Ingredient ing1 = new Ingredient("Noodle", "aaa", 0.1);
        Ingredient ing2 = new Ingredient("Rice", "bbb", 0.2);
        Ingredient ing3 = new Ingredient("Cake", "ccc", 0.3);

        recipe1.addIngredient(ing1);
        recipe1.addIngredient(ing2);
        recipe1.addIngredient(ing3);

        assertEquals(3, recipe1.getIngredients().size());

        ing1.setName("");
        assertEquals("" , ing1.getName());

        ing1.setName(null);
        assertNull(ing1.getName());

        ing1.setMeasurement("MM");
        assertEquals("mm" , ing1.getMeasurement());

        ing1.setMeasurement("MMM");
        assertEquals("mm" , ing1.getMeasurement());

        ing1.setAmount(0.0);
        assertEquals(0.1 , ing1.getAmount());

        ing1.setAmount(-0.2);
        assertEquals(0.1 , ing1.getAmount());
    }//testRecipeAddIngredients

    @Test
    public void testRecipeGetStep()
    {
        Recipe step1 = new Recipe("abcde", 1,2);

        step1.addStep("ufo");
        assertEquals(1, step1.getSteps().size());
        step1.addStep("FD");
        assertEquals(2, step1.getSteps().size());

        step1.addStep("");
        assertEquals(3, step1.getSteps().size());

        step1.addStep(null);
        assertEquals(4, step1.getSteps().size());

        step1.addStep("123");
        assertEquals(5, step1.getSteps().size());

        step1.addStep("^&*@!#");
        assertEquals(6, step1.getSteps().size());
    }//end testRecipeGetStep

    @Test
    public void testRecipeAddStep()
    {
        Recipe step1 = new Recipe("abcde", 1,2);

        step1.addStep("ufo");
        assertEquals(1, step1.getSteps().size());

        step1.addStep("FD");
        assertEquals(2, step1.getSteps().size());

        step1.addStep("");
        assertEquals(3, step1.getSteps().size());

        step1.addStep(null);
        assertEquals(4, step1.getSteps().size());

        step1.addStep("123");
        assertEquals(5, step1.getSteps().size());

        step1.addStep("^&*@!#");
        assertEquals(6, step1.getSteps().size());
    }//end testRecipeAddStep

    @Test
    public void testRecipeGetTimeToMakeHrs()
    {
        Recipe time1 = new Recipe("abcde", 1,2);
        assertEquals(1, time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(0);
        assertEquals(0 , time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE , time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE , time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(-1);
        assertEquals(1 , time1.getTimeToMakeHrs());
    }//end testRecipeGetTimeToMakeHrs

    @Test
    public void testRecipeGetTimeToMakeMins()
    {
        Recipe time1 = new Recipe("abcde", 0,2);
        assertEquals(2 , time1.getTimeToMakeMins());

        time1.setTimeToMakeMins(0);
        assertEquals(0 , time1.getTimeToMakeMins());

        time1.setTimeToMakeMins(-1);
        assertEquals(1 , time1.getTimeToMakeMins());

        time1.setTimeToMakeMins(61);
        assertEquals(1, time1.getTimeToMakeMins());
        assertEquals(1, time1.getTimeToMakeHrs());

        time1.setTimeToMakeMins(123);
        assertEquals(3, time1.getTimeToMakeMins());
        assertEquals(3, time1.getTimeToMakeHrs());

        time1.setTimeToMakeMins(Integer.MAX_VALUE);
        assertEquals( 7, time1.getTimeToMakeMins());

        time1.setTimeToMakeMins(Integer.MIN_VALUE);
        assertEquals( -8, time1.getTimeToMakeMins());
    }//end testRecipeGetTimeToMakeMins

    @Test
    public void testRecipeGetTimeToMakeString()
    {
        Recipe time1 = new Recipe("abcde", 1,2);
        assertEquals("1h 02m" , time1.getTimeToMakeString());

        Recipe time2 = new Recipe("abcde", 0,10);
        assertEquals("0h 10m" , time2.getTimeToMakeString());

        Recipe time3 = new Recipe("abcde", 0,0);
        assertEquals("0h 00m" , time3.getTimeToMakeString());

        Recipe time4 = new Recipe("abcde", 0,1);
        assertEquals("0h 01m" , time4.getTimeToMakeString());

        Recipe time5 = new Recipe("abcde", 0,Integer.MAX_VALUE);
        assertEquals("35791394"+ "h " +
                "07"+ "m" , time5.getTimeToMakeString());

        Recipe time6 = new Recipe("abcde", -1,-2);
        assertEquals("1h 02m" , time6.getTimeToMakeString());
    }//testRecipeGetTimeToMakeString

    @Test
    public void testRecipeSetTimeToMakeHrs()
    {
        Recipe time1 = new Recipe("abcde", 1,2);
        time1.setTimeToMakeHrs(99);
        assertEquals(99 , time1.getTimeToMakeHrs());

        time1.setTimeToMakeMins(61);
        assertEquals(1, time1.getTimeToMakeMins());
        assertEquals(100, time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(-1);
        assertEquals(1, time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, time1.getTimeToMakeHrs());
    }//end testRecipeSetTimeToMakeHrs

    @Test
    public void testRecipeSetTimeToMakeMins()
    {
        Recipe time1 = new Recipe("abcde", 1,2);

        time1.setTimeToMakeMins(88);
        assertEquals(28, time1.getTimeToMakeMins());

        time1.setTimeToMakeMins(61);
        assertEquals(1, time1.getTimeToMakeMins());
        assertEquals(3, time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(-1);
        assertEquals(1, time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(0);
        assertEquals(0, time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, time1.getTimeToMakeHrs());

        time1.setTimeToMakeHrs(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, time1.getTimeToMakeHrs());
    }

    @Test
    public void testRecipeGetDifficulty()
    {
        Recipe diff1 = new Recipe("abcde", 1,2);
        assertEquals(Difficulty.NOT_RATED , diff1.getDifficulty());

        diff1.setDifficulty(Difficulty.AMATEUR);
        assertEquals(Difficulty.AMATEUR, diff1.getDifficulty());

        diff1.setDifficulty(null);
        assertNull( diff1.getDifficulty());


    }

    @Test
    public void testRecipeGetDifficultyString(){
        Recipe diff1 = new Recipe("abcde", 1,2);

        assertEquals("Difficulty: Not rated" , diff1.getDifficultyString());

        diff1.setDifficulty(Difficulty.MASTER_CHEF);
        assertEquals("Difficulty: Master Chef", diff1.getDifficultyString());
    }//end testRecipeGetDifficultyString


    @Test
    public void testRecipeSetDifficulty()
    {
        Recipe diff1 = new Recipe("abcde", 1,2);
        assertEquals(Difficulty.NOT_RATED, diff1.getDifficulty());

        diff1.setDifficulty(Difficulty.AMATEUR);
        assertEquals("Difficulty: Amateur", diff1.getDifficultyString());

        diff1.setDifficulty(null);
        assertNull(diff1.getDifficulty());
    }//end testRecipeSetDifficulty

    @Test
    public void testRecipeGetQuality()
    {
        Recipe qua1 = new Recipe("abcde", 1,2);
        assertEquals(Quality.NOT_RATED, qua1.getQuality());

        qua1.setQuality(Quality.TASTY);
        assertEquals(Quality.TASTY, qua1.getQuality());

        qua1.setQuality(null);
        assertNull( qua1.getQuality());
    }//end testRecipeGetQuality

    @Test
    public void testRecipeGetQualityString()
    {
        Recipe qua1 = new Recipe("abcde", 1,2);

        assertEquals("Taste: Not rated" , qua1.getQualityString());

        qua1.setQuality(Quality.TASTY);
        assertEquals("Taste: Tasty", qua1.getQualityString());
    }//end testRecipeGetQualityString


    @Test
    public void testRecipeSetQuality(){
        Recipe qua1 = new Recipe("abcde", 1,2);
        assertEquals(Quality.NOT_RATED, qua1.getQuality());

        qua1.setQuality(Quality.TASTY);
        assertEquals("Taste: Tasty", qua1.getQualityString());

        qua1.setQuality(null);
        assertNull(qua1.getQuality());

    }

    @Test
    public void testAddRecipeRate()
    {
        Recipe rate1 = new Recipe(666);
        rate1.rate(0.0f);
        assertEquals(0.0f, rate1.getRating());

        rate1.rate(0.2f);
        assertEquals(0.1f, rate1.getRating());
    }//end testAddRecipeRate

    @Test
    public void testRecipeGetRating()
    {
        Recipe rating1 = new Recipe("abcde", 1,2);
        rating1.rate(0.1f);
        assertEquals(0.1f, rating1.getRating());

        rating1.rate(0.0f);
        assertEquals(0.05f,rating1.getRating());

        rating1.rate(0.5f);
        assertEquals(0.2f,rating1.getRating());
    }//testRecipeGetRating

    @Test
    public void testRecipeGetRatingString()
    {
        Recipe rating1 = new Recipe("abcde", 1,2);
        rating1.rate(0.0f);
        assertEquals("-/5", rating1.getRatingString());
    }
}

