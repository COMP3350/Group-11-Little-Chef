package comp3350.littlechef.objects;

import java.io.Serializable;
import java.util.ArrayList;

/*MealPlanDay.java
    - when creating have a array list for the days of the week
    - then each day of the week will be an arrayList of recipes
 */
public class MealPlanDay implements Serializable
{
    private ArrayList<Recipe> mealPlan;

    public MealPlanDay()
    {
        mealPlan = new ArrayList<Recipe>();
    }

    public ArrayList<Recipe> getMealPlan()
    {
        return mealPlan;
    }

    public void setMealPlan(ArrayList<Recipe> mealPlan)
    {
        this.mealPlan = mealPlan;
    }
}
