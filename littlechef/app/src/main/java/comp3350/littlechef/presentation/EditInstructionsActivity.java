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
    private int instructionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_instruction);
        accessRecipes = new AccessRecipes();

        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity

        final ListView listView = (ListView) findViewById(R.id.instruction_list_edit_view);
        Button saveButton = (Button) findViewById(R.id.save_button);
        Button addInstructionButton = (Button) findViewById(R.id.add_button);
        Button cancelButton= (Button) findViewById(R.id.cancel_button);

        //button listener for adding an instruction to a recipe
        addInstructionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // show the pop-up to add a new instruction
                addEditClicked(view);
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


        InstructionsArrayAdapter = new ArrayAdapter<String[]>(this,android.R.layout.simple_list_item_1, selectedRecipe.getInstructions())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                String[] instruction = getItem(position);
                instructionIndex = position;
                String number = (instructionIndex + 1) + "."; //get the order number of the instruction
                SpannableString numberBold;
                SpannableString mainInstructionBold;

                if(convertView == null)
                {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.instruction_card,parent, false);
                }

                //make the number bold
                numberBold = new SpannableString(number);
                numberBold.setSpan(new StyleSpan(Typeface.BOLD), 0, numberBold.length(), 0);

                //make main instruction bold
                mainInstructionBold = new SpannableString(instruction[0]);
                mainInstructionBold.setSpan(new StyleSpan(Typeface.BOLD), 0, mainInstructionBold.length(), 0);

                TextView mainInstruction = (TextView) convertView.findViewById(R.id.instruction);
                TextView subInstruction = (TextView) convertView.findViewById(R.id.sub_instruction);
                TextView numberOfInstruction = (TextView) convertView.findViewById(R.id.instruction_number);

                mainInstruction.setText(mainInstructionBold);
                subInstruction.setText(instruction[1]);
                numberOfInstruction.setText(numberBold);

                return convertView;
            }
        };

        listView.setAdapter(InstructionsArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String[] selectedInstruction =  (String[]) listView.getItemAtPosition(position);
                showEditInstructionWindow(selectedInstruction, false);
            }
        });

    }//end
    
    public void addEditClicked(View view)
    {
        String[] newInstruction = new String[2];
        showEditInstructionWindow(newInstruction, true);
    }
    
    private void showEditInstructionWindow(String[] instruction, boolean newRecipe)
    {
        final Dialog editInstructionWindow = new Dialog(EditInstructionsActivity.this, android.R.style.Theme_Light_NoTitleBar_Fullscreen);

        //no need for title
        editInstructionWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //make it disappear if user click outside of the window
        editInstructionWindow.setCancelable(true);

        //set the layout
        editInstructionWindow.setContentView(R.layout.edit_instruction_window);

        final EditText instructionField = (EditText) (editInstructionWindow).findViewById(R.id.instruction);
        final EditText subInstructionField = (EditText) (editInstructionWindow).findViewById(R.id.sub_instruction);
        final Button saveEdit = (Button) (editInstructionWindow).findViewById(R.id.save_dialog_button);
        final Button cancelEdit = (Button) (editInstructionWindow).findViewById(R.id.cancel_dialog_button);
        final Button deleteInstruction = (Button) (editInstructionWindow).findViewById(R.id.delete_dialog_button);

        //set up the buttons
        cancelEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(editInstructionWindow.isShowing())
                {
                    editInstructionWindow.dismiss();
                }
            }
        });

        deleteInstruction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ArrayList<String[]> recipeInstructions = selectedRecipe.getInstructions();
                int instructionIndex = recipeInstructions.indexOf(instruction);

                if(instructionIndex > -1)
                {
                    recipeInstructions.remove(instructionIndex);
                    InstructionsArrayAdapter.notifyDataSetChanged();
                    editInstructionWindow.dismiss();

                }
            }
        });

        saveEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // update
                instruction[0] = instructionField.getText().toString();
                instruction[1] = subInstructionField.getText().toString();

                if(newRecipe)
                {
                    selectedRecipe.addInstructions(instruction[0], instruction[1]);
                }

                InstructionsArrayAdapter.notifyDataSetChanged();
                editInstructionWindow.dismiss();
            }
        });

        //set up the editText listeners
        instructionField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                saveEdit.setEnabled( (instructionField.getText().toString().trim().length() > 0)) ;
            }
        });

        subInstructionField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                saveEdit.setEnabled( (instructionField.getText().toString().trim().length() > 0));
            }
        });

        instructionField.setText(instruction[0]);
        subInstructionField.setText(instruction[1]);

        if(newRecipe)
        {
            deleteInstruction.setVisibility(View.GONE);
        }

        editInstructionWindow.show();
        

    }

    public void saveEdits(View view)
    {
        String result;
        result = validateRecipeData();
        if (result == null)
        {
            result = accessRecipes.updateRecipe(selectedRecipe);
            if (result == null)
            {
                finish();
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
