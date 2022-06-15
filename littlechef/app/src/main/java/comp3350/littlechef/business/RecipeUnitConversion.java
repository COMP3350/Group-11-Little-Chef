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

    public static String convertDecimalToFraction(double numberToConvert)
    {
        if(numberToConvert < 0 || numberToConvert > 1)
        {
            return "error";
        }
        else
        {
            double allowedRoundingError = 0.01;

            int lower_numerator = 0;
            int lower_denominator = 1;
            int upper_numerator = 1;
            int upper_denominator = 1;
            int mid_numerator = 0;
            int mid_denominator = 0;

            boolean equivalentFractionFound = false;
            while(!equivalentFractionFound)
            {
                mid_numerator = lower_numerator + upper_numerator;
                mid_denominator = lower_denominator + upper_denominator;
                if(mid_denominator * (numberToConvert + allowedRoundingError) < mid_numerator)
                {
                    upper_numerator = mid_numerator;
                    upper_denominator = mid_denominator;
                }
                else if(mid_denominator * (numberToConvert - allowedRoundingError) > mid_numerator)
                {
                    upper_numerator = mid_numerator;
                    upper_denominator = mid_denominator;
                }
                else
                {
                    equivalentFractionFound = true;
                }
            }
            return(mid_numerator + "/" + mid_denominator);
        }
    }
}
