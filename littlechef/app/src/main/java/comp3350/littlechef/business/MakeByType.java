package comp3350.littlechef.business;

import java.util.ArrayList;
import java.util.Random;

import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.presentation.Messages;

public class MakeByType
{
    //TODO: make this a case statement?
    //displays how many recipes it will display from main list with required type
    private static final int MAX_SHOWING = 3;
    private static final int TIME_SAVING = 10; //for the time savings show recipes under 10 minutes to make
    private static final double TASTE_RATING = 4.5; //only recipes with a taste rating higher then this is shown

    //creates list based on values
    public static ArrayList<Recipe> createList(String type, ArrayList<Recipe> recipeList)
    {
        ArrayList<Recipe> displayList = new ArrayList<Recipe>();
        Random random = new Random();
        int randomIndex;
        int listSize;
        if(type.equals("surprise"))
        {
            while(displayList.size() < MAX_SHOWING && recipeList.size() > 0 )
            {
                listSize = recipeList.size(); //set recipe list size
                randomIndex = random.nextInt(listSize); //get random index with recipe list size
                displayList.add(recipeList.get(randomIndex));
                recipeList.remove(randomIndex); //remove from consideration (don't want the same recipe twice)
            }
        }
        else if(type.equals("challenge")) //recipe difficulty of 5.00
        {
            //until we get three recipes or until recipe list is empty
            while(displayList.size() < MAX_SHOWING && recipeList.size() > 0 )
            {
                listSize = recipeList.size(); //set recipe list size
                randomIndex = random.nextInt(listSize); //get random index with recipe list sizes
                Recipe recipe = recipeList.get(randomIndex);
                if(recipe.getDifficultyRatingDouble() == 5.0)
                {
                    displayList.add(recipe);
                }
                recipeList.remove(randomIndex); //remove recipe from further consideration
            }
        }
        else if(type.equals("ease"))
        {
            while(displayList.size() < MAX_SHOWING && recipeList.size() > 0 )
            {
                listSize = recipeList.size(); //set recipe list size
                randomIndex = random.nextInt(listSize); //get random index with recipe list sizes
                Recipe recipe = recipeList.get(randomIndex);
                if(recipe.getDifficultyRatingDouble() == 1.0)
                {
                    displayList.add(recipe);
                }
                recipeList.remove(randomIndex); //remove recipe from further consideration
            }
        }
        else if(type.equals("savingTime"))
        {
            while(displayList.size() < MAX_SHOWING && recipeList.size() > 0 )
            {
                listSize = recipeList.size(); //set recipe list size
                randomIndex = random.nextInt(listSize); //get random index with recipe list sizes
                Recipe recipe = recipeList.get(randomIndex);
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
                            displayList.add(recipe);
                    }
                    catch(Exception e)
                    {
                        //do nothing
                    }
                }
                recipeList.remove(randomIndex); //remove recipe from further consideration
            }//end while
        }
        else if(type.equals("taste"))
        {
            while(displayList.size() < MAX_SHOWING && recipeList.size() > 0 )
            {
                listSize = recipeList.size(); //set recipe list size
                randomIndex = random.nextInt(listSize); //get random index with recipe list sizes
                Recipe recipe = recipeList.get(randomIndex);

                try
                {
                    String[] split = recipe.getTasteRating().split(" ");
                    double tasteRating = Double.parseDouble(split[1]);

                    if(tasteRating >= TASTE_RATING)
                        displayList.add(recipe);
                }
                catch(Exception e)
                {
                    //do nothing
                }
                recipeList.remove(randomIndex); //remove recipe from further consideration
            }
        }


        return displayList;
    }

}
