package comp3350.littlechef.objects;

import java.util.Locale;
import java.io.Serializable;

  
enum UnitType
{
    VOLUME,
    WEIGHT,
    SIZE,
    DEFAULT
}

enum Unit
{
    PINCH,
    TSP,
    TBSP,
    CUP,
    ML,
    L,
    MG,
    G,
    KG,
    MM,
    CM,
    M,
    QUANTITY // just a default
}

public class Ingredient implements Serializable
{
    private String name;
   // private Unit measurement; //how the ingredient is measured, e.g: ml, teaspoons, cups
    private double amount; //number of ingredient needed

    private UnitType unitType;
    private Unit unit;

    public Ingredient(String name, double amount) {
        this.name = name;
      //  this.measurement = null;
        if(amount > 0) {
            this.amount = amount;
        }
        else{
            this.amount = 1;
        }

        this.unit = Unit.QUANTITY; // will just display as x quantity
        this.unitType = UnitType.DEFAULT; // will just scale no unit conversion
    }

    public Ingredient(String name, String measurement, double amount) {
        this.name = name;
        this.amount = amount;

        if(amount > 0) {
            this.amount = amount;
        }
        else{
            this.amount = 1;
        }

//        this.unit = Unit.QUANTITY; // will just display as x quantity
        this.unitType = UnitType.DEFAULT; // will just scale no unit conversion

        setUnitInformation(measurement);
       // this.measurement = this.unit.toString().toLowerCase();
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return unit.toString().toLowerCase();
    }

    public void setMeasurement(String unit) {
        setUnitInformation(unit);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if(amount > 0) {
            this.amount = amount;
        }
    }

    public String getUnitType() {
        return unitType.toString();
    }


    private void setUnitInformation(String measurement) {
        for (Unit u : Unit.values()) {
            if (measurement.equalsIgnoreCase(u.name())) {
                this.unit = u;

                if (u.name().equalsIgnoreCase("PINCH") ||
                        u.name().equalsIgnoreCase("TSP") ||
                        u.name().equalsIgnoreCase("TBSP") ||
                        u.name().equalsIgnoreCase("CUP") ||
                        u.name().equalsIgnoreCase("ML") ||
                        u.name().equalsIgnoreCase("L")) {
                    this.unitType = UnitType.VOLUME;
                } else if (u.name().equalsIgnoreCase("MG") ||
                        u.name().equalsIgnoreCase("G") ||
                        u.name().equalsIgnoreCase("KG")) {
                    this.unitType = UnitType.WEIGHT;
                } else if (u.name().equalsIgnoreCase("MM") ||
                        u.name().equalsIgnoreCase("CM") ||
                        u.name().equalsIgnoreCase("M")) {
                    this.unitType = UnitType.SIZE;
                }
                break;
            }
            // otherwise throw an error that the measurement type is invalid.

        }
    }

}
