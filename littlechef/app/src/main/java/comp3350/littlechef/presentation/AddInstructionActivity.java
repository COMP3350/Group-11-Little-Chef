package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// kajalchecked

// CLASS: AddInstructionActivity.java
// REMARKS: This class adds capability for the user to add instructions
//          to a newly created recipe.
//-----------------------------------------------------------------------
public class AddInstructionActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;

    private String instruction;
    private String instructionSteps;

    private EditText instructionInput;
    private EditText instructionInputSteps;

    private AccessRecipes accessRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instruction);

        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id");

        Button finishAdding = findViewById(R.id.all_instructions_added_button);
        Button addInstructionButton = findViewById(R.id.add_new_instruction_button);
        Button cancelButton = findViewById(R.id.cancel_adding_instructions_button);

        instructionInput = findViewById(R.id.instruction);
        instructionInputSteps = findViewById(R.id.instruction_steps);

        accessRecipes = new AccessRecipes();

        finishAdding.setOnClickListener(view -> saveRecipe());

        addInstructionButton.setOnClickListener(view -> {
            addInstruction();
            instructionInput.getText().clear();
            instructionInputSteps.getText().clear();
        });

        cancelButton.setOnClickListener(view -> finish());

    }

    //insert into database
    private void saveRecipe()
    {
        String result;

        result = accessRecipes.insertRecipe(selectedRecipe);

        if(result == null)
        {
            Toast.makeText(this, selectedRecipe.getName()+" added!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Messages.fatalError(this, result);
        }

        finish();
    }

    private void addInstruction()
    {
        String result;

        instruction = instructionInput.getText().toString().trim();
        instructionSteps = instructionInputSteps.getText().toString().trim();

        result = validateInstruction(instruction, instructionSteps);

        if(result == null)
        {
            selectedRecipe.addInstructions(instruction, instructionSteps);

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