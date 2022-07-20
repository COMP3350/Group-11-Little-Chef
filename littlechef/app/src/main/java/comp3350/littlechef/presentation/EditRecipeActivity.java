package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.business.ScaleRecipe;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditRecipeActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;
    private ArrayAdapter<Ingredient> ingredientsArrayAdapter;
    private AccessRecipes accessRecipes;
    private Spinner servingNum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        accessRecipes = new AccessRecipes();

        final ListView listView = (ListView) findViewById(R.id.ingredients_list_edit);

        //get the selected recipe that was clicked from previous activity
        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity

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


                ingredientName.setText(ingredient.getName());
                measurement.setText(ingredient.getDisplayMeasurement());

                return convertView;
            }
        };



        listView.setAdapter(ingredientsArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Ingredient selectedIngredient = (Ingredient) listView.getItemAtPosition(position);
                showEditIngrWindow(selectedIngredient.getName(), Double.toString(selectedIngredient.getAmount()), "1");

            }
        });

        setValues();
    }

    public void saveClicked(View view)
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

    private void showEditIngrWindow(String ingrName, String ingrAmount, String unit)
    {
        final Dialog editIngredientWindow = new Dialog(EditRecipeActivity.this);

        //no need for title
        editIngredientWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //make it disappear if user click outside of the window
        editIngredientWindow.setCancelable(true);

        //set the layout
        editIngredientWindow.setContentView(R.layout.edit_ingredient_window);

        final EditText ingrNameField = (EditText) (editIngredientWindow).findViewById(R.id.editIngredient);
        final EditText ingrAmountField = (EditText) (editIngredientWindow).findViewById(R.id.editAmount);
        final Spinner unitField = (Spinner) (editIngredientWindow).findViewById(R.id.editIngredientUnit);
        final Button saveEdit = (Button) (editIngredientWindow).findViewById(R.id.save_dialog_box);
        final Button cancelEdit = (Button) (editIngredientWindow).findViewById(R.id.cancel_dialog_box);
        final Button deleteIngr = (Button) (editIngredientWindow).findViewById(R.id.delete_dialog_box);

        ingrNameField.setText(ingrName);
        ingrAmountField.setText(ingrAmount);

        editIngredientWindow.show();
    }


    //to set values in the detailed_recipe_view
    private void setValues()
    {
        String difficultyRating = selectedRecipe.getDifficultyRating();
        String tasteRating = selectedRecipe.getTasteRating();

        //Fills values for header
        TextView recipeName = (TextView) findViewById(R.id.recipe_name_edit);
        TextView estimatedTime = (TextView) findViewById(R.id.estimated_time_edit);
        TextView difficulty = (TextView) findViewById(R.id.difficulty_edit);
        TextView taste = (TextView) findViewById(R.id.taste_edit);
        TextView rating = (TextView) findViewById(R.id.rating_edit);

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
        EditText editName = (EditText)findViewById(R.id.recipe_name_edit);
        if(editName.getText().length() == 0)
        {
            return "Recipe name required";
        }

        return null;
    }
}