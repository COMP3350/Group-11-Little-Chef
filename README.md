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
- business.ScaleRecipe.java
- objects.Ingredient.java
- objects.Recipe.java
- persistence.DataAccessStub.java
- presentation.DetailedRecipeActivity.java
- presentation.HomeActivity.java
- presentation.ViewFragment.java 
- presentation.Messages.java


### URL TO GIT REPO
xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

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
Currently our app allows a user to hold a list of thier recipes with included ingredients and instruction set. A user will also be able to adjust the measurements of each ingredient by how many servings they wish to make, from 1 up to 7 servings. This app also allows the user to time their meals so they can get a reference for how long each meal will take to prepare. Once the meal is made a user can then rate the meal so they can reference this if they choose to make the meal again. The app allows the user to add whatever recipes they choose very easily. 

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
	
**Note**: the functional bottom navigation menu has three buttons: "Recipes", "Add Recipe", and "Meal Plans". You can use all three buttons to switch between activities. However, "Add Recipe" and "Meal Plans" will be blank pages because they have not been implemented yet. They are needed now to show the functionality of the navigation menu. The AddFragment.java, MealsFragment.java and their respective .xml files were created to support this. In the future, iterations "Add recipe" will allow creating and adding the recipes to a database and "Meal Plans" will allow scheduling of created recipes.

### This App was tested on the following devices:
1.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: Windows 10, GPU: Radeon RX5700XT, 32gb RAM, CPU: Intel Core i5-9600K

2.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: Windows 10, GPU: NVIDIA GeFrce GTX 690M, 16gb RAM, CPU: Intel(R) Core(TM) i7-6700HQ CPU


3.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: Windows 10, GPU: NVIDIA GeForce RTX 3070, 16gb RAM, CPU: AMD Ryzen 7 3700X

4.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: Windows 10, GPU: NVIDIA GeForce GTX 1080, 8gb RAM, CPU: AMD FX-8320

5.  	- Android Systems: Android 6.0 Google APIs|x86 (Marshmallow)
    	- Emulator: Nexus 7 API 23
    	- Hardware: MacOS Catalina Version 10.15.3, GPU: Intel Iris Pro 1536 MB, CPU: 2.2 GHz Quad-Core Intel Core i7
