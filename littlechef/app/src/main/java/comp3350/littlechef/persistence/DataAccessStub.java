package comp3350.littlechef.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;

//TODO NEED TO CREATE A RATING CLASS THAT WILL HOLD ALL THE RATINGS + DATE FOR THE RATING IN THE RECIPE
public class DataAccessStub
{
    private final String pinch = "pinch";
    private final String teaspoon = "tsp";
    private final String tablespoon = "tbsp";
    private final String cup = "cup";
    private final String milliliter = "ml";
    private final String gram = "g";
    private final String quantity = "quantity";

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

        recipe = new Recipe("Guacamole", 0, 30);
        recipe.addIngredient(new Ingredient("Ripe avocados", quantity, 2));
        recipe.addIngredient(new Ingredient("Kosher salt", teaspoon, 0.25));
        recipe.addIngredient(new Ingredient("Fresh Lime or Lemon Juice", tablespoon, 1));
        recipe.addIngredient(new Ingredient("Minced Red Onion", tablespoon, 4));
        recipe.addIngredient(new Ingredient("Jalapeno chillis", quantity, 2));
        recipe.addIngredient(new Ingredient("Cilantro", tablespoon, 2));
        recipe.addIngredient(new Ingredient("Black Pepper", pinch, 1));
        recipe.addIngredient(new Ingredient("Ripe Tomato", quantity, 0.5));
        recipes.add(recipe);

        recipe = new Recipe("Pancakes", 0, 30);
        recipe.addIngredient(new Ingredient("All-purpose Flour", cup, 1.5));
        recipe.addIngredient(new Ingredient("Baking Powder", teaspoon, 3.5));
        recipe.addIngredient(new Ingredient("Salt", teaspoon, 0.25));
        recipe.addIngredient(new Ingredient("White Sugar", tablespoon, 1));
        recipe.addIngredient(new Ingredient("Milk", cup, 1.25));
        recipe.addIngredient(new Ingredient("Egg", quantity, 1));
        recipe.addIngredient(new Ingredient("Melted Butter", tablespoon, 2));
        recipes.add(recipe);

        recipe = new Recipe("Chili", 2, 0);
        recipe.addIngredient(new Ingredient("Olive Oil", tablespoon, 1));
        recipe.addIngredient(new Ingredient("Yellow onion - diced", quantity, 1));
        recipe.addIngredient(new Ingredient("Lean Ground Beaf", gram, 453));
        recipe.addIngredient(new Ingredient("Chili Powder", tablespoon, 2.5));
        recipe.addIngredient(new Ingredient("Ground Cumin", tablespoon, 2));
        recipe.addIngredient(new Ingredient("Granulated Sugar", tablespoon, 2));
        recipe.addIngredient(new Ingredient("Tomato Paste", tablespoon, 1));
        recipe.addIngredient(new Ingredient("Garlic Powder", tablespoon, 1));
        recipe.addIngredient(new Ingredient("Salt", teaspoon, 1.5));
        recipe.addIngredient(new Ingredient("Ground Black Pepper", teaspoon, 0.5));
        recipe.addIngredient(new Ingredient("Ground Cayenne Pepper", teaspoon, 0.25));
        recipe.addIngredient(new Ingredient("Beef Broth", cup, 1.5));
        recipe.addIngredient(new Ingredient("Can of Diced Tomatoes", quantity, 1));
        recipe.addIngredient(new Ingredient("Can Red Kidney, drained and rinsed", quantity, 1));
        recipe.addIngredient(new Ingredient("Can of Tomato Sauce", quantity, 1));
        recipes.add(recipe);

        recipe = new Recipe("Chicken Wrap", 0, 30);
        recipe.addIngredient(new Ingredient("Grilled Chicken Breasts copped", cup, 2));
        recipe.addIngredient(new Ingredient("Ranch Dressing", cup, 0.25));
        recipe.addIngredient(new Ingredient("Mozzarella Cheese", cup, 0.5));
        recipe.addIngredient(new Ingredient("Cilantro", cup, 0.25));
        recipe.addIngredient(new Ingredient("8 inch tortillas", milliliter, 4));
        recipes.add(recipe);

