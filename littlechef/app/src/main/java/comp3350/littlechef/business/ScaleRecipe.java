package comp3350.littlechef.business;

import java.util.ArrayList;
import java.lang.Math;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Unit;

// CLASS: ScaleRecipe.java
//
//
// REMARKS: This class will scale the recipes ingredients based on serving size input.
//
//-----------------------------------------
public class ScaleRecipe
{
    private static ArrayList<Ingredient> convertedIngredients;

    final static double PINCH_IN_TSP = 16;
    final static double TSP_IN_TBSP = 3;
    final static double TBSP_IN_CUP = 16;

    final static double WEIGHT_FACTOR = 1000;
    final static double MM_IN_CM = 10;
    final static double CM_IN_M = 100;
    final static double ML_IN_L = 1000;

    public static ArrayList<Ingredient> scaleIngredients(Recipe recipe, int servings)
    {
        ArrayList<Ingredient> actualIngredients = recipe.getIngredients();
        double newAmount;
        convertedIngredients = new ArrayList<>();
        if(servings > 1)
        {
            for (int i = 0; i < actualIngredients.size(); i++)
            {
                newAmount = actualIngredients.get(i).getAmount() * servings;
                convertedIngredients.add(new Ingredient(actualIngredients.get(i).getName(), actualIngredients.get(i).getMeasurement(), newAmount));
                adjustUnits(convertedIngredients.get(i));
            }
        }
        else
        {
            convertedIngredients = actualIngredients;
        }

        return convertedIngredients;
    }

    //this function will adjust the units based on what serving metric is used, calling the appropriate function.
    private static void adjustUnits(Ingredient ingredient)
    {
        String unitType;
        double adjustedAmount = ingredient.getAmount();
        unitType = ingredient.getUnitType();
        
            switch(unitType.toLowerCase())
            {
                case "volume":
                    adjustedAmount =  adjustVolume(ingredient);
                    break;

                case "weight":
                    adjustedAmount = adjustWeight(ingredient);
                    break;

                case "size":
                    adjustedAmount = adjustSize(ingredient);
                    break;
            }

        adjustedAmount = Math.round(adjustedAmount*4)/4f;
        ingredient.setAmount(adjustedAmount);

    }


    private static double adjustVolume(Ingredient ingredient)
    {

        double tempAmount = ingredient.getAmount();

        if(ingredient.getMeasurement() == Unit.PINCH && tempAmount >= PINCH_IN_TSP)
        {
            ingredient.setMeasurement(Unit.TSP);
            tempAmount /= PINCH_IN_TSP;
        }

        if(ingredient.getMeasurement() == Unit.TSP && tempAmount >= TSP_IN_TBSP)
        {
            ingredient.setMeasurement(Unit.TBSP);
            tempAmount /= TSP_IN_TBSP;
        }

        if(ingredient.getMeasurement() == Unit.TBSP && tempAmount >= TBSP_IN_CUP/4)
        {
            ingredient.setMeasurement(Unit.CUP);
            tempAmount /= TBSP_IN_CUP;
        }

        if(ingredient.getMeasurement() == Unit.ML && tempAmount >= ML_IN_L/2)
        {
            ingredient.setMeasurement(Unit.L);
            tempAmount /= ML_IN_L;
        }

        return tempAmount;
    }

    private static double adjustWeight(Ingredient ingredient)
    {
        double tempAmount = ingredient.getAmount();

        if(ingredient.getMeasurement() == Unit.MG && tempAmount >= WEIGHT_FACTOR)
        {
            ingredient.setMeasurement(Unit.G);
            tempAmount /= WEIGHT_FACTOR;
        }

        if(ingredient.getMeasurement()== Unit.G && tempAmount >= WEIGHT_FACTOR)
        {
            ingredient.setMeasurement(Unit.KG);
            tempAmount /= WEIGHT_FACTOR;
        }

        return tempAmount;
    }

    private static double adjustSize(Ingredient ingredient)
    {
        double tempAmount = ingredient.getAmount();

        if(ingredient.getMeasurement()== Unit.MM && tempAmount >= MM_IN_CM)
        {
            ingredient.setMeasurement(Unit.CM);
            tempAmount /= MM_IN_CM;
        }

        if(ingredient.getMeasurement() == Unit.CM && tempAmount >= CM_IN_M)
        {
            ingredient.setMeasurement(Unit.M);
            tempAmount /= CM_IN_M;

        }

        return tempAmount;
    }
}
