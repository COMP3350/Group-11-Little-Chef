# Little Chef

**Group 11: Dylan, Brad, Kajal, Wen, Denys**

### Packages 
- application
- business 
- objects
- persistence
- presentation 

### Major Souce Code Files
- application.Main.java
- application.Services.java
- business.AccessRecipes.java
- business.DecimalFractionConversion.java
- business.ScaleRecipe.java
- business.TimeRecipe.java
- objects.Ingredient.java
- objects.Recipe.java
- persistence.DataAccess.java
- persistence.DataAccessObject.java
- presentation.AddFragment.java
- presentation.AddRecipeActivity.java
- presentation.CookedAnotherMealActivity.java
- presentation.DetailedRecipeActivity.java
- presentation.HomeActivity.java
- presentation.Messages.java
- presentation.RateRecipyActivity.java
- presentation.RecipeInstructionActivity.java
- presentation.ViewFragment.java 


### URL TO GIT REPO
An invite link has been sent to username:Braico

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

### Big and Detailed User Stories
**Filename**: Big and Detailed User Stories.pdf. 
**Location**: saved in the private repository along with the project.

### Big Picture Overview
Currently our app allows a user to hold a list of their recipes with included ingredients and instruction set. A user will also be able to adjust the measurements of each ingredient by how many servings they wish to make, from 1 up to 7 servings. This app also allows the user to time their meals so they can get a reference for how long each meal will take to prepare. Once the meal is made a user can then rate the meal so they can reference this if they choose to make the meal again. The app allows the user to add whatever recipes they choose very easily. 

### Overview of the features

1. See a list of all recipes
**Overview**: on this screen, you can scroll through the entire list of recipes. 
**Where to find it**: this screen will open when the application starts. You can return to this screen by clicking the ‘recipe’ button (icon has a book) on the navigation bar at the bottom of the screen.

2. See high-level information about each recipe
**Overview**: when you scroll through the main recipe screen, you can see the estimated time, difficulty, taste and overall rating of each recipe to help decide which recipe to make. 
**Where to find it**: this screen will open when the application starts. You can return to this screen by clicking the ‘recipe’ button (icon has a book) on the navigation bar at the bottom of the screen.

3. Adjust serving size
**Overview**: You can use a drop-down menu to adjust the serving size of the recipe you are viewing. The quantity and unit (if applicable) of each ingredient in the recipe will be adjusted to reflect the new serving size. For instance, if 1 serving of a given recipe calls for 250 ml of Milk needed, then if "2 servings" is chosen, the amount shown will be changed to 0.5, and the measurement will be l - which stands for litres).
**Where to find it**: Start on the Recipe screen. Click on the title of any recipe. A new screen will open up, showing you the ingredients in the recipe. A button on the top right corner is labelled ‘1 serving’. If you click the button, a drop-down menu will show up where you can select the new serving size. 

4. Read instructions
**Overview**: Every recipe in the app has its own instructions which consist of the main instruction, which is bold and numbered, and a more detailed sub instruction, which is optional. The instructions are scrollable if they don't fit onto the screen and are easy to read
**Where to find it**: Choose a recipe from the list of recipes and once chosen, you will be taken to the ingredient screen of the recipe where you would see instructions button at the very bottom of the screen, press it and you will be taken to the instructions screen of the recipe.

5. Start cooking(Start timer)
**Overview**: On the same screen where a user reads instructions, the user can also start cooking or start a timer and the app will track the amount of time the user spends on cooking by the recipe. If the user gets distracted and needs to pause cooking for a bit, the user can stop the timer and then continue cooking when back. The timer can also be restarted by a click of a button, if the restart is pressed, a pop up warning message will come up to make sure the user did not press it unintentionally. Once the user finishes cooking, the stop should be pressed, and the user can press finish then to finish cooking the recipe and be taken to a rating screen.
**Where to find it**: Get to the instructions screen. The buttons for the timer will be displayed at the bottom of the screen on the instructions screen.

6. Rate the recipe
**Overview**: Once the user cooked the recipe and pressed submit, the user will be taken to the screen where the user could rate the recipe. There are two criterias by which the recipe is rated, taste and difficulty, the user can choose to rate both criterias from 1-5 and then press submit which will take the user to the homepage, where the list of all recipes are and the where the user can start over
**Where to find it**: Get to the instructions screen, press start the timer, then stop the timer and finish to get to the rate recipe screen.

7. Add Recipe
**Overview**: The Add Recipe screen allows the user to add their own recipes to the collection. It guides the user through each step. To start, the user will name their recipe, then they will add the all the ingredients and steps for the recipe.

**Note**: the functional bottom navigation menu has three buttons: "Recipes", "Add Recipe", and "Meal Plans". You can use only two of the buttons("Recipes", "Add Recipe") to switch between activities. "Meal Plans" will be implemented in the future iteration and for now, if pressed it notifies user that it is a work in progress. All three buttons, however, are needed now to show the functionality of the navigation menu. In the future iterations "Meal Plans" will allow scheduling of created recipes.

### Major changes
There were no major changes made to the structure of the code or behaviour of the system. All the changes made were the extensions of the system in the Iteration 1 in terms if functionality and addition of the real DataBase.

### Outstanding issues or bugs:
A recipe is moved to the end of the list of recipes if the estimated time of a recipe is updated or the recipe is rated. This is because updates are implemented as reinsertion in our Database. This issue will be fixed in the next iteration by implementing update functionality of the database to actually update rather than reinsert a recipe.

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
