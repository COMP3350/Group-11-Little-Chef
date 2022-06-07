package comp3350.littlechef.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.objects.Recipe;

//TODO NEED TO CREATE A RATING CLASS THAT WILL HOLD ALL THE RATINGS + DATE FOR THE RATING IN THE RECIPE
public class DataAccessStub
{
    private String dbName;
    private String dbType = "stub";
    private ArrayList<Recipe> recipes;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(Main.dbName);
    }

    public void open(String dbName)
    {
        Recipe recipe;

        recipes = new ArrayList<Recipe>();

        recipe = new Recipe("Pizza", 0, 30);
        recipes.add(recipe);
        recipe = new Recipe("Mushroom Ravioli", 2, 30);
        recipes.add(recipe);
        recipe = new Recipe("Guacamole", 1,0);
        recipes.add(recipe);
        recipe = new Recipe("Pierogies", 1,30);
        recipes.add(recipe);
        recipe = new Recipe("Vermicelli", 1,30);
        recipes.add(recipe);
        recipe = new Recipe("Lasagna", 1,30);
        recipes.add(recipe);
        recipe = new Recipe("Mom's spaghetti", 1,30);
        recipes.add(recipe);
        recipe = new Recipe("Nervous Broccoli", 1,30);
        recipes.add(recipe);


        System.out.println("Opened " +dbType +" database " +dbName);
    }

    public void close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String getRecipeSequential(List<Recipe> recipeResult)
    {
        recipeResult.addAll(recipes);
        return null;
    }

    public ArrayList<Recipe> getRecipeRandom(Recipe currentRecipe)
    {
        ArrayList<Recipe> newRecipes;
        int index;

        newRecipes = new ArrayList<Recipe>();
        index = recipes.indexOf(currentRecipe);
        if (index >= 0)
        {
            newRecipes.add(recipes.get(index));
        }
        return newRecipes;
    }

    public String insertRecipe(Recipe currentRecipe)
    {
        // don't bother checking for duplicates
        recipes.add(currentRecipe);
        return null;
    }

    public String updateRecipe(Recipe currentRecipe)
    {
        int index;

        index = recipes.indexOf(currentRecipe);
        if (index >= 0)
        {
            recipes.set(index, currentRecipe);
        }
        return null;
    }

    public String deleteRecipe(Recipe currentRecipe)
    {
        int index;

        index = recipes.indexOf(currentRecipe);
        if (index >= 0)
        {
            recipes.remove(index);
        }
        return null;
    }
}
