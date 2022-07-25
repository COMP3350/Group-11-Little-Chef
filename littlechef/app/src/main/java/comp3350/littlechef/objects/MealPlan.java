package comp3350.littlechef.objects;

import java.io.Serializable;
import java.util.ArrayList;

import comp3350.littlechef.business.AccessRecipes;

public class MealPlan implements Serializable
{

    private ArrayList<Recipe> dayList;
    String day;

    //for testing add random recipes
    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;


    //initiaize all instructions
    public MealPlan()
    {
        dayList = new ArrayList();
        day = "[this day]";

        //TO TEST ADD SOME RANDOM RECIPES TO THE LISTS*****************
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        accessRecipes.getRecipes(recipeList);

        dayList.add(recipeList.get(0));
        dayList.add(recipeList.get(1));
        //END TEST CODE*************************************
    }

    public String getDay()
    {
        return this.day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public ArrayList<Recipe> getList()
    {
        return dayList;
    }

    public void addRecipe(Recipe recipe)
    {
        recipeList.add(recipe);
    }

/*
    public void removeFrom(String day, int index)
    {
        if(day.equals("Sunday") && !sundayList.isEmpty())
        {
            sundayList.remove(index);
        }
    }
    public void addTo(String day, Recipe recipe)
    {
        if(day.equals("Sunday"))
        {
            sundayList.add(recipe);
        }
    }

    public int sizeSunday()
    {
        return sundayList.size();
    }
    */


}
