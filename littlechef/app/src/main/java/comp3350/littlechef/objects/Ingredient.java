package comp3350.littlechef.objects;

public class Ingredient
{
    private String name;

    private String measurement; //how the ingredient is measured, e.g: ml, teaspoons, cups
    private double amount; //number of ingredient needed

    public Ingredient(String name, double amount)
    {
        this.name = name;
        this.measurement = null;
        this.amount = amount;
    }

    public Ingredient(String name, String measurement, double amount)
    {
        this.name = name;
        this.measurement = measurement;
        this.amount = amount;
    }
//getters and setters
    public String getName() { return name; }
    public void setName(String name) {this.name = name;}

    public String getMeasurement() {return measurement;}
    public void setMeasurement(String measurement) {this.measurement = measurement;}

    public double getAmount() {return amount;}
    public void setAmount() {this.amount = amount;}

}
