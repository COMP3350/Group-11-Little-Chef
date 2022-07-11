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
import android.widget.TextView;
import android.widget.Toast;


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

        TextView workingRecipeName= (TextView) findViewById(R.id.workingRecipeName);
        workingRecipeName.setText("Add Ingredients and steps to "+selectedRecipe.getName());
        Button addIngredientButton= (Button) findViewById(R.id.addIngredientButton);
        Button addInstructionButton= (Button) findViewById(R.id.addInstructionButton);

        //Get input from text fields
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
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addIngredients();
                //clear fields
                ingredientInputName.getText().clear();
                ingredientInputAmount.getText().clear();
            }
        });

        //button listener for adding an instruction to a recipe
        addInstructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                addInstruction();
                //clear fields
                instructionInput.getText().clear();
                instructionInputSteps.getText().clear();
            }
        });
    }

    //add instruction
    private void addInstruction()
    {
        String result;

        //get text from boxes
        instruction = instructionInput.getText().toString();
        instructionSteps = instructionInputSteps.getText().toString();

        result = validateInstruction(instruction + instructionSteps);
        if(result == null)
        {
            selectedRecipe.addInstructions(instruction, instructionSteps);

            result = accessRecipes.updateRecipe(selectedRecipe);

            if (result != null)
            {
                Messages.fatalError(this, result);
            }
            else
            {
                Toast.makeText(this, "Added instruction to " + selectedRecipe.getName(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Messages.warning(this, result);
        }
    }

    //add ingredients
    private void addIngredients()
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

    //validate if proper ingredient
    private String validateIngredient(Ingredient ingredient)
    {
        if (ingredient.getName().length() == 0)
        {
            return "Ingredient name required";
        }

        if( ingredient.getAmount() <= 0 )
        {
            return "Ingredient amount must be greater then 0!";
        }

        return null;
    }

    //validate if proper instruction
    private String validateInstruction(String string)
    {
        if (string.length() == 0)
        {
            return "Instructions Input required";
        }

        return null;
    }


}