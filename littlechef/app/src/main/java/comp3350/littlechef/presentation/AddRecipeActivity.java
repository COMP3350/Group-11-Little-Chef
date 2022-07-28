package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


// CLASS: AddRecipeActivity.java
//
//
// REMARKS: This class is clicked on by addFragment, where you can add ingredients and instructions
//            to a certain recipe
//
//-----------------------------------------

public class AddRecipeActivity extends AppCompatActivity
{
    Recipe selectedRecipe;

    String nameIngred,amountIngred,instruction, instructionSteps;

    EditText ingredientInputName;
    EditText ingredientInputAmount;
    EditText instructionInput;
    EditText instructionInputSteps;

    private AccessRecipes accessRecipes;

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

       // TextView workingRecipeName= (TextView) findViewById(R.id.workingRecipeName);
       // workingRecipeName.setText("Add Ingredients and steps to "+selectedRecipe.getName());

        Button addIngredientButton= (Button) findViewById(R.id.addIngredientButton);
        Button saveAllIngredientsButton= (Button) findViewById(R.id.save_all_ingredients);
        Button finishAddingButton= (Button) findViewById(R.id.finishAdding);
        Button cancelButton= (Button) findViewById(R.id.cancelButton);

        //Get input from text fields
        ingredientInputName = (EditText) findViewById(R.id.ingredientName);
        ingredientInputName = (EditText) findViewById(R.id.ingredientName);
        ingredientInputAmount = (EditText) findViewById(R.id.ingredientAmount);
        instructionInput = (EditText) findViewById(R.id.instruction);
        instructionInputSteps = (EditText) findViewById(R.id.instructionsSteps);

        //for db
        accessRecipes = new AccessRecipes();

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
        addIngredientButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addIngredients();
                //clear fields
                ingredientInputName.getText().clear();
                ingredientInputAmount.getText().clear();
            }
        });


        //button to cancel and delete working recipe
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                deleteRecipe();
            }
        });

        //button to start adding instructions to the recipe
        saveAllIngredientsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addInstruction();
            }
        });


    }

    private void deleteRecipe()
    {
        String result;
        AccessRecipes accessRecipes = new AccessRecipes();
        result = accessRecipes.deleteRecipe(selectedRecipe);
        if (result == null)
        {
            finish();
        }
        else
        {
            Messages.fatalError(this, result);
        }
    }

    //add instruction
    private void addInstruction()
    {
        Intent addInstructionActivity = new Intent(this, AddInstructionActivity.class);
        addInstructionActivity.putExtra("id", selectedRecipe); //pass the object reference to another activity
        startActivity(addInstructionActivity);
        finish();
    }

    //add ingredients
    private void addIngredients()
    {
        String result;
        nameIngred = ingredientInputName.getText().toString();
        amountIngred = ingredientInputAmount.getText().toString();
        double amount;
         try
        {
            amount = Double.parseDouble(amountIngred);
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
            }
            else
            {
                Toast.makeText(this, "Added " + nameIngred + " to " + selectedRecipe.getName(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Messages.warning(this, result);
        }

    }
    //this just checks the unit input and assigns it
    private Unit checkUnit()
    {
        return Unit.valueOf(unitString.toUpperCase());
    }

    //validate if proper ingredient
    private String validateIngredient(Ingredient ingredient)
    {
        String result = null;

        if(ingredient == null)
        {
            result = "Ingredient cannot be null";
        }
        else if(checkDuplicateIngredient(ingredient))
        {
            result = "Ingredient already exists!";
        }
        else
        {
            if (ingredient.getName().trim().length() == 0) {
                result = "Ingredient name required";
            }

            if (ingredient.getNumberOfIngredients() <= 0) {
                result = "Ingredient amount must be greater then 0!";
            }
        }

        return result;
    }

    //checks if duplicate ingredient
    private boolean checkDuplicateIngredient(Ingredient ingredient)
    {
        boolean result = false;
        ArrayList<Ingredient> recipeIngredients = selectedRecipe.getIngredients();

        for(int i = 0; i < recipeIngredients.size(); i++)
        {
            Ingredient check = recipeIngredients.get(0);
            if(ingredient.getName().equals(check.getName()))
                result = true;
        }

        return result;
    }



}