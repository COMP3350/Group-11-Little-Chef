package comp3350.littlechef.business;


import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;
import comp3350.littlechef.objects.Recipe;


public class MakeByTypeTest extends TestCase
{

    public MakeByTypeTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testOneCase()
    {
        //should send an arraylist with recipe
        ArrayList<Recipe> testList;

        testList = makeRecipeListOne();
        ArrayList<Recipe> testChallenge = MakeByType.createList("challenge", testList);
        assertEquals("test challenge error",1, testChallenge.size());

        testList = makeRecipeListOne();
        ArrayList<Recipe> testEase = MakeByType.createList("ease", testList);
        assertEquals("test ease error",1, testEase.size());

        testList = makeRecipeListOne();
        ArrayList<Recipe> testSavingTime = MakeByType.createList("savingTime", testList);
        assertEquals("test saving time error",1, testSavingTime.size());

        testList = makeRecipeListOne();
        ArrayList<Recipe> testTaste = MakeByType.createList("taste", testList);
        assertEquals("test taste error",1, testTaste.size());

        ArrayList<Recipe> testSurpriseOne = new ArrayList<Recipe>();
        testSurpriseOne.add( new Recipe("Test7") );
        ArrayList<Recipe> testSurprise = MakeByType.createList("surprise", testSurpriseOne);
        assertEquals("surprise test error",1, testSurprise.size());
    }

    @Test
    public void testEmptyCases()
    {
        ArrayList<Recipe> testList = new ArrayList<Recipe>();

        ArrayList<Recipe> testChallenge = MakeByType.createList("challenge", testList);
        assertEquals("test challenge error",0, testChallenge.size());

        ArrayList<Recipe> testEase = MakeByType.createList("ease", testList);
        assertEquals("test ease error",0, testEase.size());

        ArrayList<Recipe> testSavingTime = MakeByType.createList("savingTime", testList);
        assertEquals("test saving time error",0, testSavingTime.size());

        ArrayList<Recipe> testTaste = MakeByType.createList("taste", testList);
        assertEquals("test taste error",0, testTaste.size());

        ArrayList<Recipe> testSurprise = MakeByType.createList("surprise", testList);
        assertEquals("test surprise error",0, testSurprise.size());

    }

    //test general cases, should not be any duplicates showing
    @Test
    public void testGeneralCases()
    {
        //should send an arraylist with recipe
        ArrayList<Recipe> testList;

        testList = makeRecipeListThree();
        ArrayList<Recipe> testChallenge = MakeByType.createList("challenge", testList);
        assertEquals("test challenge error",3, testChallenge.size());
        assertEquals(0,countInstances(testChallenge));

        testList = makeRecipeListThree();
        ArrayList<Recipe> testEase = MakeByType.createList("ease", testList);
        assertEquals("test ease error",3, testEase.size());
        assertEquals(0,countInstances(testEase));

        testList = makeRecipeListThree();
        ArrayList<Recipe> testSavingTime = MakeByType.createList("savingTime", testList);
        assertEquals("test saving time error",3, testSavingTime.size());
        assertEquals(0,countInstances(testSavingTime));

        testList = makeRecipeListThree();
        ArrayList<Recipe> testTaste = MakeByType.createList("taste", testList);
        assertEquals("test taste error",3, testTaste.size());
        assertEquals(0,countInstances(testTaste));

        testList = makeRecipeListThree();
        ArrayList<Recipe> testSurprise = MakeByType.createList("surprise", testList);
        assertEquals("surprise",3, testSurprise.size());
        assertEquals(0,countInstances(testSurprise));

    }
    private int countInstances(ArrayList<Recipe> list)
    {
        //checks if the middle recipe is unique
        Recipe middle = list.get(1);

        int count = 0;
        if(middle.equals(list.get(0)))
            count++;
        if(middle.equals(list.get(2)))
            count++;
        return count;
    }
    //makes an arraylist with one of each type and returns it for general testing
    private ArrayList<Recipe> makeRecipeListOne()
    {
        ArrayList<Recipe> list = new ArrayList<Recipe>();

        //challenge
        Recipe recipe = new Recipe("Test1");
        recipe.addDifficultyRating(5);
        list.add(recipe);

        //ease
        recipe = new Recipe("Test4");
        recipe.addDifficultyRating(1.0);
        list.add(recipe);

        //saving time
        recipe = new Recipe("Test7");
        recipe.addCookingTime(120);
        list.add(recipe);

        //taste
        recipe = new Recipe("Test10");
        recipe.addTasteRating(5);
        list.add(recipe);

        return list;
    }

    //makes an arraylist with three of each type and returns it for general testing
    private ArrayList<Recipe> makeRecipeListThree()
    {
        ArrayList<Recipe> list = new ArrayList<Recipe>();

        //challenge
        Recipe recipe = new Recipe("Test1");
        recipe.addDifficultyRating(5);
        list.add(recipe);
        recipe = new Recipe("Test2");
        recipe.addDifficultyRating(5);
        list.add(recipe);
        recipe = new Recipe("Test3");
        recipe.addDifficultyRating(5);
        list.add(recipe);

        //ease
        recipe = new Recipe("Test4");
        recipe.addDifficultyRating(1.0);
        list.add(recipe);
        recipe = new Recipe("Test5");
        recipe.addDifficultyRating(1.0);
        list.add(recipe);
        recipe = new Recipe("Test6");
        recipe.addDifficultyRating(1.0);
        list.add(recipe);

        //saving time
        recipe = new Recipe("Test7");
        recipe.addCookingTime(120);
        list.add(recipe);
        recipe = new Recipe("Test8");
        recipe.addCookingTime(120);
        list.add(recipe);
        recipe = new Recipe("Test9");
        recipe.addCookingTime(120);
        list.add(recipe);

        //taste
        recipe = new Recipe("Test10");
        recipe.addTasteRating(5);
        list.add(recipe);
        recipe = new Recipe("Test11");
        recipe.addTasteRating(5);
        list.add(recipe);
        recipe = new Recipe("Test12");
        recipe.addTasteRating(5);;
        list.add(recipe);

        return list;
    }
}
