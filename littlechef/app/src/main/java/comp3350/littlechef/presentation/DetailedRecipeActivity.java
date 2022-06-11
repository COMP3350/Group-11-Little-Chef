package comp3350.littlechef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.littlechef.R;
import comp3350.littlechef.objects.Recipe;

public class DetailedRecipeActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_recipe_activity);

        //get the selected shape
        Intent previousIntent = getIntent();
        String recipeId = previousIntent.getStringExtra("id");
        selectedRecipe = HomeActivity.getViewFragment().getRecipeList().get(Integer.parseInt((recipeId)));

        setValues();
    }


    //to set values in the detailed_recipe_view
    private void setValues()
    {
        //Fills values for header
        TextView recipeName = (TextView) findViewById(R.id.recipeName);
        //TextView estimatedTime = (TextView) findViewById(R.id.estimatedTime); //****FIGURE OUT TIME STUFF, error?
        TextView difficulty = (TextView) findViewById(R.id.difficulty);
        TextView taste = (TextView) findViewById(R.id.taste);
        TextView rating = (TextView) findViewById(R.id.rating);

        recipeName.setText(selectedRecipe.getName());
        //estimatedTime.setText(selectedRecipe.getTimeToMakeMins());
        difficulty.setText(selectedRecipe.getDifficultyString());
        taste.setText(selectedRecipe.getQualityString());
        rating.setText("Rating: "+selectedRecipe.getRatingString()); //might not need Rating here?


        //fills ingredients list
        String[] menuItems = {"apple", "banana", "orange"};

    }
}
