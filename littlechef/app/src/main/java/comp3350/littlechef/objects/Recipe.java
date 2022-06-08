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
    private int timeToMakeHrs; //could later make a timer to calculate the average time to make for the user(in hrs)
    private int timeToMakeMins;
    private Difficulty difficulty; //could make it a user-defined string?
    private Quality quality; //same as for difficulty?
    private ArrayList<Float> rating; //calculate the average similar to gpa calculation, from 0 to 5(can make "animated 5 stars that fill up the color later")
    //or could make it overall rating based on the rating the user gives and/or calculated from enum values

    //constructors
    public Recipe(int recipeID) //is used for accessRandom in the AccessRecipes.java
    {
        this.recipeID = recipeID;

        this.name = null;
        this.timeToMakeHrs = 0;
        this.timeToMakeMins = 0;
        ingredients = null;
        steps = null;

        difficulty = null;
        quality = null;
        rating = null;
    }

    public Recipe(String name, int timeToMakeHrs, int timeToMakeMins)
    {
        this.name = name;
        this.recipeID = nextID++;
        this.timeToMakeHrs = timeToMakeHrs;
        this.timeToMakeMins = timeToMakeMins;
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

    public int getTimeToMakeHrs()
    {
        return timeToMakeHrs;
    }

    public int getTimeToMakeMins()
    {
        return timeToMakeMins;
    }

    public String getTimeToMakeString()
    {
        int hours = getTimeToMakeHrs();
        int mins  = getTimeToMakeMins();

        String minsString = mins + "";
        if(minsString.length() == 1)
        {
            minsString = "0"+minsString; //make minutes in the format (xx) in case it took a single digit number of mins
        }

        return hours + "h " + minsString + "m";
    }

    public void setTimeToMakeHrs(int hours)
    {
        timeToMakeHrs = hours;
    }

    public void setTimeToMakeMins(int mins)
    {
        timeToMakeMins = mins;
    }

    public Difficulty getDifficulty()
    {
        return difficulty;
    }

    public String getDifficultyString()
    {
        String result;
        Difficulty difficulty = getDifficulty();
        switch(difficulty)
        {
            case MASTER_CHEF:
                result = "Master Chef";
                break;

            case EXPERIENCED:
                result = "Experienced";
                break;

            case AMATEUR:
                result = "Amateur";
                break;

            case BEGINNER:
                result = "Beginner";
                break;

            default:
                result = "Not rated";
        }
        return "Difficulty: " + result;
    }

    public void setDifficulty(Difficulty difficulty)
    {
        this.difficulty = difficulty;
    }

    public Quality getQuality()
    {
        return quality;
    }

    public String getQualityString()
    {
        String result;
        Quality quality = getQuality();
        switch(quality)
        {
            case TASTY:
                result = "Tasty";
                break;

            case HEAVENLY:
                result = "Heavenly";
                break;

            case DELICIOUS:
                result = "Delicious";
                break;

            case HORRIBLE:
                result = "Horrible";
                break;

            default:
                result = "Not rated";
        }
        return "Taste: " + result;
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

    public String getRatingString()
    {
        float rating = getRating();
        String result;
        if(rating == 0.0f)
        {
            result = "-";
        }

        else
        {
            result = Float.toString(rating);
        }

        return result + "/5";
    }

    public void rate(float userRating)
    {
        rating.add(userRating);
    }

    public int getId()
    {
        return this.recipeID;
    }
}
