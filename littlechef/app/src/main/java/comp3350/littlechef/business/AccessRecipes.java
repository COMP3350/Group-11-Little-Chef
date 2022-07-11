package comp3350.littlechef.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.application.Services;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;
import comp3350.littlechef.persistence.DataAccess;

// CLASS: AccessRecipes.java
//
//
// REMARKS: This class will access the database of recipes to be used for processing.
//
//-----------------------------------------
public class AccessRecipes
{
    private DataAccess dataAccess;
    private List<Recipe> recipes;
    private Recipe recipe;
    private int currentRecipe;

    //constructor
    public AccessRecipes()
    {
        dataAccess = Services.getDataAccess(Main.dbName);
        recipes = null;
        recipe = null;
        currentRecipe = 0;
        resetDatabase();
    }

    public String getRecipes(List<Recipe> recipes)
    {
        recipes.clear();
        return dataAccess.getRecipeSequential(recipes);
    }

    public Recipe getSequential()
    {
        String result = null;
        if (recipes == null)
        {
            recipes = new ArrayList<Recipe>();
            result = dataAccess.getRecipeSequential(recipes);
            currentRecipe = 0;
        }
        if (currentRecipe < recipes.size())
        {
            recipe = (Recipe) recipes.get(currentRecipe);
            currentRecipe++;
        }
        else
        {
            recipes = null;
            recipe = null;
            currentRecipe = 0;
        }

        return recipe;
    }

    public Recipe getRandom(int recipeID)
    {
        recipe = null;

        recipes = dataAccess.getRecipeRandom(new Recipe(recipeID));

        if(recipes != null)
        {
            if (recipes.size() == 1) {
                recipe = (Recipe) recipes.get(0);
            }
        }

        return recipe;
    }


    public String insertRecipe(Recipe currentRecipe)
    {
        return dataAccess.insertRecipe(currentRecipe);
    }

    public String updateRecipe(Recipe currentRecipe)
    {
        return dataAccess.updateRecipe(currentRecipe);
    }

    public String deleteRecipe(Recipe currentRecipe)
    {
        return dataAccess.deleteRecipe(currentRecipe);
    }

    public void resetDatabase()
    {
        if(dataAccess.resetDatabase() == null)
        {
            Recipe.resetID();
            addDefaultData();
        }
    }

