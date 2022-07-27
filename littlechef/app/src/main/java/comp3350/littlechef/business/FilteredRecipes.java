package comp3350.littlechef.business;

import java.util.ArrayList;
import java.util.Random;

import comp3350.littlechef.objects.Recipe;

// CLASS: FilteredRecipes.java
//
//
// REMARKS: This class will return an arraylist of recipes corresponding to the type, given a recipe list
//
//-----------------------------------------

public class FilteredRecipes
{
    //displays how many recipes it will display from main list with required type
    private static final int TIME_SAVING = 10; //for the time savings show recipes under 10 minutes to make
    private static final double TASTE_RATING = 4.5; //only recipes with a taste rating higher then this is shown

    private static Random random = new Random();
    private static int listSize;
    private static int recipeIndex;
    private static Recipe recipe; 

    //creates list based on values
    public static ArrayList<Recipe> getListofRecipesByType(String type, int amountToReturn, ArrayList<Recipe> recipeList)
    {
        ArrayList<Recipe> returnList = null;

        if(recipeList != null && type != null && amountToReturn > 0)
        {
            returnList = new ArrayList<Recipe>();
            switch (type) {
                case "surprise":
                    returnList = getSurpriseRecipes(recipeList, amountToReturn);
                    break;
                case "challenge":
                    returnList = getMostDifficultRecipes(recipeList, amountToReturn);
                    break;
                case "ease":
                    returnList = getEasiestRecipes(recipeList, amountToReturn);
                    break;
                case "savingTime":
                    returnList = getFastestRecipes(recipeList, amountToReturn);
                    break;
                case "taste":
                    returnList = getTastiestRecipes(recipeList, amountToReturn);
                    break;
                default:
                    returnList = null;
            }
        }

        return returnList;
    }

    private static ArrayList<Recipe> getSurpriseRecipes(ArrayList<Recipe> recipeList, int amountToReturn)
    {
        ArrayList<Recipe> returnList = new ArrayList<Recipe>();

        while(returnList.size() < amountToReturn && recipeList.size() > 0 )
        {
            listSize = recipeList.size(); //set recipe list size
            recipeIndex = random.nextInt(listSize); //get random index with recipe list size
            returnList.add(recipeList.get(recipeIndex));
            recipeList.remove(recipeIndex); //remove from consideration (don't want the same recipe twice)
        }

        return returnList;
    }

    private static ArrayList<Recipe> getMostDifficultRecipes(ArrayList<Recipe> recipeList, int amountToReturn)
    {
        ArrayList<Recipe> returnList = new ArrayList<Recipe>();

        //until we get three recipes or until recipe list is empty
        while(returnList.size() < amountToReturn && recipeList.size() > 0 )
        {
            setRandomRecipe(recipeList);
            if(recipe.getDifficultyRatingDouble() == 5.0)
            {
                returnList.add(recipe);
            }
            recipeList.remove(recipeIndex); //remove recipe from further consideration
        }

        return returnList;
    }

    private static ArrayList<Recipe> getEasiestRecipes(ArrayList<Recipe> recipeList, int amountToReturn)
    {
        ArrayList<Recipe> returnList = new ArrayList<Recipe>();

        while(returnList.size() < amountToReturn && recipeList.size() > 0 )
        {
            setRandomRecipe(recipeList);
            if(recipe.getDifficultyRatingDouble() == 1.0)
            {
                returnList.add(recipe);
            }
            recipeList.remove(recipeIndex); //remove recipe from further consideration
        }

        return returnList;
    }

    private static ArrayList<Recipe> getFastestRecipes(ArrayList<Recipe> recipeList, int amountToReturn)
    {
        ArrayList<Recipe> returnList = new ArrayList<Recipe>();

        while(returnList.size() < amountToReturn && recipeList.size() > 0 )
        {
            setRandomRecipe(recipeList);
            //returns either not cooked or time: [time]
            String getAvg = recipe.getAverageCookingTime();

            //check if not cooked, then don't show
            if(!getAvg.equals("Time: Not cooked"))
            {
                try
                {
                    //if not cooked then get the double
                    String[] split = getAvg.split(" ");
                    String minutes = split[2];
                    minutes = minutes.substring(0, minutes.length() - 1);
                    double minutesDouble = Double.parseDouble(minutes);
                    if(minutesDouble <= TIME_SAVING)
                        returnList.add(recipe);
                }
                catch(Exception e)
                {
                    //do nothing
                }
            }
            recipeList.remove(recipeIndex); //remove recipe from further consideration
        }//end while

        return returnList;
    }

    private static ArrayList<Recipe> getTastiestRecipes(ArrayList<Recipe> recipeList, int amountToReturn)
    {
        ArrayList<Recipe> returnList = new ArrayList<Recipe>();

        while(returnList.size() < amountToReturn && recipeList.size() > 0 )
        {
            setRandomRecipe(recipeList);

            try
            {
                String[] split = recipe.getTasteRating().split(" ");
                double tasteRating = Double.parseDouble(split[1]);

                if(tasteRating >= TASTE_RATING)
                    returnList.add(recipe);
            }
            catch(Exception e)
            {
                //do nothing
            }
            recipeList.remove(recipeIndex); //remove recipe from further consideration
        }

        return returnList;
    }

    private static void setRandomRecipe(ArrayList<Recipe> recipeList)
    {
        Random random = new Random();
        
        listSize = recipeList.size(); //set recipe list size
        recipeIndex = random.nextInt(listSize); //get random index with recipe list sizes
        recipe = recipeList.get(recipeIndex);
        
    }

}
