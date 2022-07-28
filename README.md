# Little Chef

**Group 11: Dylan, Brad, Kajal, Wen, Denys**

### Packages 
- application
- business 
- objects
- persistence
- presentation 

### Major Source Code Files
- application.Main.java
- application.Services.java
- business.AccessRecipes.java
- business.DecimalFractionConversion.java
- business.FilteredRecipes.java
- business.ScaleRecipe.java
- business.TimeRecipe.java
- objects.Ingredient.java
- objects.Recipe.java
- objects.Unit.java
- objects.UnitType.java
- persistence.DataAccess.java
- persistence.DataAccessObject.java
- presentation.AddIngredientActivity.java
- presentation.AddInstructionActivity.java
- presentation.AddNewRecipeFragment.java
- presentation.CookedAnotherMealActivity.java
- presentation.DetailedRecipeActivity.java
- presentation.EditInstructionsActivity.java
- presentation.EditRecipeActivity.java
- presentation.HomeActivity.java
- presentation.Messages.java
- presentation.RateRecipeActivity.java
- presentation.RecipeInstructionActivity.java
- presentation.SuggestRecipeFragment.java
- presentation.SuggestRecipesActivity.java
- presentation.ViewRecipesFragment.java

### URL TO GIT REPO
An invite link has been sent to username: Braico

### The Log
**File name**: log.txt
**Location**: saved in the private repository along with the project. All members of our team contributed to keeping the log up to date.

**The log contains the following information (in order)**
- Meeting notes and who was in attendance for all the meetings
- All the dev tasks and related information
- Brad’s activity log
- Denys’s activity log
- Dylan’s activity log
- Kajal’s activity log
- Wen’s activity log
- Design Decisions and Rationale

### Retrospective Activity 
**Filename**: Retrospective Activity.txt  
**Location**: saved in the private repository along with the project.

### Big Picture Overview
Little chef is a digital recipe book that will also recommend recipes based on your preferences. The Recipe Screen lists all the saved recipes and additional details (the recipe rating, estimated cooking time, and cooking difficulty). You can select a recipe to view the recipe's ingredients and instructions. You can adjust the serving size, and the Little Chef will update the ingredient quantities for you. Once you start cooking a given recipe, you can use the timer function to track how long that meal took to prepare. The app will use this metric to update the estimated cook time for the recipe. Once the meal is made, a user will rate the meal. Little Chef will use this rating to calculate the recipe's overall rating, which is used in the recipe suggestion screen. In the ‘suggest a recipe’ screen, you can click a button based on your preferences, and Little Chef will recommend recipes that fit the given criteria. 

### Overview of the features

1. See a list of all recipes
**Overview**: on this screen, you can scroll through the entire list of recipes. 
**Where to find it**: this screen will open when the application starts. You can return to this screen by clicking the ‘recipe’ button (icon has a book) on the navigation bar at the bottom of the screen.

2. See high-level information about each recipe
**Overview**: when you scroll through the main recipe screen, you can see the estimated time, difficulty, taste and overall rating of each recipe to help decide which recipe to make. 
**Where to find it**: this screen will open when the application starts. You can return to this screen by clicking the ‘recipe’ button (icon has a book) on the navigation bar at the bottom of the screen.

3. Adjust serving size
**Overview**: You can use a drop-down menu to adjust the serving size of the recipe you are viewing. Little Chef will adjust the quantity and unit (if applicable) of each ingredient in the recipe to reflect the new serving size. For instance, if 1 serving of a given recipe calls for 250 ml of Milk needed, then if "2 servings" is chosen, the app will change the amount shown to 0.5, and the measurement will be l - which stands for litres).
**Where to find it**: Start on the Recipe screen. Click on the title of any recipe. A new screen will open up, showing you the ingredients in the recipe. A button on the top right corner is labelled ‘1 serving’. If you click the button, a drop-down menu will show up where you can select the new serving size. 

