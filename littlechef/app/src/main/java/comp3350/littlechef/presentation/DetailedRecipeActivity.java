package comp3350.littlechef.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.business.TimeRecipe;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.business.ScaleRecipe;

// CLASS: DetailedRecipeActivity.java
//
//
// REMARKS: This class creates the view for the ingredients list for a recipe.
//
//-----------------------------------------

public class DetailedRecipeActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;
    private ArrayAdapter<Ingredient> ingredientsArrayAdapter;
    private Spinner servingNum;
    private Button startCookingButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_recipe);


        final ListView listView = (ListView) findViewById(R.id.ingredients_list);

        //setting up drop down menu for choosing serving
        ArrayAdapter<String> servingSizeAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.serving_sizes));
        servingNum = (Spinner) findViewById(R.id.serving_num);
        servingSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servingNum.setAdapter(servingSizeAdapter);


        //get the selected recipe that was clicked from previous activity
        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity
        
        ingredientsArrayAdapter = new ArrayAdapter<Ingredient>(this,android.R.layout.simple_list_item_1, new ArrayList<Ingredient>())
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

        startCookingButton = (Button) findViewById(R.id.start_cooking);
        startCookingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent readInstuction= new Intent(DetailedRecipeActivity.this, RecipeInstructionActivity.class);
                readInstuction.putExtra("id", selectedRecipe); //pass the object reference to another activity
                startActivity(readInstuction);
                finish(); //finish detailed recipe activity after reading instruction(cooking) is done
            }
        });

        servingNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                String num = servingNum.getItemAtPosition(pos).toString().split(" ")[0];
                int numServings = Integer.parseInt(num);

                ingredientsArrayAdapter.clear();
                ingredientsArrayAdapter.addAll(ScaleRecipe.scaleIngredients(selectedRecipe, numServings));
                ingredientsArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        setValues();
    }


    //to set values in the detailed_recipe_view
    private void setValues()
    {
        String difficultyRating = selectedRecipe.getDifficultyRating();
        String tasteRating = selectedRecipe.getTasteRating();

        //Fills values for header
        TextView recipeName = (TextView) findViewById(R.id.recipe_name);
        TextView estimatedTime = (TextView) findViewById(R.id.estimated_time);
        TextView difficulty = (TextView) findViewById(R.id.difficulty);
        TextView taste = (TextView) findViewById(R.id.taste);
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView textIngredient = (TextView) findViewById(R.id.text_ingredients);

        //make recipe name bold
        SpannableString recipeNameFormatted = new SpannableString(selectedRecipe.getName());
        recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);

        //make "Ingredients" bold
        SpannableString textIngredientsFormatted = new SpannableString("Ingredients");
        textIngredientsFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, textIngredientsFormatted.length(), 0);

        recipeName.setText(recipeNameFormatted);
        estimatedTime.setText(selectedRecipe.getAverageCookingTime());
        difficulty.setText(difficultyRating);
        taste.setText(tasteRating);
        rating.setText(selectedRecipe.getRatingString());
        textIngredient.setText(textIngredientsFormatted);


    }

    public void deleteClicked(View view)
    {
        AlertDialog.Builder deleteAlert = new AlertDialog.Builder(this);
        deleteAlert.setTitle("Delete Recipe");
        deleteAlert.setMessage("Are you sure you want delete " + selectedRecipe.getName() + "?" );

        deleteAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String result;
                AccessRecipes accessRecipes = new AccessRecipes();
                result = accessRecipes.deleteRecipe(selectedRecipe);
                if (result == null)
                {
                    Toast.makeText(DetailedRecipeActivity.this, "Deleted Recipe: " + selectedRecipe.getName(), Toast.LENGTH_SHORT).show();
                    finish();
                }

                else
                {
                    Messages.fatalError(DetailedRecipeActivity.this, result);
                }
            }
        });

        deleteAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //do nothing
            }
        });

        deleteAlert.show();
    }
}
