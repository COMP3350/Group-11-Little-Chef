package comp3350.littlechef.business;


import android.widget.ArrayAdapter;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;
import comp3350.littlechef.objects.Recipe;


public class FilteredRecipesTest extends TestCase
{
    private static final int MAX_SHOWING = 3;

    public FilteredRecipesTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testNullCases()
    {
        ArrayList<Recipe> recipeList= new ArrayList<Recipe>();

        for(int i = 0; i < 50; i++)
        {
            recipeList.add(null);
        }

        ArrayList<Recipe> newList = FilteredRecipes.getListofRecipesByType(null, 5, null);
        assertEquals("newList size= " ,null, newList);

        ArrayList<Recipe> newListEase = FilteredRecipes.getListofRecipesByType("ease", 5, null);
        assertEquals("newList size= " ,null, newListEase);

        ArrayList<Recipe> newListChallenge = FilteredRecipes.getListofRecipesByType("challenge", 5, null);
        assertEquals("newList size= " ,null, newListChallenge);

        ArrayList<Recipe> newListSurprise = FilteredRecipes.getListofRecipesByType("surprise", 5, null);
        assertEquals("newList size= " ,null, newListSurprise);

        ArrayList<Recipe> newListSavingTime = FilteredRecipes.getListofRecipesByType("savingTime", 5, null);
        assertEquals("newList size= " ,null, newListSavingTime);

        ArrayList<Recipe> newListTaste = FilteredRecipes.getListofRecipesByType("taste", 5, null);
        assertEquals("newList size= " ,null, newListTaste);

    }

    //test typical cases, should not be any duplicates showing
    @Test
    public void testTypicalCases()
    {
        //should send an arraylist with recipe
        ArrayList<Recipe> testList;

        testList = makeRecipeListThree();
        ArrayList<Recipe> testChallenge = FilteredRecipes.getListofRecipesByType("challenge", MAX_SHOWING, testList);
        assertEquals("test challenge error",3, testChallenge.size());
        assertEquals(0,countInstances(testChallenge));

        testList = makeRecipeListThree();
        ArrayList<Recipe> testEase = FilteredRecipes.getListofRecipesByType("ease", MAX_SHOWING, testList);
        assertEquals("test ease error",3, testEase.size());
        assertEquals(0,countInstances(testEase));

        testList = makeRecipeListThree();
        ArrayList<Recipe> testSavingTime = FilteredRecipes.getListofRecipesByType("savingTime", MAX_SHOWING, testList);
        assertEquals("test saving time error",3, testSavingTime.size());
        assertEquals(0,countInstances(testSavingTime));

        testList = makeRecipeListThree();
        ArrayList<Recipe> testTaste = FilteredRecipes.getListofRecipesByType("taste", MAX_SHOWING, testList);
        assertEquals("test taste error",3, testTaste.size());
        assertEquals(0,countInstances(testTaste));

        testList = makeRecipeListThree();
        ArrayList<Recipe> testSurprise = FilteredRecipes.getListofRecipesByType("surprise", MAX_SHOWING, testList);
        assertEquals("surprise",3, testSurprise.size());
        assertEquals(0,countInstances(testSurprise));

    }

    @Test
    public void testEmptyList()
    {
        ArrayList<Recipe> testList = new ArrayList<Recipe>();

        ArrayList<Recipe> testChallenge = FilteredRecipes.getListofRecipesByType("challenge", MAX_SHOWING, testList);
        assertEquals("test challenge error",0, testChallenge.size());

        ArrayList<Recipe> testEase = FilteredRecipes.getListofRecipesByType("ease", MAX_SHOWING, testList);
        assertEquals("test ease error",0, testEase.size());

        ArrayList<Recipe> testSavingTime = FilteredRecipes.getListofRecipesByType("savingTime", MAX_SHOWING, testList);
        assertEquals("test saving time error",0, testSavingTime.size());

        ArrayList<Recipe> testTaste = FilteredRecipes.getListofRecipesByType("taste", MAX_SHOWING, testList);
        assertEquals("test taste error",0, testTaste.size());

        ArrayList<Recipe> testSurprise = FilteredRecipes.getListofRecipesByType("surprise", MAX_SHOWING,testList);
        assertEquals("test surprise error",0, testSurprise.size());

    }

    @Test
    public void testLizeSizeone()
    {
        //should send an arraylist with recipe
        ArrayList<Recipe> testList;

        testList = makeRecipeListOne();
        ArrayList<Recipe> testChallenge = FilteredRecipes.getListofRecipesByType("challenge", MAX_SHOWING, testList);
        assertEquals("test challenge error",1, testChallenge.size());

        testList = makeRecipeListOne();
        ArrayList<Recipe> testEase = FilteredRecipes.getListofRecipesByType("ease", MAX_SHOWING, testList);
        assertEquals("test ease error",1, testEase.size());

        testList = makeRecipeListOne();
        ArrayList<Recipe> testSavingTime = FilteredRecipes.getListofRecipesByType("savingTime", MAX_SHOWING, testList);
        assertEquals("test saving time error",1, testSavingTime.size());

        testList = makeRecipeListOne();
        ArrayList<Recipe> testTaste = FilteredRecipes.getListofRecipesByType("taste", MAX_SHOWING, testList);
        assertEquals("test taste error",1, testTaste.size());

        ArrayList<Recipe> testSurpriseOne = new ArrayList<Recipe>();
        testSurpriseOne.add( new Recipe("Test7") );
        ArrayList<Recipe> testSurprise = FilteredRecipes.getListofRecipesByType("surprise", MAX_SHOWING, testSurpriseOne);
        assertEquals("surprise test error",1, testSurprise.size());
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
