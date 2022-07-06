package comp3350.littlechef.objects;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

public class RecipeTest extends TestCase
{
    public RecipeTest(String arg0) {
        super(arg0);
    }//end RecipeTest

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

        Ingredient ing1 = new Ingredient("Noodle", null, 0.1);
        Ingredient ing2 = new Ingredient("Rice", null, 0.2);
        Ingredient ing3 = new Ingredient("Cake",null, 0.3);

        recipe1.addIngredient(ing1);
        recipe1.addIngredient(ing2);
        recipe1.addIngredient(ing3);

        assertEquals(3, recipe1.getIngredients().size());

        ing1.setName("");
        assertEquals("" , ing1.getName());

        ing1.setName(null);
        assertNull(ing1.getName());

        ing1.setMeasurement(Unit.MM);
        assertEquals(Unit.MM , ing1.getMeasurement());

        ing1.setMeasurement(null);
        assertEquals(Unit.MM , ing1.getMeasurement());

        ing1.setAmount(0.0);
        assertEquals(0.1 , ing1.getAmount());

        ing1.setAmount(-0.1);
        assertEquals(0.1 , ing1.getAmount());
    }//testRecipeGetIngredients

    @Test
    public void testRecipeAddIngredients()
    {
        Recipe recipe1 = new Recipe("aaa",1,2);

        Ingredient ing1 = new Ingredient("Noodle", null, 0.1);
        Ingredient ing2 = new Ingredient("Rice", null, 0.2);
        Ingredient ing3 = new Ingredient("Cake", null, 0.3);

        recipe1.addIngredient(ing1);
        recipe1.addIngredient(ing2);
        recipe1.addIngredient(ing3);

        assertEquals(3, recipe1.getIngredients().size());

        ing1.setName("");
        assertEquals("" , ing1.getName());

        ing1.setName(null);
        assertNull(ing1.getName());

        ing1.setMeasurement(Unit.MM);
        assertEquals(Unit.MM , ing1.getMeasurement());

        ing1.setMeasurement(null);
        assertEquals(Unit.MM , ing1.getMeasurement());

        ing1.setAmount(0.0);
        assertEquals(0.1 , ing1.getAmount());

        ing1.setAmount(-0.2);
        assertEquals(0.1 , ing1.getAmount());
    }//testRecipeAddIngredients

    @Test
    public void testRecipeGetStep() //TODO CHANGE -> RECIPE HAS String[] FOR INSTRUCTIONS
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
    public void testRecipeAddStep() //TODO CHANGE -> RECIPE HAS String[] FOR INSTRUCTIONS
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
        assertEquals(Difficulty.NOT_RATED , diff1.getDifficultyRatings());

        diff1.setDifficulty(Difficulty.AMATEUR);
        assertEquals(Difficulty.AMATEUR, diff1.getDifficultyRatings());

        diff1.setDifficulty(null);
        assertNull( diff1.getDifficultyRatings());


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
        assertEquals(Difficulty.NOT_RATED, diff1.getDifficultyRatings());

        diff1.setDifficulty(Difficulty.AMATEUR);
        assertEquals("Difficulty: Amateur", diff1.getDifficultyString());

        diff1.setDifficulty(null);
        assertNull(diff1.getDifficultyRatings());
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