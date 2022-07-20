package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.business.ScaleRecipe;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

//        //setting up drop down menu for choosing serving
//        ArrayAdapter<String> servingSizeAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.serving_sizes));
//        servingNum = (Spinner) findViewById(R.id.serving_num);
//        servingSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        servingNum.setAdapter(servingSizeAdapter);


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