    private void addDefaultData()
    {
        Recipe recipe;

        String instruction;
        String subInstruction;

        recipe = new Recipe("Guacamole");
        recipe.addIngredient(new Ingredient("Ripe avocados", Unit.QUANTITY, 2));
        recipe.addIngredient(new Ingredient("Kosher salt", Unit.TSP, 0.25));
        recipe.addIngredient(new Ingredient("Fresh Lime or Lemon Juice", Unit.TBSP, 1));
        recipe.addIngredient(new Ingredient("Minced Red Onion", Unit.TBSP, 4));
        recipe.addIngredient(new Ingredient("Jalapeno chillis", Unit.QUANTITY, 2));
        recipe.addIngredient(new Ingredient("Cilantro", Unit.TBSP , 2));
        recipe.addIngredient(new Ingredient("Black Pepper", Unit.PINCH , 1));
        recipe.addIngredient(new Ingredient("Ripe Tomato", Unit.QUANTITY, 0.5));

        //adding instructions
        instruction = "Place eggs in a saucepan or pot and cover with cold water.";
        subInstruction = "Eggs first, then water. Why? Because if you put the eggs in afterward, they might crack as they fall to the bottom of the pan. It's no fun to learn this the hard way.";
        recipe.addInstructions(instruction,subInstruction);

        instruction = "Put pan over high heat and bring water to a rolling boil. Remove pan from heat and cover.";
        subInstruction = "How long does it take to boil an egg? Well, actually, you want the water to come just to a boil but not stay there. " +
                "Eggs exposed to high heat for a long time go through a chemical reaction that turns the yolks green. So the answer to \"How long do you boil hard boiled eggs?\" is: " +
                "pretty much not at all. Because the eggs cook in water that's not actually boiling, some people use the term \"hard-cooked\" instead of \"hard-boiled\" eggs.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Drain eggs immediately and put in a bowl filled with water and ice cubes.";
        subInstruction = "Why ice water? It cools the eggs down and prevents the green yolk problem. " +
                "(Chilled water isn't cold enough - you want cold water with lots of ice cubes floating in it.) " +
                "If you're planning to peel the eggs, crack them slightly before putting them in the ice water and let them sit for an hour for maximum ease of peeling.";
        recipe.addInstructions(instruction, subInstruction);

        insertRecipe(recipe);

        recipe = new Recipe("Pancakes");
        recipe.addIngredient(new Ingredient("All-purpose Flour", Unit.CUP, 1.5));
        recipe.addIngredient(new Ingredient("Baking Powder", Unit.TSP, 3.5));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 0.25));
        recipe.addIngredient(new Ingredient("White Sugar", Unit.TBSP, 1));
        recipe.addIngredient(new Ingredient("Milk", Unit.CUP, 1.25));
        recipe.addIngredient(new Ingredient("Egg", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Melted Butter", Unit.TBSP, 2));
        insertRecipe(recipe);

        recipe = new Recipe("Chili");
        recipe.addIngredient(new Ingredient("Olive Oil", Unit.TBSP, 1));
        recipe.addIngredient(new Ingredient("Yellow onion - diced", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Lean Ground Beaf", Unit.G, 453));
        recipe.addIngredient(new Ingredient("Chili Powder", Unit.TBSP, 2.5));
        recipe.addIngredient(new Ingredient("Ground Cumin", Unit.TBSP, 2));
        recipe.addIngredient(new Ingredient("Granulated Sugar", Unit.TBSP, 2));
        recipe.addIngredient(new Ingredient("Tomato Paste", Unit.TBSP, 1));
        recipe.addIngredient(new Ingredient("Garlic Powder", Unit.TBSP, 1));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 1.5));
        recipe.addIngredient(new Ingredient("Ground Black Pepper", Unit.TSP, 0.5));
        recipe.addIngredient(new Ingredient("Ground Cayenne Pepper", Unit.TSP, 0.25));
        recipe.addIngredient(new Ingredient("Beef Broth", Unit.CUP, 1.5));
        recipe.addIngredient(new Ingredient("Can of Diced Tomatoes", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Can Red Kidney, drained and rinsed", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Can of Tomato Sauce", Unit.QUANTITY, 1));
        insertRecipe(recipe);

        recipe = new Recipe("Chicken Wrap");
        recipe.addIngredient(new Ingredient("Grilled Chicken Breasts copped", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Ranch Dressing", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("Mozzarella Cheese", Unit.CUP, 0.5));
        recipe.addIngredient(new Ingredient("Cilantro", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("8 inch tortillas", Unit.ML, 4));
        insertRecipe(recipe);

        recipe = new Recipe("Pizza");
        recipe.addIngredient(new Ingredient("Active Dry Yeast", Unit.TBSP, 0.5));
        recipe.addIngredient(new Ingredient("Sugar", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient("Warm Water", Unit.CUP, 1.25));
        recipe.addIngredient(new Ingredient("Canola Oil", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient("All-purpose Flour", Unit.CUP, 4));
        recipe.addIngredient(new Ingredient("Small Onion", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Can of tomato sauce", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Dried Oregano", Unit.TSP, 3));
        recipe.addIngredient(new Ingredient("Dried Basil", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient("Medium Green Pepper", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Shredded Part-skim Mozzarella Cheese", Unit.CUP, 2));
        insertRecipe(recipe);

        recipe = new Recipe("Chocolate Chip Cookies");
        recipe.addIngredient(new Ingredient("Softened Butter", Unit.CUP, 1));
        recipe.addIngredient(new Ingredient("White Sugar", Unit.CUP, 1));
        recipe.addIngredient(new Ingredient("Packed Brown Sugar", Unit.CUP, 1));
        recipe.addIngredient(new Ingredient("Eggs", Unit.QUANTITY, 2));
        recipe.addIngredient(new Ingredient("Vanilla Extract", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient("Baking Soda", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient("Hot Water", Unit.TSP, 2));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 0.5));
        recipe.addIngredient(new Ingredient("All-Purpose Flour", Unit.CUP, 3));
        recipe.addIngredient(new Ingredient("Semisweet Chocolate Chips", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Chopped Walnuts", Unit.CUP, 1));
        insertRecipe(recipe);

        recipe = new Recipe("Perogies");
        recipe.addIngredient(new Ingredient("All-Purpose Flour", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient("Beaten Egg", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Cold Water", Unit.CUP, 0.75));
        recipe.addIngredient(new Ingredient("Baking Potatoes", Unit.QUANTITY, 5));
        recipe.addIngredient(new Ingredient("Shredded Cheese", Unit.CUP, 1));
        recipe.addIngredient(new Ingredient("Salt", Unit.PINCH, 1));
        recipe.addIngredient(new Ingredient("Pepper", Unit.PINCH, 1));
        recipe.addIngredient(new Ingredient("Jar Sauerkraut - drained, rinsed and minced", Unit.CUP, 4));
        recipe.addIngredient(new Ingredient("Sour Cream", Unit.TBSP, 3));
        insertRecipe(recipe);

        recipe = new Recipe("Perogies");
        recipe.addIngredient(new Ingredient("All-Purpose Flour", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 1));
        recipe.addIngredient(new Ingredient("Beaten Egg", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Cold Water", Unit.CUP, 0.75));
        recipe.addIngredient(new Ingredient("Baking Potatoes", Unit.QUANTITY, 5));
        recipe.addIngredient(new Ingredient("Shredded Cheese", Unit.CUP, 1));
        recipe.addIngredient(new Ingredient("Salt", Unit.PINCH, 1));
        recipe.addIngredient(new Ingredient("Pepper", Unit.PINCH, 1));
        recipe.addIngredient(new Ingredient("Jar Sauerkraut - drained, rinsed and minced", Unit.CUP, 4));
        recipe.addIngredient(new Ingredient("Sour Cream", Unit.TBSP, 3));
        insertRecipe(recipe);

        recipe = new Recipe("Grilled Halloumi Salad");
        recipe.addIngredient(new Ingredient("Halloumi Cheese, sliced into ¼ inch thick slices", Unit.G, 250));
        recipe.addIngredient(new Ingredient("Packed Spring Greens", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Chopped Cucumber", Unit.CUP, 1));
        recipe.addIngredient(new Ingredient("Chopped Pineapple", Unit.CUP, 1.5));
        recipe.addIngredient(new Ingredient("Red Onion, thinly sliced", Unit.QUANTITY, 0.2));
        recipe.addIngredient(new Ingredient("Toasted Almonds", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("Olive Oil", Unit.TBSP, 3));
        recipe.addIngredient(new Ingredient("Lemon Juice", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("Cayenne Pepper", Unit.TSP, 0.25));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 0.5));
        insertRecipe(recipe);
    }
}
