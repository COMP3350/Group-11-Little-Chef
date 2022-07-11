package comp3350.littlechef.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

// CLASS: DataAccessStub.java
//
//
// REMARKS: This is the access stub database that will hold the Recipes.
//
//-----------------------------------------
public class DataAccessStub implements DataAccess
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
    private boolean success = false;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        this(Main.dbName);
    }

    public boolean open(String dbName)
    {
        Recipe recipe;

        recipes = new ArrayList<Recipe>();
        String instruction;
        String subInstruction;

        validateName(dbName);
        dbName = dbName.trim();

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

        recipes.add(recipe);

        recipe = new Recipe("Pancakes");
        recipe.addIngredient(new Ingredient("All-purpose Flour", Unit.CUP, 1.5));
        recipe.addIngredient(new Ingredient("Baking Powder", Unit.TSP, 3.5));
        recipe.addIngredient(new Ingredient("Salt", Unit.TSP, 0.25));
        recipe.addIngredient(new Ingredient("White Sugar", Unit.TBSP, 1));
        recipe.addIngredient(new Ingredient("Milk", Unit.CUP, 1.25));
        recipe.addIngredient(new Ingredient("Egg", Unit.QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Melted Butter", Unit.TBSP, 2));
        recipes.add(recipe);

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
        recipes.add(recipe);

        recipe = new Recipe("Chicken Wrap");
        recipe.addIngredient(new Ingredient("Grilled Chicken Breasts copped", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Ranch Dressing", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("Mozzarella Cheese", Unit.CUP, 0.5));
        recipe.addIngredient(new Ingredient("Cilantro", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("8 inch tortillas", Unit.ML, 4));
        recipes.add(recipe);

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
        recipes.add(recipe);

        recipe = new Recipe("Chocolate Cip Cookies");
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
        recipes.add(recipe);

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
        recipes.add(recipe);

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
        recipes.add(recipe);

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
        recipes.add(recipe);

        success = true;
        return success;
    }

    public boolean close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
        success = true;

        return success;
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

    private void validateName(String name)
    {
        if(name == null)
        {
            throw new NullPointerException("Name cannot be null.");
        }

        name = name.trim();

        if(name.length() == 0)
        {
            throw new IllegalArgumentException("Name cannot be an empty String.");
        }
    }
}
