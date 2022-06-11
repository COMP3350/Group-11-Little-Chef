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

    
    private void setValues()
    {
        //TODO make it fill values
        TextView recipeName = (TextView) findViewById(R.id.recipeName);


        recipeName.setText(selectedRecipe.getName());
    }
}
