package comp3350.littlechef.objects;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;



public class IngredientTest extends TestCase
{
    public IngredientTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testIngredientCreation()
    {
        ArrayList<Ingredient>inList = new ArrayList<>();
        Ingredient ingredientName = new Ingredient("a1", null, 0.1d);
        assertEquals("a1" , ingredientName.getName());

        ingredientName.setName("b1");
        assertEquals("b1", ingredientName.getName());
        inList.add(ingredientName);

        Ingredient ingredientUnit = new Ingredient(null,Unit.MM,0.4d);
        inList.add(ingredientUnit);
        assertEquals(2,inList.size());

        Ingredient ingredientNewAmount = new Ingredient("e1",null,5);
        inList.add(ingredientNewAmount);
        assertEquals(3,inList.size());

        Ingredient ingredientMaxAmount = new Ingredient("f1",Unit.MM, Double.MAX_VALUE);
        inList.add(ingredientMaxAmount);
        assertEquals(4,inList.size());

    }//end testIngredientCreation

    @Test
    public void testIngredientName()
    {
        Recipe recipe = new Recipe(111);
        recipe.addIngredient(new Ingredient("a1", Unit.PINCH, 0.25));
        recipe.addIngredient(new Ingredient("", Unit.CUP, 0.5));
        recipe.addIngredient(new Ingredient("b", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient(" ", Unit.MM, 2d));
        recipe.addIngredient(new Ingredient("%^&*$#饺子", Unit.MG, 100));

        assertEquals("a1" , recipe.getIngredients().get(0).getName());
        assertEquals("" ,  recipe.getIngredients().get(1).getName());
        assertEquals("b" , recipe.getIngredients().get(2).getName());
        assertEquals(" " , recipe.getIngredients().get(3).getName());
        assertEquals("%^&*$#饺子" , recipe.getIngredients().get(4).getName());

        assertEquals(Unit.PINCH,recipe.getIngredients().get(0).getMeasurement());
        assertEquals(Unit.CUP,recipe.getIngredients().get(1).getMeasurement());
        assertEquals(Unit.TSP,recipe.getIngredients().get(2).getMeasurement());
        assertEquals(Unit.MM,recipe.getIngredients().get(3).getMeasurement());
        assertEquals(Unit.MG,recipe.getIngredients().get(4).getMeasurement());
    }//end testIngredientNameSimple

    @Test
    public void testIngredientMeasurement()
    {
        Recipe recipe = new Recipe(112);
        recipe.addIngredient(new Ingredient("ingredient0", Unit.G, 123));
        recipe.addIngredient(new Ingredient("ingredient1", Unit.MG, 1234));
        recipe.addIngredient(new Ingredient("ingredient2", Unit.KG, 1234));
        recipe.addIngredient(new Ingredient("ingredient3", Unit.L, 1234));
        recipe.addIngredient(new Ingredient("ingredient4", Unit.ML, 1234));

        assertEquals(Unit.G, recipe.getIngredients().get(0).getMeasurement());
        assertEquals(Unit.MG, recipe.getIngredients().get(1).getMeasurement());
        assertEquals(Unit.KG, recipe.getIngredients().get(2).getMeasurement());
        assertEquals(Unit.L, recipe.getIngredients().get(3).getMeasurement());
        assertEquals(Unit.ML, recipe.getIngredients().get(4).getMeasurement());

    }//end testIngredientMeasurement

    @Test
    public void testIngredientAmount()
    {
        Recipe recipe = new Recipe(113);
        recipe.addIngredient(new Ingredient("ingredient0", Unit.G, 0.1));
        recipe.addIngredient(new Ingredient("ingredient1", Unit.MG, -0.1));
        recipe.addIngredient(new Ingredient("ingredient2", Unit.KG, 0.0));
        recipe.addIngredient(new Ingredient("ingredient3", Unit.L, 1));
        recipe.addIngredient(new Ingredient("ingredient4", Unit.ML, 0.987973));
        recipe.addIngredient(new Ingredient("ingredient5", Unit.ML, Double.MAX_VALUE));

        assertEquals(0.1, recipe.getIngredients().get(0).getNumberOfIngredients());
        assertEquals(1.0, recipe.getIngredients().get(1).getNumberOfIngredients());
        assertEquals(1.0, recipe.getIngredients().get(2).getNumberOfIngredients());
        assertEquals(1.0, recipe.getIngredients().get(3).getNumberOfIngredients());
        assertEquals(0.987973, recipe.getIngredients().get(4).getNumberOfIngredients());
        assertEquals(Double.MAX_VALUE, recipe.getIngredients().get(5).getNumberOfIngredients());
    }//end testIngredientAmount



    @Test
    public void testIngredientConvertUnitType()
    {
        Recipe recipe = new Recipe(114);
        recipe.addIngredient(new Ingredient("ingredient0", Unit.PINCH, 0.1));
        recipe.addIngredient(new Ingredient("ingredient1", Unit.MG, -0.1));
        recipe.addIngredient(new Ingredient("ingredient2", Unit.KG, 0.0));
        recipe.addIngredient(new Ingredient("ingredient3", Unit.MM, 1));
        recipe.addIngredient(new Ingredient("ingredient4", Unit.L, 0.987973));
        recipe.addIngredient(new Ingredient("ingredient5", Unit.ML, Double.MAX_VALUE));

        assertEquals("WEIGHT", recipe.getIngredients().get(1).getUnitType());
        assertEquals("WEIGHT", recipe.getIngredients().get(2).getUnitType());
        assertEquals("SIZE", recipe.getIngredients().get(3).getUnitType());
        assertEquals("VOLUME", recipe.getIngredients().get(4).getUnitType());
        assertEquals("VOLUME", recipe.getIngredients().get(5).getUnitType());
    }//end testIngredientConvertUnitType

    @Test
    public void testIngredientDisplayMeasurement()
    {
        Recipe recipe = new Recipe(115);
        recipe.addIngredient(new Ingredient("ingredient0", Unit.PINCH, 0.1));
        recipe.addIngredient(new Ingredient("ingredient1", Unit.MG, -0.1));
        recipe.addIngredient(new Ingredient("ingredient2", Unit.KG, 0.222));
        recipe.addIngredient(new Ingredient("ingredient3", Unit.MM, 0.229));
        recipe.addIngredient(new Ingredient("ingredient4", Unit.L, 0.987973));
        recipe.addIngredient(new Ingredient("ingredient5", Unit.ML, 10001));
        recipe.addIngredient(new Ingredient("ingredient6", Unit.ML, Double.MIN_VALUE));
        recipe.addIngredient(new Ingredient("ingredient7", Unit.ML, 10000));

        assertEquals("0.10 PINCH", recipe.getIngredients().get(0).getDisplayMeasurement());
        assertEquals("1.00 MG", recipe.getIngredients().get(1).getDisplayMeasurement());
        assertEquals("0.22 KG", recipe.getIngredients().get(2).getDisplayMeasurement());
        assertEquals("0.23 MM", recipe.getIngredients().get(3).getDisplayMeasurement());
        assertEquals("0.99 L", recipe.getIngredients().get(4).getDisplayMeasurement());
        assertEquals("10000.00 ML", recipe.getIngredients().get(5).getDisplayMeasurement());
        assertEquals( "0.00 ML", recipe.getIngredients().get(6).getDisplayMeasurement());
        recipe.getIngredients().get(7).setMax_amount(5000.0);
        assertEquals( "5000.00 ML", recipe.getIngredients().get(7).getDisplayMeasurement());
        assertEquals( 5000.0, recipe.getIngredients().get(7).getMax_amount());
    }//end testIngredientDisplayMeasurement

    @Test
    public void testUnitInformation()
    {
        Ingredient PINCHVolume = new Ingredient("a", Unit.PINCH, 0.1);
        Ingredient TSPVolume = new Ingredient("a", Unit.TSP, 0.1);
        Ingredient TBSPVolume = new Ingredient("a", Unit.TBSP, 0.1);
        Ingredient CUPVolume = new Ingredient("a", Unit.CUP, 0.1);
        Ingredient MLVolume = new Ingredient("a", Unit.ML, 0.1);
        Ingredient LVolume = new Ingredient("a", Unit.ML, 0.1);
        Ingredient EdgeVolume = new Ingredient("a", Unit.QUANTITY, 0.1);

        assertEquals("VOLUME", PINCHVolume.getUnitType());
        assertEquals("VOLUME", TSPVolume.getUnitType());
        assertEquals("VOLUME", TBSPVolume.getUnitType());
        assertEquals("VOLUME", CUPVolume.getUnitType());
        assertEquals("VOLUME", MLVolume.getUnitType());
        assertEquals("VOLUME", LVolume.getUnitType());
        assertEquals("DEFAULT", EdgeVolume.getUnitType());

        Ingredient MGWeight = new Ingredient("a", Unit.MG, 0.1);
        Ingredient GWeight = new Ingredient("a", Unit.G, 0.1);
        Ingredient KGWeight = new Ingredient("a", Unit.KG, 0.1);
        assertEquals("WEIGHT", MGWeight.getUnitType());
        assertEquals("WEIGHT", GWeight.getUnitType());
        assertEquals("WEIGHT", KGWeight.getUnitType());

        Ingredient MMSize = new Ingredient("a", Unit.MM, 0.1);
        Ingredient CMSize = new Ingredient("a", Unit.CM, 0.1);
        Ingredient MSize = new Ingredient("a", Unit.M, 0.1);
        assertEquals("SIZE", MMSize.getUnitType());
        assertEquals("SIZE", CMSize.getUnitType());
        assertEquals("SIZE", MSize.getUnitType());

        Ingredient unitNull = new Ingredient("a", null, 0.1);
        unitNull.setMeasurement(null);
        assertEquals(unitNull.getUnitType() , "DEFAULT");
    }//end testUnitInformation

    @Test
    public void testCreateIngredientNull()
    {
        try
        {
            Recipe recipe = new Recipe(1000);
            Ingredient recipeNull = new Ingredient(null, null, 0);
        }
        catch (NullPointerException unused)
        {
            //catch error in create ingredient
        }
    }

    @Test
    public void testDisplayMeasurement()
    {
        try
        {
            Recipe recipe = new Recipe(1000);
            Ingredient recipeNull = new Ingredient(null, null, 0);
            recipe.addIngredient(recipeNull);
            recipeNull.getDisplayMeasurement();
        }
        catch (NullPointerException unused)
        {
            //catch error in get display measurement
        }
    }
}

