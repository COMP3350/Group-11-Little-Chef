package comp3350.littlechef.objects;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;

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

        assertEquals(2, inList.size());
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

        assertEquals(2, inList.size());

        //retest - constructor 1 & 2 combination

        Recipe recipe3 = new Recipe(123);
        inList.add(recipe3);
        assertEquals(3, inList.size());
    }

    @Test
    public void testRecipeGetName() {
        Recipe recipe1 = new Recipe("Paste", 60, 70);
        assertNotNull(recipe1);

        assertEquals("Paste", recipe1.getName());
    }

    @Test
    public void testRecipeSetName(){
        Recipe recipe1 = new Recipe("juice", 100, 200);
        recipe1.setName("aa");
        assertEquals("aa", recipe1.getName());
    }

    @Test
    public void testRecipeGetIngredients(){
        Recipe recipe1 = new Recipe("aaa",1,2);

        Ingredient ing1 = new Ingredient("Noodle", "aaa", 0.1);
        Ingredient ing2 = new Ingredient("Rice", "bbb", 0.2);
        Ingredient ing3 = new Ingredient("Cake","ccc", 0.3);

        recipe1.addIngredient(ing1);
        recipe1.addIngredient(ing2);
        recipe1.addIngredient(ing3);

        assertEquals(3, recipe1.getIngredients().size());
    }

    @Test
    public void testRecipeAddIngredients(){
        Recipe recipe1 = new Recipe("aaa",1,2);

        Ingredient ing1 = new Ingredient("Noodle", "aaa", 0.1);
        Ingredient ing2 = new Ingredient("Rice", "bbb", 0.2);
        Ingredient ing3 = new Ingredient("Cake");

        recipe1.addIngredient(ing1);
        recipe1.addIngredient(ing2);
        recipe1.addIngredient(ing3);

        assertEquals(3, recipe1.getIngredients().size());
    }

    @Test
    public void testRecipeGetStep(){
        Recipe step1 = new Recipe("abcde", 1,2);

        step1.addStep("ufo");
        step1.addStep("FD");
        assertEquals(2, step1.getSteps().size());
    }

    @Test
    public void testRecipeAddStep(){
        Recipe step1 = new Recipe("abcde", 1,2);

        step1.addStep("ufo");
        assertEquals(1, step1.getSteps().size());

        step1.addStep("FD");
        assertEquals(1, step1.getSteps().size());
    }

    @Test
    public void testRecipeGetTimeToMakeHrs(){
        Recipe time1 = new Recipe("abcde", 1,2);
        assertEquals(1, time1.getTimeToMakeHrs());
    }

    @Test
    public void testRecipeGetTimeToMakeMins(){
        Recipe time1 = new Recipe("abcde", 1,2);
        assertEquals(2 , time1.getTimeToMakeMins());
    }

    @Test
    public void testRecipeGetTimeToMakeString(){
        Recipe time1 = new Recipe("abcde", 1,2);
        assertEquals("1h 02m" , time1.getTimeToMakeString());

        Recipe time2 = new Recipe("abcde", 0,10);
        assertEquals("0h 10m" , time2.getTimeToMakeString());

        Recipe time3 = new Recipe("abcde", 0,0);
        assertEquals("0h 00m" , time3.getTimeToMakeString());

        Recipe time4 = new Recipe("abcde", 0,1);
        assertEquals("0h 01m" , time4.getTimeToMakeString());

        //Recipe time5 = new Recipe("abcde", Integer.MAX_VALUE,Integer.MAX_VALUE);
    }

    @Test
    public void testRecipeSetTimeToMakeHrs(){
        Recipe time1 = new Recipe("abcde", 1,2);
        time1.setTimeToMakeHrs(99);
        assertEquals(99 , time1.getTimeToMakeHrs());
    }

    @Test
    public void testRecipeSetTimeToMakeMins(){
        Recipe time1 = new Recipe("abcde", 1,2);

        time1.setTimeToMakeMins(88);
        assertEquals(88, time1.getTimeToMakeMins());
    }

    @Test
    public void testRecipeGetDifficulty(){
        Recipe diff1 = new Recipe("abcde", 1,2);
        assertEquals(Difficulty.NOT_RATED , diff1.getDifficulty());

        diff1.setDifficulty(Difficulty.AMATEUR);
        assertEquals(Difficulty.AMATEUR, diff1.getDifficulty());
    }

    @Test
    public void testRecipeGetDifficultyString(){
        Recipe diff1 = new Recipe("abcde", 1,2);

        assertEquals("Difficulty: Not rated" , diff1.getDifficultyString());

        diff1.setDifficulty(Difficulty.MASTER_CHEF);
        assertEquals("Difficulty: Master Chef", diff1.getDifficultyString());
    }


    @Test
    public void testRecipeSetDifficulty(){
        Recipe diff1 = new Recipe("abcde", 1,2);
        assertEquals(Difficulty.NOT_RATED, diff1.getDifficulty());

        diff1.setDifficulty(Difficulty.AMATEUR);
        assertEquals("Difficulty: Amateur", diff1.getDifficultyString());
    }

    @Test
    public void testRecipeGetQuality(){
        Recipe qua1 = new Recipe("abcde", 1,2);
        assertEquals(Quality.NOT_RATED, qua1.getQuality());

        qua1.setQuality(Quality.TASTY);
        assertEquals(Quality.TASTY, qua1.getQuality());
    }

    @Test
    public void testRecipeGetQualityString(){
        Recipe qua1 = new Recipe("abcde", 1,2);

        assertEquals("Taste: Not rated" , qua1.getQualityString());

        qua1.setQuality(Quality.TASTY);
        assertEquals("Taste: Tasty", qua1.getQualityString());
    }


    @Test
    public void testRecipeSetQuality(){
        Recipe qua1 = new Recipe("abcde", 1,2);
        assertEquals(Quality.NOT_RATED, qua1.getQuality());

        qua1.setQuality(Quality.TASTY);
        assertEquals("Taste: Tasty", qua1.getQualityString());
    }

    @Test
    public void testAddRecipeRate(){
        Recipe rate1 = new Recipe(666);
        rate1.rate(0.1f);

        assertEquals(0.1f, rate1.getRating());

    }

    @Test
    public void testRecipeGetRating(){
        Recipe rating1 = new Recipe("abcde", 1,2);
        rating1.rate(0.1f);
        assertEquals(0.1f, rating1.getRating());

        rating1.rate((0.2f));
        assertEquals(0.15f,rating1.getRating() );
    }

    @Test
    public void testRecipeGetRatingString(){
        Recipe rating1 = new Recipe("abcde", 1,2);
        rating1.rate(0.0f);
        assertEquals("-/5", rating1.getRatingString());

        rating1.rate((0.2f));
        assertEquals(Float.toString(0.1f) + "/5",rating1.getRatingString() );
    }

}