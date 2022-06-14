package comp3350.littlechef.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Ingredient;

public class ScaleRecipe {
    private static ArrayList<Ingredient> convertedIngredients;

    final static double PINCH_IN_TSP = 16;
    final static double TSP_IN_TBSP = 3;
    final static double TBSP_IN_HALF_CUP = 16;

    final static double QUARTER = 4;



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

    private static void adjustUnits(Ingredient ingredient){
        String unitType;
        System.out.println("adjusting units for "+ingredient.getName()+" .\n");

            unitType = ingredient.getUnitType();


            System.out.println("<"+ingredient.getName()+"> "+unitType.toLowerCase());
            switch(unitType.toLowerCase()){
                case "volume":
                    adjustVolume(ingredient);
                    break;

                case "weight":
                    adjustWeight(ingredient);
                    break;

                case "size":
                    adjustSize(ingredient);
                    break;
            }

    }


    private static void adjustVolume(Ingredient ingredient){

        double tempAmount = ingredient.getAmount();

        System.out.println("adjusting volume for "+ingredient.getName()+"\n");
        System.out.println("<"+ingredient.getName()+"> before: "+tempAmount+" "+ingredient.getMeasurement()+"\n");

        if(ingredient.getMeasurement().equalsIgnoreCase("PINCH") && tempAmount >= PINCH_IN_TSP)
        {
            ingredient.setMeasurement("tsp");
            tempAmount /= PINCH_IN_TSP;

            System.out.println("<"+ingredient.getName()+"> then: "+tempAmount+" "+ingredient.getMeasurement()+"\n");
        }

        if(ingredient.getMeasurement().equalsIgnoreCase("tsp") && tempAmount >= TSP_IN_TBSP)
        {
            ingredient.setMeasurement("tbsp");
            tempAmount /= TSP_IN_TBSP;

            System.out.println("<"+ingredient.getName()+"> then: "+tempAmount+" "+ingredient.getMeasurement()+"\n");
        }

        if(ingredient.getMeasurement().equalsIgnoreCase("tbsp") && tempAmount >= QUARTER)
        {
            ingredient.setMeasurement("cup");
            tempAmount /= TBSP_IN_HALF_CUP;

            System.out.println("<"+ingredient.getName()+"> then: "+tempAmount+" "+ingredient.getMeasurement()+"\n");
        }

        if(ingredient.getMeasurement().equalsIgnoreCase("ml") && tempAmount >= 250)
        {
            ingredient.setMeasurement("l");
            tempAmount /= 1000;

            System.out.println("<"+ingredient.getName()+"> then: "+tempAmount+" "+ingredient.getMeasurement()+"\n");
        }

        ingredient.setAmount(tempAmount);

    }

    private static void adjustWeight(Ingredient ingredient){
        double tempAmount = ingredient.getAmount();

        System.out.println("adjusting volume for "+ingredient.getName()+"\n");
        System.out.println("<"+ingredient.getName()+"> before: "+tempAmount+" "+ingredient.getMeasurement()+"\n");

        if(ingredient.getMeasurement().equalsIgnoreCase("MG") && tempAmount >= 1000)
        {
            ingredient.setMeasurement("g");
            tempAmount /= 1000;

            System.out.println("<"+ingredient.getName()+"> then: "+tempAmount+" "+ingredient.getMeasurement()+"\n");
        }

        if(ingredient.getMeasurement().equalsIgnoreCase("G") && tempAmount >= 1000)
        {
            ingredient.setMeasurement("kg");
            tempAmount /= 1000;

            System.out.println("<"+ingredient.getName()+"> then: "+tempAmount+" "+ingredient.getMeasurement()+"\n");
        }

        ingredient.setAmount(tempAmount);
    }

    private static void adjustSize(Ingredient ingredient){
        double tempAmount = ingredient.getAmount();

        System.out.println("adjusting volume for "+ingredient.getName()+"\n");
        System.out.println("<"+ingredient.getName()+"> before: "+tempAmount+" "+ingredient.getMeasurement()+"\n");

        if(ingredient.getMeasurement().equalsIgnoreCase("MM") && tempAmount >= 10)
        {
            ingredient.setMeasurement("CM");
            tempAmount /= 10;

            System.out.println("<"+ingredient.getName()+"> then: "+tempAmount+" "+ingredient.getMeasurement()+"\n");
        }

        if(ingredient.getMeasurement().equalsIgnoreCase("G") && tempAmount >= 100)
        {
            ingredient.setMeasurement("kg");
            tempAmount /= 100;

            System.out.println("<"+ingredient.getName()+"> then: "+tempAmount+" "+ingredient.getMeasurement()+"\n");
        }

        ingredient.setAmount(tempAmount);
    }
}
