package comp3350.littlechef.business;

import java.util.ArrayList;
import java.util.Random;

import comp3350.littlechef.objects.Recipe;

public class MakeByType
{
    //TODO: make this a case statement
    //creates list based on values
    public static ArrayList<Recipe> createList(String type, ArrayList<Recipe> recipeList)
    {
        ArrayList<Recipe> displayList = new ArrayList<Recipe>();
        Random random = new Random();
        int randomIndex;
        int listSize;
        if(type.equals("surprise"))
        {
            //TODO: or until no more left
            for(int i = 0; i < 3; i++)
            {
                listSize = recipeList.size(); //set recipe list size
                randomIndex = random.nextInt(listSize); //get random index with recipe list size
                displayList.add(recipeList.get(randomIndex));
                recipeList.remove(randomIndex); //remove from consideration (don't want the same recipe twice)
            }
        }
        else if(type.equals("challenge")) //recipe difficulty of 5.00
        {
            //TODO: or until no more left
            //until we get three recipes or until recipe list is empty
            while(recipeList.size() > 0)
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
        return displayList;
    }

}
