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
- presentation.ViewFragment.java 
- presentation.Messages.java
- presentation.RateRecipyActivity.java
- presentation.RecipeInstructionActivity.java


### URL TO GIT REPO
the link was sent to the professor

### The Log

**Location** : The log.txt is saved in the private repository along with the project. All members of our team contributed to keeping the log up to date.

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
**Filename** : Big and Detailed User Stories.pdf. It is found in the submission folder.

### Big Picture Overview
Currently our app allows a user to hold a list of their recipes with included ingredients and instruction set. A user will also be able to adjust the measurements of each ingredient by how many servings they wish to make, from 1 up to 7 servings. This app also allows the user to time their meals so they can get a reference for how long each meal will take to prepare. Once the meal is made a user can then rate the meal so they can reference this if they choose to make the meal again. The app allows the user to add whatever recipes they choose very easily. 

### Overview of the features

1. See a list of all recipes
**Overview**: on this page, you can scroll through the entire list of recipes. 
**Where to find it**: this page will open when the application starts. You can return to this page by clicking the ‘recipe’ button (icon has a book) on the navigation bar at the bottom of the screen.

2. See high-level information about each recipe
**Overview**: when you scroll through the main recipe page, you can see the estimated time, difficulty, taste and overall rating of each recipe to help decide which recipe to make. 
**Where to find it**: this page will open when the application starts. You can return to this page by clicking the ‘recipe’ button (icon has a book) on the navigation bar at the bottom of the screen.

3. Adjust serving size
**Overview**: You can use a drop-down menu to adjust the serving size of the recipe you are viewing. The quantity and unit (if applicable) of each ingredient in the recipe will be adjusted to reflect the new serving size. For instance, if 1 serving of a given recipe calls for 250 ml of Milk needed, then if "2 servings" is chosen, the amount shown will be changed to 0.5, and the measurement will be l - which stands for litres).
**Where to find it**: Start on the Recipe screen. Click on the title of any recipe. A new page will open up, showing you the ingredients in the recipe. A button on the top right corner is labelled ‘1 serving’. If you click the button, a drop-down menu will show up where you can select the new serving size. 
	
**Note**: the functional bottom navigation menu has three buttons: "Recipes", "Add Recipe", and "Meal Plans". You can use only two of the buttons("Recipes", "Add Recipe") to switch between activities. "Meal Plans" will be implemented in the future iteration and for now, if pressed it notifies user that it is a work in progress. All three buttons, however, are needed now to show the functionality of the navigation menu. In the future iterations "Meal Plans" will allow scheduling of created recipes.

4. Read instructions
**Overview**: Every recipe in the app has its own instructions which consist of the main instruction, which is bold and numbered, and a more detailed sub instruction, which is optional. The instructions are scrollable if they don't fit onto the screen and are easy to read
**Where to find it**: Choose a recipe from the list of recipes and once chosen, you will be taken to the ingredient page of the recipe where you would see instructions button at the very bottom of the page, press it and you will be taken to the instructions page of the recipe.

5. Start cooking(Start timer)
**Overview**: On the same page where a user reads instructions, the user can also start cooking or start a timer and the app will track the amount of time the user spends on cooking by the recipe. If the user gets distracted and needs to pause cooking for a bit, the user can stop the timer and then continue cooking when back. The timer can also be restarted by a click of a button, if the restart is pressed, a pop up warning message will come up to make sure the user did not press it unintentionally. Once the user finishes cooking, the stop should be pressed, and the user can press finish then to finish cooking the recipe and be taken to a rating page.
**Where to find it**: same place where the instructions are found.

6. Rate the recipe
**Overview**: Once the user cooked the recipe and pressed submit, the user will be taken to the page where the user could rate the recipe. There are two criterias by which the recipe is rated, taste and difficulty, the user can choose to rate both criterias from 1-5 and then press submit which will take the user to the home page, where the list of all recipes are and the where the user can start over
**Where to find it**: Get to the instructions page, press start the timer, then stop the timer and finish to get to the rate recipe page.

7. Add Recipe
**Overview**: Every Recipe will have ingredients and instructions which can be added through the add recipe fragment at the bottom menu bar. The user will first need to add the name of the recipe which will then open an activity to add ingredients and instructions to that specific recipe. The text boxes in the add instruction section have been made bigger to allow for more text to be seen, the ingredients text boxes were left smaller since not as much information will be inputted. The add ingredient section consists of a spinner where the user can selected the measurement of the ingredient. Once the user is finished adding the information they can go back to where the full recipe will be stored.

### There were no major changes made to the structure of the code or behaviour of the system.
All the changes made were the extensions of the system in the Iteration 1 in terms if functionality and addition of the real DataBase.

### Outstanding issues or bugs:
When a recipe is updated through starting cooking(updating estimated time of cooking) or rating the recipe, the recipe changes its position to the last position in the list.
This a cause of how updates are implemented in our Database, which is a reinsertion. This issue will be definitely fixed in the next iteration by implementing update functionality of the database to actually update rather than reinsert a recipe.
	

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
