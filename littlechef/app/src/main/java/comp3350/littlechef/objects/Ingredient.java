package comp3350.littlechef.objects;

public class Ingredient
{
    private String Name;

    private String measurement; //how the ingredient is measured, e.g: ml, teaspoons, cups
    private double amount; //number of ingredient needed

    public Ingredient(String Name)
    {
        this.Name = Name;
    }

    public Ingredient(String Name, String measurement, double amount)
    {
        this.Name = Name;
        this.measurement = measurement;
        this.amount = amount;
    }
//getters and setters
    public String getName() { return Name; }
    public void setName(String name) {this.Name = name;}

    public String getMeasurement() {return measurement;}
    public void setMeasurement(String measurement) {this.measurement = measurement;}

    public double getAmount() {return amount;}
    public void setAmount() {this.amount = amount;}

}