        recipe = new Recipe("Pizza", 1, 0);
        recipe.addIngredient(new Ingredient("Active Dry Yeast", tablespoon, 0.5));
        recipe.addIngredient(new Ingredient("Sugar", teaspoon, 1));
        recipe.addIngredient(new Ingredient("Warm Water", cup, 1.25));
        recipe.addIngredient(new Ingredient("Canola Oil", cup, 0.25));
        recipe.addIngredient(new Ingredient("Salt", teaspoon, 1));
        recipe.addIngredient(new Ingredient("All-purpose Flour", cup, 4));
        recipe.addIngredient(new Ingredient("Small Onion", quantity, 1));
        recipe.addIngredient(new Ingredient("Can of tomato sauce", quantity, 1));
        recipe.addIngredient(new Ingredient("Dried Oregano", teaspoon, 3));
        recipe.addIngredient(new Ingredient("Dried Basil", teaspoon, 1));
        recipe.addIngredient(new Ingredient("Medium Green Pepper", quantity, 1));
        recipe.addIngredient(new Ingredient("Shredded Part-skim Mozzarella Cheese", cup, 2));
        recipes.add(recipe);

        recipe = new Recipe("Chocolate Cip Cookies", 0, 30);
        recipe.addIngredient(new Ingredient("Softened Butter", cup, 1));
        recipe.addIngredient(new Ingredient("White Sugar", cup, 1));
        recipe.addIngredient(new Ingredient("Packed Brown Sugar", cup, 1));
        recipe.addIngredient(new Ingredient("Eggs", quantity, 2));
        recipe.addIngredient(new Ingredient("Vanilla Extract", teaspoon, 1));
        recipe.addIngredient(new Ingredient("Baking Soda", teaspoon, 1));
        recipe.addIngredient(new Ingredient("Hot Water", teaspoon, 2));
        recipe.addIngredient(new Ingredient("Salt", teaspoon, 0.5));
        recipe.addIngredient(new Ingredient("All-Purpose Flour", cup, 3));
        recipe.addIngredient(new Ingredient("Semisweet Chocolate Chips", cup, 2));
        recipe.addIngredient(new Ingredient("Chopped Walnuts", cup, 1));
        recipes.add(recipe);

        recipe = new Recipe("Perogies", 0, 50);
        recipe.addIngredient(new Ingredient("All-Purpose Flour", cup, 2));
        recipe.addIngredient(new Ingredient("Salt", teaspoon, 1));
        recipe.addIngredient(new Ingredient("Beaten Egg", quantity, 1));
        recipe.addIngredient(new Ingredient("Cold Water", cup, 0.75));
        recipe.addIngredient(new Ingredient("Baking Potatoes", quantity, 5));
        recipe.addIngredient(new Ingredient("Shredded Cheese", cup, 1));
        recipe.addIngredient(new Ingredient("Salt", pinch, 1));
        recipe.addIngredient(new Ingredient("Pepper", pinch, 1));
        recipe.addIngredient(new Ingredient("Jar Sauerkraut - drained, rinsed and minced", cup, 4));
        recipe.addIngredient(new Ingredient("Sour Cream", tablespoon, 3));
        recipes.add(recipe);

        recipe = new Recipe("Perogies", 0, 50);
        recipe.addIngredient(new Ingredient("All-Purpose Flour", cup, 2));
        recipe.addIngredient(new Ingredient("Salt", teaspoon, 1));
        recipe.addIngredient(new Ingredient("Beaten Egg", quantity, 1));
        recipe.addIngredient(new Ingredient("Cold Water", cup, 0.75));
        recipe.addIngredient(new Ingredient("Baking Potatoes", quantity, 5));
        recipe.addIngredient(new Ingredient("Shredded Cheese", cup, 1));
        recipe.addIngredient(new Ingredient("Salt", pinch, 1));
        recipe.addIngredient(new Ingredient("Pepper", pinch, 1));
        recipe.addIngredient(new Ingredient("Jar Sauerkraut - drained, rinsed and minced", cup, 4));
        recipe.addIngredient(new Ingredient("Sour Cream", tablespoon, 3));
        recipes.add(recipe);

        recipe = new Recipe("Grilled Halloumi Salad", 0, 20);
        recipe.addIngredient(new Ingredient("Halloumi Cheese, sliced into ¼ inch thick slices", gram, 250));
        recipe.addIngredient(new Ingredient("Packed Spring Greens", cup, 2));
        recipe.addIngredient(new Ingredient("Chopped Cucumber", cup, 1));
        recipe.addIngredient(new Ingredient("Chopped Pineapple", cup, 1.5));
        recipe.addIngredient(new Ingredient("Red Onion, thinly sliced", quantity, 0.2));
        recipe.addIngredient(new Ingredient("Toasted Almonds", cup, 0.25));
        recipe.addIngredient(new Ingredient("Olive Oil", tablespoon, 3));
        recipe.addIngredient(new Ingredient("Lemon Juice", cup, 0.25));
        recipe.addIngredient(new Ingredient("Cayenne Pepper", teaspoon, 0.25));
        recipe.addIngredient(new Ingredient("Salt", teaspoon, 0.5));
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
