package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
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

import android.os.Bundle;

public class AddInstructionActivity extends AppCompatActivity
{
    Recipe selectedRecipe;

    String instruction;
    String instructionSteps;

    EditText instructionInput;
    EditText instructionInputSteps;

    private AccessRecipes accessRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instruction);

        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id");

        //Toast.makeText(this, "Add to " + selectedRecipe.getName(), Toast.LENGTH_SHORT).show();
        Button finishAdding= (Button) findViewById(R.id.cancel_button);
        Button addInstructionButton= (Button) findViewById(R.id.addInstructionButton);
        Button cancelButton= (Button) findViewById(R.id.cancelButton);

        instructionInput = (EditText) findViewById(R.id.instruction);
        instructionInputSteps = (EditText) findViewById(R.id.instructionsSteps);

        //for db
        accessRecipes = new AccessRecipes();

        //button to go back to the main add recipe screen when done
        finishAdding.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        //button listener for adding an instruction to a recipe
        addInstructionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addInstruction();
                //clear fields
                instructionInput.getText().clear();
                instructionInputSteps.getText().clear();
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

    }//finish oCreate


    //add instruction
    private void addInstruction()
    {
        String result;

        //get text from boxes
        instruction = instructionInput.getText().toString().trim();
        instructionSteps = instructionInputSteps.getText().toString().trim();

        result = validateInstruction(instruction, instructionSteps);

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

    //validate if proper instruction
    private String validateInstruction(String instruction, String instructionSteps)
    {
        String result = null;

        if (instruction == null || instructionSteps == null)
        {
            result = "Instructions cannot be null";
        }
        else if (instruction.trim().length() <= 0)
        {
            result = "Instructions cannot be empty";
        }

        return result;
    }
}