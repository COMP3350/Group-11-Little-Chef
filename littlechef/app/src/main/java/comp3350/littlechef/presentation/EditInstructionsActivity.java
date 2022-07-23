package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.business.ScaleRecipe;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditInstructionsActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;
    private ArrayAdapter<String[]> InstructionsArrayAdapter;
    private AccessRecipes accessRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_instruction);
        accessRecipes = new AccessRecipes();

        final ListView listView = (ListView) findViewById(R.id.instruction_list_edit_view);

        //get the selected recipe that was clicked from previous activity
        Intent previousIntent = getIntent();

        Button saveButton = (Button) findViewById(R.id.save_button);
        Button addInstructionButton = (Button) findViewById(R.id.add_button);
        Button cancelButton= (Button) findViewById(R.id.cancelButton);

        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity

        InstructionsArrayAdapter = new ArrayAdapter<String[]>(this,android.R.layout.simple_list_item_1, selectedRecipe.getInstructions())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                String[] instructions = getItem(position);

                if(convertView == null)
                {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.instruction_card,parent, false);
                }

                TextView instructionNumber = (TextView) convertView.findViewById(R.id.instruction_number);
                TextView instruction = (TextView) convertView.findViewById(R.id.instruction);
                TextView subInstruction = (TextView) convertView.findViewById(R.id.sub_instruction);

                instruction.setText(instructions[0]);
                subInstruction.setText(instructions[1]);

                return convertView;
            }
        };

        listView.setAdapter(InstructionsArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedInstruction =  (String) listView.getItemAtPosition(position);
                //showEditInstructionWindow(selectedIngredient, false);
            }
        });

        // button to save the edits
        saveButton.setOnClickListener(new View.OnClickListener()
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
                // show the pop-up to add a new instruction
            }
        });

        // button to cancel edits
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    public void saveEdits(View view)
    {
        String result;
        EditText editName = (EditText)findViewById(R.id.recipe_name_edit);

        result = validateRecipeData();

        if (result == null)
        {
            selectedRecipe.setName(editName.getText().toString());
            result = accessRecipes.updateRecipe(selectedRecipe);

            if (result == null)
            {
                finish();
                //TODO get rid of if nothing else is updated
            }
            else
            {
                Messages.fatalError(this, result);
            }
        }

        else
        {
            Messages.warning(this, result);
        }
    }

    private String validateRecipeData()
    {
        return null;
    }
}
