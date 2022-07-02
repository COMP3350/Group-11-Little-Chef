package comp3350.littlechef.objects;

import java.io.Serializable;
import java.util.ArrayList;

import comp3350.littlechef.business.TimeRecipe;

// CLASS: Recipe.java
//
//
// REMARKS: This class is to create a recipe with a list of ingredients and steps to make it.
//
//-----------------------------------------
public class Recipe implements Serializable
{
    private static int nextID = 0;

    //instance vars
    private String name;
    private int recipeID;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String[]> instructions; //steps(instructions) to make the recipe

    //rating criteria
    private ArrayList<Integer> cookingTimes; //for each time that this recipe was cooked, contains the total seconds of cooking time
    private Difficulty difficulty;
    private Quality quality; //same as for difficulty
    private ArrayList<Float> rating; //calculate the average similar to gpa calculation, from 0 to 5(can make "animated 5 stars that fill up the color later")

    //constructors
    public Recipe(int recipeID)
    {
        this.recipeID = recipeID;
        this.name = "null";
        this.cookingTimes = new ArrayList<Integer>();
        ingredients = new ArrayList<Ingredient>();
        instructions = new ArrayList<String[]>();

        difficulty = Difficulty.NOT_RATED;
        quality = Quality.NOT_RATED;
        rating = new ArrayList<Float>();
    }

    public Recipe(String name, int timeToMakeHrs, int timeToMakeMins)
    {
        this.name = name;
        this.recipeID = nextID++;
        this.cookingTimes = new ArrayList<Integer>();
        this.cookingTimes = new ArrayList<Integer>();
        ingredients = new ArrayList<Ingredient>();
        instructions = new ArrayList<String[]>();

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

    public ArrayList<String[]> getInstructions()
    {
        return instructions;
    }

    public void addInstructions(String instruction, String subInstruction)
    {
        instructions.add(new String[]{instruction, subInstruction});
    }

    public ArrayList<Integer> getCookingTimes()
    {
        return cookingTimes;
    }

    public String getAverageCookingTime()
    {
        String result = "Time: ";
        int average;
        int sum = 0;

        if(cookingTimes.size() == 0)
        {
            result += "Not cooked";
        }

        else
        {
            for(int i = 0; i < cookingTimes.size(); i++)
            {
                sum += cookingTimes.get(i);
            }

            average = sum/cookingTimes.size();
            //returns average total of seconds it take to cook a recipe
            result += TimeRecipe.totalSecondsToString(average,true);
        }

        return result;
    }

    public void addCookingTime(int totalSeconds)
    {
        cookingTimes.add(totalSeconds);
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
