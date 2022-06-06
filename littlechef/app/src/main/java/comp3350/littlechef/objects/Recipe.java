package comp3350.littlechef.objects;

import java.util.ArrayList;

enum Difficulty
{
    NOT_RATED,
    BEGINNER,
    AMATEUR,
    EXPERIENCED,
    MASTER_CHEF
}

enum Quality
{
    NOT_RATED,
    HORRIBLE,
    TASTY,
    DELICIOUS,
    HEAVENLY
}

public class Recipe
{
    private static int nextID = 0;

    //instance vars
    private String name;
    //TODO should maybe make a timer instance variable for the time
    private int recipeID;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps; //steps(instructions) to make the recipe

    //rating criteria
    private float timeToMake; //could later make a timer to calculate the average time to make for the user(in hrs)
    private Difficulty difficulty; //could make it a user-defined string?
    private Quality quality; //same as for difficulty?
    private ArrayList<Float> rating; //calculate the average similar to gpa calculation, from 0 to 5(can make "animated 5 stars that fill up the color later")
    //or could make it overall rating based on the rating the user gives and/or calculated from enum values

    //constructors
    public Recipe(int recipeID) //is used for accessRandom in the AccessRecipes.java
    {
        this.recipeID = recipeID;

        this.name = null;
        this.timeToMake = 0.0f;
        ingredients = null;
        steps = null;

        difficulty = null;
        quality = null;
        rating = null;
    }

    public Recipe(String name, float timeToMake)
    {
        this.name = name;
        this.recipeID = nextID++;
        this.timeToMake = timeToMake;
        ingredients = new ArrayList<Ingredient>();
        steps = new ArrayList<String>();

        difficulty = Difficulty.NOT_RATED;
        quality = Quality.NOT_RATED;
        rating = new ArrayList<Float>();
    }

    //private methods
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public void addIngredient(Ingredient ingr)
    {
        ingredients.add(ingr);
    }

    public ArrayList<String> getSteps()
    {
        return steps;
    }

    public void addStep(String step)
    {
        steps.add(step);
    }

    public float getTimeToMake()
    {
        return getTimeToMake();
    }

    public void setTimeToMake(float time)
    {
        timeToMake = time;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }

    public Quality getQuality()
    {
        return quality;
    }

    public void setQuality(Quality quality)
    {
        this.quality = quality;
    }

    public float getRating()
    {
        int size = rating.size(); //number of ratings
        float averageRating = 0.0f;

        //if rating array is empty, means no rating was given yet -> defaults to 0.0
        if(size != 0)
        {
            for(float num:rating)
            {
                averageRating += num;
            }
            averageRating = averageRating/size;
        }
        return averageRating;
    }

    public void rate(float userRating)
    {
        rating.add(userRating);
    }




}
