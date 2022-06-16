package comp3350.littlechef.business;

import java.util.List;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.application.Services;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.persistence.DataAccessStub;
// CLASS: AccessRecipes.java
//
//
// REMARKS: This class will access the database of recipes to be used for processing.
//
//-----------------------------------------
public class AccessRecipes
{
    private DataAccessStub dataAccess;
    private List<Recipe> recipes;
    private Recipe recipe;
    private int currentRecipe;

    //constructor
    public AccessRecipes()
    {
        dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
        recipes = null;
        recipe = null;
        currentRecipe = 0;
    }

    public String getRecipes(List<Recipe> recipes)
    {
        recipes.clear();
        return dataAccess.getRecipeSequential(recipes);
    }
}
