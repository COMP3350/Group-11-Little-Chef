package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

// CLASS: EditRecipeActivity.java
// REMARKS: This class is so the user can edit a recipe.
//------------------------------------------------------------------------------
public class EditRecipeActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;
    private ArrayAdapter<Ingredient> ingredientsArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        final ListView listView = findViewById(R.id.ingredients_list_edit);

        //get the selected recipe that was clicked from previous activity
        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity

        Button nextButton = findViewById(R.id.next_button);
        Button addIngredientButton = findViewById(R.id.add_ingredient_button);

        ingredientsArrayAdapter = new ArrayAdapter<Ingredient>(this,android.R.layout.simple_list_item_1, selectedRecipe.getIngredients())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {

                Ingredient ingredient = getItem(position);

                if(convertView == null)
                {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_card,parent, false);
                }

                TextView ingredientName = (TextView) convertView.findViewById(R.id.ingredient);
                TextView measurement = (TextView) convertView.findViewById(R.id.measurement);

                SpannableString ingrName = new SpannableString(ingredient.getName());
                ingrName.setSpan(new UnderlineSpan(), 0, ingrName .length(), 0);

                ingredientName.setText(ingrName);
                measurement.setText(ingredient.getDisplayMeasurement());

                return convertView;
            }
        };

        nextButton.setOnClickListener(view -> nextClicked(view));

        addIngredientButton.setOnClickListener(view -> addEditClicked(view));

        listView.setAdapter(ingredientsArrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Ingredient selectedIngredient = (Ingredient) listView.getItemAtPosition(position);
            showEditIngrWindow(selectedIngredient, false);

        });

        setValues();
    }

    public void nextClicked(View view)
    {

        String result;
        result = validateRecipeData();
        if (result == null)
        {
            EditText editName = findViewById(R.id.recipe_name_edit);
            selectedRecipe.setName(editName.getText().toString());

            Intent EditInstructionsActivity = new Intent(this, EditInstructionsActivity.class);
            EditInstructionsActivity.putExtra("id", selectedRecipe); // pass the object reference to another activity
            startActivity(EditInstructionsActivity);
            finish();
        }

        else
        {
            Messages.warning(this, result);
        }
    }


    public void addEditClicked(View view)
    {
        showEditIngrWindow(new Ingredient("", Unit.QUANTITY,0), true);
    }

    private void showEditIngrWindow(Ingredient ingredient, boolean newRecipe)
    {
        final Dialog editIngredientWindow = new Dialog(EditRecipeActivity.this);

        editIngredientWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
        editIngredientWindow.setCancelable(true);
        editIngredientWindow.setContentView(R.layout.edit_ingredient_window);

        final EditText ingrNameField = (editIngredientWindow).findViewById(R.id.editIngredient);
        final EditText ingrAmountField = (editIngredientWindow).findViewById(R.id.editAmount);
        final Spinner unitField = (editIngredientWindow).findViewById(R.id.editIngredientUnit);
        final Button saveEdit = (editIngredientWindow).findViewById(R.id.save_dialog_box);
        final Button cancelEdit = (editIngredientWindow).findViewById(R.id.cancel_dialog_box);
        final Button deleteIngr = (editIngredientWindow).findViewById(R.id.delete_dialog_box);


        //set the spinner with units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitField.setAdapter(adapter);

        //set the ingredients unit in the spinner

        unitField.setSelection(adapter.getPosition(ingredient.getUnit()));



        //set up the buttons
        cancelEdit.setOnClickListener(v -> {
            if(editIngredientWindow.isShowing())
            {
                editIngredientWindow.dismiss();
            }
        });

        deleteIngr.setOnClickListener(v -> {
            ArrayList<Ingredient> recipeIngredients = selectedRecipe.getIngredients();
            int ingrIndex = recipeIngredients.indexOf(ingredient);
            if(ingrIndex > -1)
            {
                recipeIngredients.remove(ingrIndex);
                ingredientsArrayAdapter.notifyDataSetChanged();
                editIngredientWindow.dismiss();

            }
        });

        saveEdit.setOnClickListener(v -> {

            ingredient.setName(ingrNameField.getText().toString());
            ingredient.setNumberOfIngredients(Double.parseDouble(ingrAmountField.getText().toString()));
            ingredient.setUnit(unitField.getSelectedItem().toString());

            if(newRecipe)
            {
                selectedRecipe.getIngredients().add(0,ingredient);
            }

            ingredientsArrayAdapter.notifyDataSetChanged();
            editIngredientWindow.dismiss();
        });

        //set up the editText listeners
        ingrNameField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                saveEdit.setEnabled( (ingrNameField.getText().toString().trim().length() > 0) && (ingrAmountField.getText().toString().length() > 0)) ;
            }
        });

        ingrAmountField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                saveEdit.setEnabled( (ingrNameField.getText().toString().trim().length() > 0) && (ingrAmountField.getText().toString().length() > 0));
            }
        });

        ingrNameField.setText(ingredient.getName());
        ingrAmountField.setText(Double.toString(ingredient.getNumberOfIngredients()));

        if(newRecipe)
        {
            deleteIngr.setVisibility(View.GONE);
        }

        editIngredientWindow.show();
    }


    //to set values in the detailed_recipe_view
    private void setValues()
    {
        String difficultyRating = selectedRecipe.getDifficultyRating();
        String tasteRating = selectedRecipe.getTasteRating();

        //Fills values for header
        TextView recipeName = findViewById(R.id.recipe_name_edit);
        TextView estimatedTime = findViewById(R.id.estimated_time_edit);
        TextView difficulty = findViewById(R.id.difficulty_edit);
        TextView taste = findViewById(R.id.taste_edit);
        TextView rating = findViewById(R.id.rating_edit);

        //make recipe name bold
        SpannableString recipeNameFormatted = new SpannableString(selectedRecipe.getName());
        recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);


        recipeName.setText(recipeNameFormatted);
        estimatedTime.setText(selectedRecipe.getAverageCookingTime());
        difficulty.setText(difficultyRating);
        taste.setText(tasteRating);
        rating.setText(selectedRecipe.getRatingString());
    }


    private String validateRecipeData()
    {
        EditText editName = findViewById(R.id.recipe_name_edit);
        if(editName.getText().toString().trim().length() == 0)
        {
            return "Recipe name required";
        }

        return null;
    }


}