4. Read instructions
**Overview**: Instructions are broken down into ‘primary instruction’ and ‘sub-instruction’ to make the instructions readable at a glance. The primary instructions are bolded and numbered. Each primary instruction has an accompanying sub-instruction below it, which gives more detail about the primary instruction. The instructions are scrollable if they don't fit onto the screen.
**Where to find it**: Choose a recipe from the list of recipes, and once chosen, the app will take you to the ingredient screen of the recipe, where you will see instructions button at the very bottom of the screen, press it, and the app will take you to the instructions screen of the recipe.

5. Start cooking (start timer)
**Overview**: On the same screen where a user reads instructions, the user can start, stop and reset a cooking timer. The user will start the time when they begin cooking. If the user gets distracted and needs to pause cooking for a bit, the user can stop the timer and then continue cooking when back. A click of a button can also restart the timer. If the restart is pressed, a warning message will come up to ensure the user did not press it unintentionally. Once the user finishes cooking and has pressed finish, the app will save time on the timer. Little Chef will use this and all the previous times for this recipe to update the estimated cooking time.
**Where to find it**: Get to the instructions screen. The app will display the buttons for the timer at the bottom of the screen on the instructions screen.

6. Rate the recipe
**Overview**: Once the User has indicated that they are done cooking (by pressing the finish button), the app will take them to the rate recipe page. There are two criteria by which you will rate the recipe: taste and difficulty. You use the radio buttons to select a number from 1 (worse than expected) to 5 (better than expected) to rate the tastiness and difficulty of the recipe.
**Where to find it**: Get to the instructions screen, press start the timer, stop it, and finish to get to the rate recipe screen.

7. Add Recipe
**Overview**: The Add Recipe screen allows users to add their recipes to the collection. It guides the user through each step. To start, the user will name their recipe. Then they will add all the ingredients and steps for the recipe. When the user first clicks add recipe it will add the recipe to the list, and every time the user clicks add instructions and add ingredient it will automatically add them to that recipe, when the user is done they can simply go back and the recipe with all the added ingredients and instructions will be there.

8. Edit a Recipe
**Overview**: Once the user is on the detailed recipe view, they can click on the 'edit' icon to start editing it. On the edit recipe screen, the user can change the name of the recipe to a new valid name. They can then click on the next button which will take them to the edit instructions page. Here they can add, edit, or delete ingredients. Finally, they can add, edit, or delete the instructions. When the user is done they can click the save button to finish saving the changes. 

9. Suggest a Recipe
**Overview**: The Suggest a Recipe screen has 5 buttons each representing what they care about: a challenge, ease, saving time, taste, and surprise. The user can click the challenge button if they would like to get some recipes that were rated as most difficult by them. They can click the ease button if they would like the recipes that have the lowest difficulty rating. They can click saving time if they want recipes with the lowest estimated cooking time. They can click taste if they want recipes with the highest taste rating, and click the surprise button for a random recipe.

### Major changes
Our group made no significant changes to the code's structure or system's behaviour. All the changes made were the system's extensions in Iteration 2 in terms of functionality and addition of the real DataBase.

### Outstanding issues or bugs:
We have no outstanding issues or bugs that we know of.

### This App was tested on the following devices:
1.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: Windows 10, GPU: Radeon RX5700XT, 32gb RAM, CPU: Intel Core i5-9600K

2.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: Windows 10, GPU: NVIDIA GeForce GTX 690M, 16gb RAM, CPU: Intel(R) Core(TM) i7-6700HQ CPU
 

3.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: Windows 10, GPU: NVIDIA GeForce RTX 3070, 16gb RAM, CPU: AMD Ryzen 7 3700X

4.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: Windows 10, GPU: NVIDIA GeForce GTX 1080, 8gb RAM, CPU: AMD FX-8320

5.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: MacOS Catalina Version 10.15.3, GPU: Intel Iris Pro 1536 MB, CPU: 2.2 GHz Quad-Core Intel Core i7
