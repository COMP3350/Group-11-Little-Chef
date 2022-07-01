package comp3350.littlechef.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.application.Main;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
// CLASS: DataAccessStub.java
//
//
// REMARKS: This is the access stub database that will hold the Recipes.
//
//-----------------------------------------
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
        String instruction;
        String subInstruction;

        recipe = new Recipe("Guacamole", 0, 30);
        recipe.addIngredient(new Ingredient("Ripe avocados", quantity, 2));
        recipe.addIngredient(new Ingredient("Kosher salt", teaspoon, 0.25));
        recipe.addIngredient(new Ingredient("Fresh Lime or Lemon Juice", tablespoon, 1));
        recipe.addIngredient(new Ingredient("Minced Red Onion", tablespoon, 4));
        recipe.addIngredient(new Ingredient("Jalapeno chillis", quantity, 2));
        recipe.addIngredient(new Ingredient("Cilantro", tablespoon, 2));
        recipe.addIngredient(new Ingredient("Black Pepper", pinch, 1));
        recipe.addIngredient(new Ingredient("Ripe Tomato", quantity, 0.5));

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
