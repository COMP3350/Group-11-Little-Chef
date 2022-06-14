package comp3350.littlechef.presentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;

public class DetailedRecipeActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;
    private ArrayAdapter<Ingredient> ingredientsArrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_recipe_activity);

        Toast.makeText(DetailedRecipeActivity.this, "Click on any ingredient to read the recipe!",Toast.LENGTH_SHORT).show(); //pop-up hint message for the user

        final ListView listView = (ListView) findViewById(R.id.ingredients_list);


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
                measurement.setText(ingredient.getAmount() + " " + ingredient.getMeasurement());

                return convertView;
            }
        };


        listView.setAdapter(ingredientsArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

            }

        });
        setValues();
    }


    //to set values in the detailed_recipe_view
    private void setValues()
    {
        //Fills values for header
        TextView recipeName = (TextView) findViewById(R.id.recipeName);
        TextView estimatedTime = (TextView) findViewById(R.id.estimatedTime);
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
        estimatedTime.setText(selectedRecipe.getTimeToMakeString());
        difficulty.setText(selectedRecipe.getDifficultyString());
        taste.setText(selectedRecipe.getQualityString());
        rating.setText("Rating: " + selectedRecipe.getRatingString());
        textIngredient.setText(textIngredientsFormatted);


    }
}
