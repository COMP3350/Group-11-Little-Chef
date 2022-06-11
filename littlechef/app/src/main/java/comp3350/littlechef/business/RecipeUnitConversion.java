package comp3350.littlechef.business;

import java.util.List;

import comp3350.littlechef.objects.Ingredient;

public class RecipeUnitConversion
{

    public static String calculateConversion(String measurement, double amount, int servings)
    {
        double newAmount = amount * servings;
        String newMeasurement = measurement;
        final String[] volume = {"drop", "pinch", "ml", "teaspoon", "tablespoon", "cup"};
        final double[] volumeAmount = {1, 6, 96, 480, 1440, 23040};

        if((newMeasurement == "teaspoon") && (newAmount > 3))
        {
            newMeasurement = "tablespoon";
            newAmount = newAmount/3;
        }

        return newMeasurement +
    }

    public static String displayIngredients(Ingredient ingredient)
    {
        if(ingredient.getAmount() > 1)
        {
            //make plural
        }
    }

    public static Double convertFractionToDecimal(String fraction)
    {
        //in the format of "a/b" where 'a' = numerator and 'b' = denominator and '/' seperates the two
        String[] splitFraction = fraction.split("/");
        int numerator = Integer.parseInt(splitFraction[0]);
        int denominator = Integer.parseInt(splitFraction[1]);
        double decimalAmount = (double) (numerator / denominator);
        decimalAmount = Math.round(decimalAmount);
    }

    public static String convertDecimalToFraction(double demical)
    {
        double[] doubleValues = [0.25, 0.33333, 0.5, 0.66666, 0.75, 1.5];
        double negligibleRatio = 0.01;

        for(int i=1;;i++){
            double tem = doubleVal/(1D/i);
            if(Math.abs(tem-Math.round(tem))<negligibleRatio){
                System.out.println(Math.round(tem)+"/"+i);
                break;
            }
        }
    }
}
