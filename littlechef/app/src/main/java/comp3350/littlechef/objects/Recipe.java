package comp3350.littlechef.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

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
    private ArrayList<Double> difficultyRatings; //for each time that this recipe is rated stores difficulty rating
    private ArrayList<Double> tasteRatings; //for each time that this recipe is rated stores taste rating

    //constructors
    public Recipe(int recipeID)
    {
        this.recipeID = recipeID;
        this.name = null;
        this.cookingTimes = new ArrayList<Integer>();
        ingredients = new ArrayList<Ingredient>();
        instructions = new ArrayList<String[]>();

        difficultyRatings = new ArrayList<Double>();
        tasteRatings = new ArrayList<Double>();
    }

    public Recipe(String name)
    {
       validateName(name);

        this.name = name.trim();
        this.recipeID = nextID++;
        this.cookingTimes = new ArrayList<Integer>();
        ingredients = new ArrayList<Ingredient>();
        instructions = new ArrayList<String[]>();

        difficultyRatings = new ArrayList<Double>();
        tasteRatings = new ArrayList<Double>();

    }

    //private methods
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        validateName(name);
        this.name = name.trim();
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
        if(instruction != null && subInstruction != null)
        {
            if (!instruction.equals(subInstruction))
            {
                instructions.add(new String[]{instruction, subInstruction});
            }
        }
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
        if (totalSeconds >= 0)
        cookingTimes.add(totalSeconds);
    }

    public ArrayList<Double> getDifficultyRatingsList()
    {
        return difficultyRatings;
    }

    public ArrayList<Double> getTasteRatingsList()
    {
        return tasteRatings;
    }

    public String getDifficultyRating()
    {
        double rating = calcAverage(difficultyRatings);
        String result = "Difficulty: ";
        if(rating == 0.0)
        {
            result += "-";
        }

        else
        {
            result += String.format(Locale.CANADA,"%.2f",rating);
        }

        return result;
    }
    public double getDifficultyRatingDouble()
    {
        return calcAverage(difficultyRatings);
    }


    public void addDifficultyRating(double rating)
    {
        if(rating >= 0)
        difficultyRatings.add(rating);
    }

    public String getTasteRating()
    {
        double rating = calcAverage(tasteRatings);
        String result = "Taste: ";
        if(rating == 0.0)
        {
            result += "-";
        }

        else
        {
            result += String.format(Locale.CANADA,"%.2f",rating);
        }

        return result;
    }

    public void addTasteRating(double rating)
    {
        if(rating >= 0)
        tasteRatings.add(rating);
    }

    //returns average rating, which an average of difficulty and taste ratings
    public double getRating()
    {
        double rating;
        String result;
        double averageDifficulty = calcAverage(difficultyRatings);
        double averageTaste = calcAverage(tasteRatings);

        rating = (averageDifficulty+averageTaste)/2;
        result = String.format(Locale.CANADA, "%.2f", rating);
        return Double.parseDouble(result);

    }

    public String getRatingString()
    {
        double rating = getRating();
        String result;
        if(rating == 0.0f)
        {
            result = "-";
        }

        else
        {
            result = String.format(Locale.CANADA,"%.2f",rating);
        }

        return  result + "/5";
    }

    public int getId()
    {
        return this.recipeID;
    }

    public void setId(int id) {this.recipeID = id;}

    public static void resetID()
    {
        nextID = 0;
    }

    public boolean equals(Object object)
    {
        boolean result;
        Recipe recipe;

        result = false;

        if (object instanceof Recipe)
        {
            recipe = (Recipe) object;
            if (recipe.recipeID == this.recipeID)
            {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString()
    {
        return name + ", " + getRatingString() + ", " + getTasteRating()  + ", " + getDifficultyRating();
    }

    private double calcAverage(ArrayList<Double> ratingVals)
    {
        double sum = 0.0;
        if(ratingVals.size()>0)
        {
            for(int i = 0; i<ratingVals.size(); i++)
            {
                sum+=ratingVals.get(i);
            }
            return sum/ratingVals.size();
        }
        return 0.0;

    }

    private void validateName(String name)
    {
        if(name == null)
        {
            throw new NullPointerException("Recipe name cannot be null.");
        }

        name = name.trim();

        if(name.length() == 0 || (name.length() == 1 && name.charAt(0) == ' '))
        {
            throw new IllegalArgumentException("Recipe name cannot be an empty String.");
        }
    }

}
