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
        recipes = dataAccess.getRecipeRandom(new Recipe(recipeID));
        if (recipes.size()==1)
        {
            recipe = (Recipe) recipes.get(0);
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
        dataAccess.resetDatabase();
        addDefaultData();
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
        //adding instructions
        instruction = "Whisk banana, eggs, almond flour, almond butter, vanilla extract, cinnamon, baking soda, and baking powder together in a bowl until batter is smooth.";
        subInstruction = "";
        recipe.addInstructions(instruction,subInstruction);

        instruction = "Heat olive oil on a griddle or skillet over medium-high heat. Drop batter by large spoonfuls onto the griddle and cook until bubbles form and the edges are dry, 3 to 4 minutes. Flip and cook until browned on the other side, 2 to 3 minutes. Repeat with remaining batter.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);
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

        //adding instructions
        instruction = "Place the ground beef in a large pot and throw in the garlic. ";
        subInstruction = "Cook over medium heat until browned. Drain off the excess fat, and then pour in the tomato sauce, chili powder, cumin, oregano, salt and cayenne. " +
                "Stir together well, cover, and then reduce the heat to low. Simmer for 1 hour, stirring occasionally. " +
                "If the mixture becomes overly dry, add 1/2 cup water at a time as needed. ";
        recipe.addInstructions(instruction,subInstruction);

        instruction = "After an hour, place the masa harina in a small bowl.";
        subInstruction = "Add 1/2 cup water and stir together with a fork. " +
                "Dump the masa mixture into the chili. Stir together well, and then taste and adjust the seasonings. " +
                "Add more masa paste and/or water to get the chili to your preferred consistency, or to add more corn flavor. " +
                "Add the beans and simmer for 10 minutes. Serve with shredded Cheddar, chopped onions, tortilla chips and lime wedges.";
        recipe.addInstructions(instruction, subInstruction);
        insertRecipe(recipe);

        recipe = new Recipe("Chicken Wrap");
        recipe.addIngredient(new Ingredient("Grilled Chicken Breasts copped", Unit.CUP, 2));
        recipe.addIngredient(new Ingredient("Ranch Dressing", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("Mozzarella Cheese", Unit.CUP, 0.5));
        recipe.addIngredient(new Ingredient("Cilantro", Unit.CUP, 0.25));
        recipe.addIngredient(new Ingredient("8 inch tortillas", Unit.ML, 4));

        //adding instructions
        instruction = "Lay tortillas on a clean flat surface. Place about 1/2 cup chicken, 1 tablespoon ranch, 2 tablespoons of cheese, and 1 tablespoon of minced cilantro on each tortilla. Fold tightly to form a burrito shape. ";
        subInstruction = "";
        recipe.addInstructions(instruction,subInstruction);

        instruction = "Heat a heavy-duty pan or grill to medium heat. Coat with a light layer or oil or cooking spray and cook wraps for 1-2 minutes on each side or until the tortilla is crispy and golden. Remove from heat, slice in half and serve immediately.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);
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

        //adding instructions
        instruction = "Proof the yeast";
        subInstruction = "Place the warm water in the large bowl of a heavy duty stand mixer. Sprinkle the yeast over the warm water and let it sit for 5 minutes until the yeast is dissolved.\n" +
                "\n" +
                "After 5 minutes stir if the yeast hasn't dissolved completely. The yeast should begin to foam or bloom, indicating that the yeast is still active and alive.\n" +
                "\n" +
                "(Note that if you are using \"instant yeast\" instead of \"active yeast\", no proofing is required. Just add to the flour in the next step.)";
        recipe.addInstructions(instruction,subInstruction);

        instruction = "Make and knead the pizza dough";
        subInstruction = "Add the flour, salt, sugar, and olive oil, and using the mixing paddle attachment, mix on low speed for a minute. Then replace the mixing paddle with the dough hook attachment.\n" +
                "\n" +
                "Knead the pizza dough on low to medium speed using the dough hook about 7-10 minutes.\n" +
                "\n" +
                "If you don't have a mixer, you can mix the ingredients together and knead them by hand.\n" +
                "\n" +
                "The dough should be a little sticky, or tacky to the touch. If it's too wet, sprinkle in a little more flour.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Let the dough rise";
        subInstruction = "Spread a thin layer of olive oil over the inside of a large bowl. Place the pizza dough in the bowl and turn it around so that it gets coated with the oil.\n" +
                "\n" +
                "At this point you can choose how long you want the dough to ferment and rise. A slow fermentation (24 hours in the fridge) will result in more complex flavors in the dough. A quick fermentation (1 1/2 hours in a warm place) will allow the dough to rise sufficiently to work with.\n" +
                "\n" +
                "Cover the dough with plastic wrap.\n" +
                "\n" +
                "For a quick rise, place the dough in a warm place (75°F to 85°F) for 1 1/2 hours.\n" +
                "\n" +
                "For a medium rise, place the dough in a regular room temperature place (your kitchen counter will do fine) for 8 hours. For a longer rise, chill the dough in the refrigerator for 24 hours (no more than 48 hours).\n" +
                "\n" +
                "The longer the rise (to a point) the better the flavor the crust will have.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Make-Ahead and Freezing Instructions For Dough";
        subInstruction = "After the pizza dough has risen, you can freeze it to use later. Divide the dough in half (or the portion sizes you will be using to make your pizzas). Place on parchment paper or a lightly floured dish and place, uncovered, in the freezer for 15 to 20 minutes. Then remove from the freezer, and place in individual freezer bags, removing as much air as you can from the bags. Return to the freezer and store for up to 3 months.\n" +
                "\n" +
                "Thaw the pizza dough in the refrigerator overnight or for 5 to 6 hours. Then let the dough sit at room temperature for 30 minutes before stretching it out in the next steps.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Preheat the pizza stone (or pizza pan or baking sheet)";
        subInstruction = "Place a pizza stone on a rack in the lower third of your oven. Preheat the oven to 475°F for at least 30 minutes, preferably an hour. If you don't have a pizza stone, you can use a pizza pan or a thick baking sheet; you need something that will not warp at high temperatures.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Divide the dough into two balls";
        subInstruction = "Remove the plastic cover from the dough. Dust your hands with flour and push the dough down so it deflates a bit. Divide the dough in half.\n" +
                "\n" +
                "Form two round balls of dough. Place each in its own bowl, cover with plastic and let sit for 15 minutes (or up to 2 hours).";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Prep the toppings";
        subInstruction = "Prepare your desired toppings. Note that you are not going to want to load up each pizza with a lot of toppings as the crust will end up not crisp that way.\n" +
                "\n" +
                "About a third a cup each of tomato sauce and cheese would be sufficient for one pizza. One to two mushrooms thinly sliced will cover a pizza.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Flatten the dough ball, and stretch out into a round";
        subInstruction = "Working one ball of dough at a time, take one ball of dough and flatten it with your hands on a lightly floured work surface.\n" +
                "\n" +
                "Starting at the center and working outwards, use your fingertips to press the dough to 1/2-inch thick. Turn and stretch the dough until it will not stretch further.\n" +
                "\n" +
                "Let the dough relax 5 minutes and then continue to stretch it until it reaches the desired diameter—10 to 12 inches.\n" +
                "\n" +
                "Treat the dough gently!\n" +
                "\n" +
                "You can also hold up the edges of the dough with your fingers, letting the dough hang and stretch, while working around the edges of the dough.\n" +
                "\n" +
                "If a hole appears in your dough, place the dough on a floured surface and push the dough back together to seal the hole.\n" +
                "\n" +
                "Use your palm to flatten the edge of the dough where it is thicker. Pinch the edges if you want to form a lip.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Brush the dough top with olive oil";
        subInstruction = "Use your fingertips to press down and make dents along the surface of the dough to prevent bubbling. Brush the top of the dough with olive oil (to prevent it from getting soggy from the toppings). Let rest another 10 to 15 minutes.\n" +
                "\n" +
                "Repeat with the second ball of dough.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Sprinkle the pizza peel with corn meal, put flattened dough on top";
        subInstruction = "Lightly sprinkle your pizza peel (or flat baking sheet) with cornmeal. (The corn meal will act as little ball bearings to help move the pizza from the pizza peel into the oven.)\n" +
                "\n" +
                "Transfer one prepared flattened dough to the pizza peel.\n" +
                "\n" +
                "If the dough has lost its shape in the transfer, lightly shape it to the desired dimensions.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Spread with tomato sauce and sprinkle with toppings";
        subInstruction = "Spoon on the tomato sauce, sprinkle with cheese, and place your desired toppings on the pizza. Be careful not to overload the pizza with too many toppings, or your pizza will be soggy.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Sprinkle the cornmeal on pizza stone, slide pizza onto pizza stone in oven";
        subInstruction = "Sprinkle some cornmeal on the baking stone in the oven (watch your hands, the oven is hot!). Gently shake the peel to see if the dough will easily slide, if not, gently lift up the edges of the pizza and add a bit more cornmeal.\n" +
                "\n" +
                "Slide the pizza off of the peel and onto the baking stone in the oven.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Bake";
        subInstruction = "Bake pizza in the 475°F oven, one at a time, until the crust is browned and the cheese is golden, about 10 to 15 minutes. If you want, toward the end of the cooking time you can sprinkle on a little more cheese.";
        recipe.addInstructions(instruction, subInstruction);
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

        //adding instructions
        instruction = "Mix the dry ingredients";
        subInstruction = "In a medium mixing bowl, combine the flour, milk powder, salt, and baking soda and whisk to mix. Set aside.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Beat the sugars and the butter";
        subInstruction = "With an electric mixer or in a stand mixer with a beater attachment, beat together the granulated sugar, brown sugar, and softened butter on medium speed until combined, 30 to 60 seconds. Pick out any large pebbles of hard brown sugar that you see. Scrape down the sides of the bowl and the beaters";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Mix in the eggs and vanilla";
        subInstruction = "Add the two eggs and vanilla to the butter-sugar mixture and beat at medium speed until no clumps or streaks remain, 10 to 20 seconds. Scrape down the sides of the bowl and the beaters.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Mix in the flour mixture";
        subInstruction = "Add the flour mixture all at once. Beat on low speed just until no more dry streaks of flour are visible, 20 to 40 seconds. You may still see some flecks of milk powder -- this is fine.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Mix in the chocolate chips";
        subInstruction = "Add all of the chocolate chips and beat on low speed for just a few seconds until the chips are evenly incorporated.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Chill the dough";
        subInstruction = "Scrape down the sides of the bowl and the beater. Cover the bowl and refrigerate for at least 30 minutes or up to three days. (Or freeze in individual scoops for up to 3 months.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Heat the oven to 375F";
        subInstruction = "Place a rack in the middle of the oven. Line two baking sheet with silicon baking mats or parchment.\n" +
                "\n" +
                "I usually bake my cookies one sheet at a time to help them bake evenly. If you prefer, or are crunched for time, bake two sheets at a time with one sheet in the upper third of the oven and the other in the lower third of the oven.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Scoop the dough";
        subInstruction = "Use a medium cookie scoop (or a well-mounded tablespoon measure) to scoop the dough out onto one of the baking sheet. Space the cookies about 2 inches apart.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Bake the cookies for 10 to 12 minutes";
        subInstruction = "While the first batch bakes, scoop the cookies for the next batch.\n" +
                "\n" +
                "The cookies are done when they look slightly puffed in the middle and are starting to turn toasty at the edges.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Cool the cookies";
        subInstruction = "Cool the cookies on the baking sheet for about 5 minutes, or until the puffed middles collapse and the cookies have firmed a little. Transfer the cookies to a wire cooling rack to cool completely.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Continue baking cookies in batches until all the cookie dough has been used.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Enjoy";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);
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

        instruction = "To make the dough.";
        subInstruction = "Mix together the flour and salt. Add the egg to the flour and combine. The dough will be quite clumpy at this stage.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Work in the sour cream and soft butter until the dough comes together in a slightly rough, slightly sticky ball.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Using just your fingertips, knead and fold the dough without adding additional flour until the dough becomes less sticky but still quite moist.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Wrap the dough well in plastic wrap and refrigerate for 30 to 60 minutes, or up to 48 hours.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "To make the filling.";
        subInstruction = "Combine the warm mashed potato and cheese. Stir and mash until the cheese is melted and the filling is cool to the touch. Taste and adjust the seasonings with salt and pepper.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "To fill the pierogi";
        subInstruction = "Roll half the dough 1/8\" thick. Use a 2\" round cutter to cut circles of dough. Repeat with the other half of the dough. Save the scraps; these can be snipped into small pieces and added to simmering soups.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Place 1 1/2 teaspoons of filling on each round of dough. Gently fold the dough over, forming a pocket around the filling. Pinch the edges of the pierogi to seal, then seal again with the tines of a fork.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "At this point the pierogi can be frozen for up to 4 weeks, or refrigerated overnight, or cooked in a large stockpot of boiling salted water. Only cook about 10 pierogi at a time, so that they have room to float without sticking. When the pierogi float, they're done. The time will vary depending on if they're fresh or frozen.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Sauté the shallots or onion in the butter in a large skillet until the onion begins to brown. Add the drained pierogi and cook until browned and crisped. Serve hot with additional sour cream, applesauce, or other condiments.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);
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

        //adding the instructions
        instruction = "Make the Dough.";
        subInstruction = "Prepare the dough (per the recipe below) and gently knead a little bit. Do not overwork the dough and be sure to allow it to rest.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Make the Filling.";
        subInstruction = "Cook the potatoes and cook onions in butter.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Mash and combine with shredded cheddar cheese. Allow to cool.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Roll the dough into circles 1/8″ thick and 3″ in diameter.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Fill each circle with the mashed potato filling and press the edges to seal.";
        subInstruction = "";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Cook or Freeze.";
        subInstruction = "Cook the pierogies according to the instructions below.";
        recipe.addInstructions(instruction, subInstruction);
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

        //add recipes
        instruction = "Cook the farro.";
        subInstruction = "Cook the farro in 3 cups of water or vegetable stock according to these instructions until it is chewy and tender. (Cooking time will vary depending on what type of farro you use.) Drain the farro in a fine-mesh strainer and add it to a large mixing bowl.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Make the dressing.";
        subInstruction = "Meanwhile, whisk all ingredients together in a small bowl (or shake in a covered mason jar) until completely combined. Set aside until ready to use.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Pan-grill the halloumi.";
        subInstruction = "Meanwhile, heat the olive oil in a large grill pan or sauté pan over high heat. Add however many halloumi strips that you can fit in an even layer, then cook for about 30 seconds per side or until the cheese is lightly browned. Transfer the halloumi to the large mixing bowl with the farro, then repeat with the remaining strips until all of the halloumi has been cooked.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Toss the salad.";
        subInstruction = "Add the remaining ingredients (arugula, cucumber, red onion, mint, Kalamata olives and pine nuts) to the large mixing bowl. Drizzle evenly with the dressing, and then toss until the salad is completely combined.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Season.";
        subInstruction = "Taste and season the salad with extra salt, black pepper and/or lemon juice, if needed.";
        recipe.addInstructions(instruction, subInstruction);

        instruction = "Serve.";
        subInstruction = "Serve immediately and enjoy!";
        recipe.addInstructions(instruction, subInstruction);
        insertRecipe(recipe);
    }
}
