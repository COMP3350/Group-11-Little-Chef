package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity
{
    Recipe selectedRecipe;

    String name;
    String nameIngred,amountIngred;

    EditText ingredientInputName;
    EditText ingredientInputAmount;

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    String unitString;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id");

        Toast.makeText(this, "clicked: "+selectedRecipe.getName(), Toast.LENGTH_SHORT).show();

        TextView workingRecipeName= (TextView) findViewById(R.id.workingRecipeName);
        workingRecipeName.setText("Add Ingredients and steps to "+selectedRecipe.getName());
        EditText ingredientName= (EditText) findViewById(R.id.ingredientName);
        EditText ingredientAmount= (EditText) findViewById(R.id.ingredientAmount);
        Button addIngredientButton= (Button) findViewById(R.id.addIngredientButton);

        //Get input from text fields
        ingredientInputName = (EditText) findViewById(R.id.ingredientName);
        ingredientInputAmount = (EditText) findViewById(R.id.ingredientAmount);

        //for db
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();

        //for the spinner
        spinner = (Spinner) findViewById(R.id.spinnerUnit);
        adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //set unit to what ever is set
                unitString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                //keep empty
            }
        });

        //button listener for adding an ingredient to a recipe
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addIngredients(view);
            }
        });


    }

    //add ingredients
    private void addIngredients(View view)
    {
        String result;
        nameIngred = ingredientInputName.getText().toString();
        amountIngred = ingredientInputAmount.getText().toString();
        int amount;
        try
        {
            amount = Integer.parseInt(amountIngred);
        }
        catch(Exception e)
        {
            amount = 0;
        }

        Unit unit = checkUnit();
        Ingredient newIngredient = new Ingredient(nameIngred, unit, amount);
        result = validateIngredient(newIngredient);
        if(result == null)
        {
            selectedRecipe.addIngredient(new Ingredient(nameIngred, unit, amount));

            result = accessRecipes.updateRecipe(selectedRecipe);
            if (result != null)
            {
                Messages.fatalError(this, result);
            } else
            {
                Toast.makeText(this, "Added " + nameIngred + " to " + selectedRecipe.getName(), Toast.LENGTH_SHORT).show();
            }
        }

    }
    //this just checks the unit input and assigns it
    private Unit checkUnit()
    {
        Unit unit;
        if( unitString.equals("PINCH") )
            return Unit.PINCH;
        else if( unitString.equals("TSP") )
            return Unit.TSP;
        else if( unitString.equals("TBSP") )
            return Unit.TBSP;
        else if( unitString.equals("CUP") )
            return Unit.CUP;
        else if( unitString.equals("ML") )
            return Unit.ML;
        else if( unitString.equals("L") )
            return Unit.L;
        else if( unitString.equals("MG") )
            return Unit.MG;
        else if( unitString.equals("G") )
            return Unit.G;
        else if( unitString.equals("KG") )
            return Unit.KG;
        else if( unitString.equals("MM") )
            return Unit.MM;
        else if( unitString.equals("CM") )
            return Unit.CM;
        else if( unitString.equals("M") )
            return Unit.M;
        else
            return null;
    }

    //COURSE TESTING
    private String validateIngredient(Ingredient ingredient)
    {
        if (ingredient.getName().length() == 0)
        {
            return "Ingredient input name required";
        }

        if( ingredient.getAmount() <= 0 )
        {
            return "Ingredient amount must be greater then 0!";
        }

        return null;
    }


}