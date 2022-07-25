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

        if(type.equals("suprise"))
        {
            Random random = new Random();
            int listSize = recipeList.size();
            //add three random recipes to display list
            displayList.add( recipeList.get(random.nextInt(listSize)));
            displayList.add( recipeList.get(random.nextInt(listSize)));
            displayList.add( recipeList.get(random.nextInt(listSize)));

        }
        return displayList;
    }

}
