package comp3350.littlechef.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.business.FilteredRecipes;
import comp3350.littlechef.objects.Recipe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

// CLASS: SuggestRecipesActivity.java
// REMARKS: This class will call and display a list of selected types
//-----------------------------------------
public class SuggestRecipesActivity extends AppCompatActivity
{
    private static final int MAX_SHOWING = 3;

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private Recipe recipe;

    private ArrayList<Recipe> displayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_suggestions);

        //access database
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList);

        if(result != null)
        {
            Messages.fatalError(this, result);
        }

        //retrieve type from previous view
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("type");

        //create arraylist with type
        displayList = FilteredRecipes.getListofRecipesByType(type, MAX_SHOWING, recipeList);

        //if the displayList returns empty send warning that nothing to display
        if(displayList.isEmpty())
            emptyList();

        final ListView listView = (ListView) findViewById(R.id.recipe_meal_suggestion);
        recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_1, displayList)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                recipe = getItem(position);
                if(convertView == null)
                {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_card,parent, false);
                }
                setValues(convertView);

                return convertView;
            }
        };

        listView.setAdapter(recipeArrayAdapter);

        //if clicked send to detailed recipe to start cooking
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Recipe selectedRecipe = (Recipe) listView.getItemAtPosition(position);
            Intent detailedRecipe = new Intent(SuggestRecipesActivity.this, DetailedRecipeActivity.class);
            detailedRecipe.putExtra("id", selectedRecipe); //pass the object reference to another activity
            startActivity(detailedRecipe);
        });

    }

    private void setValues(View convertView)
    {
        String difficultyRating = recipe.getDifficultyRating();
        String tasteRating = recipe.getTasteRating();

        TextView name = convertView.findViewById(R.id.recipe_name);
        TextView estimatedTime = convertView.findViewById(R.id.estimated_time);
        TextView difficulty = convertView.findViewById(R.id.difficulty);
        TextView taste = convertView.findViewById(R.id.taste);
        TextView rating = convertView.findViewById(R.id.rating);

        SpannableString recipeNameFormatted = new SpannableString(recipe.getName());
        recipeNameFormatted.setSpan(new UnderlineSpan(), 0, recipeNameFormatted.length(), 0);
        recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);

        name.setText(recipeNameFormatted);
        estimatedTime.setText(recipe.getAverageCookingTime());
        difficulty.setText(difficultyRating);
        taste.setText(tasteRating);
        rating.setText(recipe.getRatingString());
    }

    private void emptyList()
    {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("No results.");
        resetAlert.setMessage("No recipes were found with selected preference.");

        resetAlert.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        resetAlert.show();
    }
}