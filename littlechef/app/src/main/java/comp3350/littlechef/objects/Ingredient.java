package comp3350.littlechef.objects;

import java.io.Serializable;
// CLASS: Ingredient.java
//
//
// REMARKS: This class is to create an ingredient with specific attributes.
//
//-----------------------------------------
public class Ingredient implements Serializable
{
    private String name;
    private double amount; //number of ingredient needed

    private UnitType unitType;
    private Unit unit;

    public Ingredient(String name, Unit measurement, double amount)
    {
        this.name = name;
        this.amount = amount;

        if(amount > 0)
        {
            this.amount = amount;
        }
        else
        {
            this.amount = 1;
        }
        this.unitType = UnitType.DEFAULT; // will just scale no unit conversion
        setUnitInformation(measurement);


    }

    //getters and setters
    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getMeasurement() {
        return unit;
    }

    public void setMeasurement(Unit unit) {
        setUnitInformation(unit);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount)
    {
        if(amount > 0)
        {
            this.amount = amount;
        }
    }

    public String getUnitType() {
        return unitType.toString();
    }

    public String getDisplayMeasurement()
    {
        return String.format("%.2f", amount) + " " + getMeasurement();
    }


    //this class sets the specific unit metric depending on unit type
    private void setUnitInformation(Unit measurement)
    {
        if(measurement != null) {
            this.unit = measurement;

            if (this.unit == Unit.PINCH || this.unit == Unit.TSP || this.unit == Unit.TBSP || this.unit == Unit.CUP || this.unit == Unit.ML || this.unit == Unit.L) {
                this.unitType = UnitType.VOLUME;
            } else if (this.unit == Unit.MG || this.unit == Unit.G || this.unit == Unit.KG) {
                this.unitType = UnitType.WEIGHT;
            } else if (this.unit == Unit.MM || this.unit == Unit.CM || this.unit == Unit.M) {
                this.unitType = UnitType.SIZE;
            }
        }
        else if (this.unit == null)
        {
            this.unit = Unit.QUANTITY;
        }
    }

}
