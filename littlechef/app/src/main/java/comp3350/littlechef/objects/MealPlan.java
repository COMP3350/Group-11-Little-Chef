package comp3350.littlechef.objects;

import java.io.Serializable;
import java.util.ArrayList;

import comp3350.littlechef.business.AccessRecipes;

public class MealPlan implements Serializable
{

    private ArrayList allDaysList;
    private ArrayList<Recipe> sundayList;
    private ArrayList<Recipe> mondayList;
    private ArrayList<Recipe> tuesdayList;
    private ArrayList<Recipe> wednesdayList;
    private ArrayList<Recipe> thursdayList;
    private ArrayList<Recipe> fridayList;
    private ArrayList<Recipe> saturdayList;

    //for testing add random recipes
    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;


    //initiaize all instructions
    public MealPlan()
    {
        allDaysList = new ArrayList();
        sundayList = new ArrayList();
        mondayList = new ArrayList();
        tuesdayList = new ArrayList();
        wednesdayList = new ArrayList();
        thursdayList = new ArrayList();
        fridayList = new ArrayList();
        saturdayList = new ArrayList();

        //TO TEST ADD SOME RANDOM RECIPES TO THE LISTS*****************
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        accessRecipes.getRecipes(recipeList);

        sundayList.add(recipeList.get(0));
        sundayList.add(recipeList.get(1));
        mondayList.add(recipeList.get(1));
        tuesdayList.add(recipeList.get(2));
        wednesdayList.add(recipeList.get(0));
        thursdayList.add(recipeList.get(1));
        fridayList.add(recipeList.get(2));
        saturdayList.add(recipeList.get(0));
        //END TEST CODE*************************************
    }

    public void removeFrom(String day, int index)
    {
        if(day.equals("Sunday"))
        {
            sundayList.remove(index);
        }

    }

    //combine all lists into one list with day names, for listview
    public ArrayList combineLists()
    {
        ArrayList newList = new ArrayList();
        newList.add("Sunday");
        newList.addAll(sundayList);
        newList.add("Monday");
        newList.addAll(mondayList);
        //newList.add("Tuesday");
        //newList.addAll(tuesdayList);
        //newList.add("Wednesday");
        //newList.addAll(wednesdayList);
        //newList.add("Thursday");
        //newList.addAll(thursdayList);
        //newList.add("Friday");
        //newList.addAll(fridayList);
        //newList.add("Saturday");
        //newList.addAll(saturdayList);
        return newList;
    }
}
