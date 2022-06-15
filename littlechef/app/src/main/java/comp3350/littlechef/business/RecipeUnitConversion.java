package comp3350.littlechef.business;

import java.util.List;

import comp3350.littlechef.objects.Ingredient;

public class RecipeUnitConversion
{
    public static Double convertFractionToDecimal(String fraction)
    {
        //in the format of "a/b" where 'a' = numerator and 'b' = denominator and '/' seperates the two
        String[] splitFraction = fraction.split("/");
        double numerator = Double.parseDouble(splitFraction[0]);
        double denominator = Double.parseDouble(splitFraction[1]);
        double decimalAmount =  (numerator / denominator);
        System.out.println("decimal:" + decimalAmount + '\n');
        decimalAmount = Math.round(decimalAmount * 1000.00)/1000.00;
        return decimalAmount;
    }

    public static String convertDecimalToFraction(double numberToConvert)
    {
        if(numberToConvert > 0 && numberToConvert < 1)
        {
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
                        lower_numerator = mid_numerator;
                        lower_denominator = mid_denominator;
                    }
                    else
                    {
                        equivalentFractionFound = true;
                    }
                }
                return(mid_numerator + "/" + mid_denominator);
            }

        }
        else
        {
            return "error";
        }
    }
}
