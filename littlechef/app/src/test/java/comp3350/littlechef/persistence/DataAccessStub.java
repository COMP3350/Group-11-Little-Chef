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

    private final Unit PINCH = Unit.PINCH;
    private final Unit TEASPOON = Unit.TSP;
    private final Unit TABLESPOON = Unit.TBSP;
    private final Unit CUP = Unit.CUP;
    private final Unit MILLILITER = Unit.ML;
    private final Unit GRAM = Unit.G;
    private final Unit QUANTITY = Unit.QUANTITY;

    private String dbName;
    private String dbType = "stub";
    private boolean connectionOpen;
    
    private ArrayList<Recipe> recipes;
    private boolean success = false;
    private String result; 
    
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
        success = false;
        validateName(dbName);
        addDefaultRecipes();
        connectionOpen = true;
        
        if(!recipes.isEmpty())
        {
            success = true;
        }

        return success;
    }

    public boolean close()
    {
        success = false;
        if(connectionOpen)
        {
            success = true;
            connectionOpen = false;
        }
        return success;
    }

    public String insertRecipe(Recipe currentRecipe)
    {
        result = null;
        
        if(connectionOpen)
        {
            try
            {
                recipes.add(currentRecipe);
            }
            catch(Exception e)
            {
                result = e.getMessage();
            }
        }

        return result;
    }

    public String updateRecipe(Recipe currentRecipe)
    {
        int index;

        result = null;

        if(connectionOpen) {

            try
            {
                index = recipes.indexOf(currentRecipe);
                if (index >= 0)
                {
                    recipes.set(index, currentRecipe);
                }
            }
            catch (Exception e)
            {
                result = e.getMessage();
            }
        }
        return result;
    }

    public String getRecipeSequential(List<Recipe> recipeResult)
    {
        result = null;

        if(connectionOpen)
        {
            try
            {
                recipeResult.addAll(recipes);
            }
            catch (Exception e)
            {
                result = e.getMessage();
            }
        }

        return result;
    }

    public ArrayList<Recipe> getRecipeRandom(Recipe currentRecipe)
    {
        ArrayList<Recipe> newRecipes = null;
        int index;

        if(connectionOpen)
        {
            newRecipes = new ArrayList<Recipe>();
            index = recipes.indexOf(currentRecipe);
            if (index >= 0)
            {
                newRecipes.add(recipes.get(index));
            }
        }
        return newRecipes;
    }

    public String deleteRecipe(Recipe currentRecipe)
    {
        int index;

        result = null;

        if(connectionOpen) {
            try
            {
                index = recipes.indexOf(currentRecipe);
                if (index >= 0) {
                    recipes.remove(index);
                }
            }
            catch (Exception e)
            {
                result = e.getMessage();
            }
        }

        return result;
    }

    public String resetDatabase()
    {
        result = null;

        if(connectionOpen) {
            recipes.clear();
            addDefaultRecipes();

            if (recipes.isEmpty()) {
                result = "fail";
            }
        }

        return result;
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

    private void addDefaultRecipes()
    {
        Recipe recipe;

        recipes = new ArrayList<Recipe>();
        String instruction;
        String subInstruction;

        recipe = new Recipe("Guacamole");
        recipe.addIngredient(new Ingredient("Ripe avocados", QUANTITY, 2));
        recipe.addIngredient(new Ingredient("Kosher salt", TEASPOON, 0.25));
        recipe.addIngredient(new Ingredient("Fresh Lime or Lemon Juice", TABLESPOON, 1));
        recipe.addIngredient(new Ingredient("Minced Red Onion", TABLESPOON, 4));
        recipe.addIngredient(new Ingredient("Jalapeno chillis", QUANTITY, 2));
        recipe.addIngredient(new Ingredient("Cilantro", TABLESPOON , 2));
        recipe.addIngredient(new Ingredient("Black Pepper", PINCH , 1));
        recipe.addIngredient(new Ingredient("Ripe Tomato", QUANTITY, 0.5));

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
        recipe.addIngredient(new Ingredient("All-purpose Flour", CUP, 1.5));
        recipe.addIngredient(new Ingredient("Baking Powder", TEASPOON, 3.5));
        recipe.addIngredient(new Ingredient("Salt", TEASPOON, 0.25));
        recipe.addIngredient(new Ingredient("White Sugar", TABLESPOON, 1));
        recipe.addIngredient(new Ingredient("Milk", CUP, 1.25));
        recipe.addIngredient(new Ingredient("Egg", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Melted Butter", TABLESPOON, 2));
        recipes.add(recipe);

        recipe = new Recipe("Chili");
        recipe.addIngredient(new Ingredient("Olive Oil", TABLESPOON, 1));
        recipe.addIngredient(new Ingredient("Yellow onion - diced", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Lean Ground Beaf", GRAM, 453));
        recipe.addIngredient(new Ingredient("Chili Powder", TABLESPOON, 2.5));
        recipe.addIngredient(new Ingredient("Ground Cumin", TABLESPOON, 2));
        recipe.addIngredient(new Ingredient("Granulated Sugar", TABLESPOON, 2));
        recipe.addIngredient(new Ingredient("Tomato Paste", TABLESPOON, 1));
        recipe.addIngredient(new Ingredient("Garlic Powder", TABLESPOON, 1));
        recipe.addIngredient(new Ingredient("Salt", TEASPOON, 1.5));
        recipe.addIngredient(new Ingredient("Ground Black Pepper", TEASPOON, 0.5));
        recipe.addIngredient(new Ingredient("Ground Cayenne Pepper", TEASPOON, 0.25));
        recipe.addIngredient(new Ingredient("Beef Broth", CUP, 1.5));
        recipe.addIngredient(new Ingredient("Can of Diced Tomatoes", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Can Red Kidney, drained and rinsed", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Can of Tomato Sauce", QUANTITY, 1));
        recipes.add(recipe);

        recipe = new Recipe("Chicken Wrap");
        recipe.addIngredient(new Ingredient("Grilled Chicken Breasts copped", CUP, 2));
        recipe.addIngredient(new Ingredient("Ranch Dressing", CUP, 0.25));
        recipe.addIngredient(new Ingredient("Mozzarella Cheese", CUP, 0.5));
        recipe.addIngredient(new Ingredient("Cilantro", CUP, 0.25));
        recipe.addIngredient(new Ingredient("8 inch tortillas", MILLILITER, 4));
        recipes.add(recipe);

        recipe = new Recipe("Pizza");
        recipe.addIngredient(new Ingredient("Active Dry Yeast", TABLESPOON, 0.5));
        recipe.addIngredient(new Ingredient("Sugar", TEASPOON, 1));
        recipe.addIngredient(new Ingredient("Warm Water", CUP, 1.25));
        recipe.addIngredient(new Ingredient("Canola Oil", CUP, 0.25));
        recipe.addIngredient(new Ingredient("Salt", TEASPOON, 1));
        recipe.addIngredient(new Ingredient("All-purpose Flour", CUP, 4));
        recipe.addIngredient(new Ingredient("Small Onion", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Can of tomato sauce", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Dried Oregano", TEASPOON, 3));
        recipe.addIngredient(new Ingredient("Dried Basil", TEASPOON, 1));
        recipe.addIngredient(new Ingredient("Medium Green Pepper", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Shredded Part-skim Mozzarella Cheese", CUP, 2));
        recipes.add(recipe);

        recipe = new Recipe("Chocolate Cip Cookies");
        recipe.addIngredient(new Ingredient("Softened Butter", CUP, 1));
        recipe.addIngredient(new Ingredient("White Sugar", CUP, 1));
        recipe.addIngredient(new Ingredient("Packed Brown Sugar", CUP, 1));
        recipe.addIngredient(new Ingredient("Eggs", QUANTITY, 2));
        recipe.addIngredient(new Ingredient("Vanilla Extract", TEASPOON, 1));
        recipe.addIngredient(new Ingredient("Baking Soda", TEASPOON, 1));
        recipe.addIngredient(new Ingredient("Hot Water", TEASPOON, 2));
        recipe.addIngredient(new Ingredient("Salt", TEASPOON, 0.5));
        recipe.addIngredient(new Ingredient("All-Purpose Flour", CUP, 3));
        recipe.addIngredient(new Ingredient("Semisweet Chocolate Chips", CUP, 2));
        recipe.addIngredient(new Ingredient("Chopped Walnuts", CUP, 1));
        recipes.add(recipe);

        recipe = new Recipe("Perogies");
        recipe.addIngredient(new Ingredient("All-Purpose Flour", CUP, 2));
        recipe.addIngredient(new Ingredient("Salt", TEASPOON, 1));
        recipe.addIngredient(new Ingredient("Beaten Egg", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Cold Water", CUP, 0.75));
        recipe.addIngredient(new Ingredient("Baking Potatoes", QUANTITY, 5));
        recipe.addIngredient(new Ingredient("Shredded Cheese", CUP, 1));
        recipe.addIngredient(new Ingredient("Salt", PINCH, 1));
        recipe.addIngredient(new Ingredient("Pepper", PINCH, 1));
        recipe.addIngredient(new Ingredient("Jar Sauerkraut - drained, rinsed and minced", CUP, 4));
        recipe.addIngredient(new Ingredient("Sour Cream", TABLESPOON, 3));
        recipes.add(recipe);

        recipe = new Recipe("Perogies");
        recipe.addIngredient(new Ingredient("All-Purpose Flour", CUP, 2));
        recipe.addIngredient(new Ingredient("Salt", TEASPOON, 1));
        recipe.addIngredient(new Ingredient("Beaten Egg", QUANTITY, 1));
        recipe.addIngredient(new Ingredient("Cold Water", CUP, 0.75));
        recipe.addIngredient(new Ingredient("Baking Potatoes", QUANTITY, 5));
        recipe.addIngredient(new Ingredient("Shredded Cheese", CUP, 1));
        recipe.addIngredient(new Ingredient("Salt", PINCH, 1));
        recipe.addIngredient(new Ingredient("Pepper", PINCH, 1));
        recipe.addIngredient(new Ingredient("Jar Sauerkraut - drained, rinsed and minced", CUP, 4));
        recipe.addIngredient(new Ingredient("Sour Cream", TABLESPOON, 3));
        recipes.add(recipe);

        recipe = new Recipe("Grilled Halloumi Salad");
        recipe.addIngredient(new Ingredient("Halloumi Cheese, sliced into ¼ inch thick slices", GRAM, 250));
        recipe.addIngredient(new Ingredient("Packed Spring Greens", CUP, 2));
        recipe.addIngredient(new Ingredient("Chopped Cucumber", CUP, 1));
        recipe.addIngredient(new Ingredient("Chopped Pineapple", CUP, 1.5));
        recipe.addIngredient(new Ingredient("Red Onion, thinly sliced", QUANTITY, 0.2));
        recipe.addIngredient(new Ingredient("Toasted Almonds", CUP, 0.25));
        recipe.addIngredient(new Ingredient("Olive Oil", TABLESPOON, 3));
        recipe.addIngredient(new Ingredient("Lemon Juice", CUP, 0.25));
        recipe.addIngredient(new Ingredient("Cayenne Pepper", TEASPOON, 0.25));
        recipe.addIngredient(new Ingredient("Salt", TEASPOON, 0.5));
        recipes.add(recipe);
    }
}
