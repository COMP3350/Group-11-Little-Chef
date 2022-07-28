package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

// CLASS: RateRecipeActivity.java
// REMARKS: This class is for the user to rate the recipe they just cooked.
//-----------------------------------------
public class RateRecipeActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;
    private RadioGroup difficultyRadioGroup;
    private RadioGroup tasteRadioGroup;
    private RadioButton radioButtonDifficulty;
    private RadioButton radioButtonTaste;
    private AccessRecipes accessRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_recipe);

        accessRecipes = new AccessRecipes();

        difficultyRadioGroup =  findViewById(R.id.difficulty_radio_group);
        tasteRadioGroup = findViewById(R.id.taste_radio_group);

        //get the selected recipe that was clicked from previous activity
        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity

        setValues();
    }


    public void submitClicked(View view)
    {
        int tasteRatingId = tasteRadioGroup.getCheckedRadioButtonId();
        int difficultyRatingId = difficultyRadioGroup.getCheckedRadioButtonId();

        radioButtonTaste = findViewById(tasteRatingId);
        radioButtonDifficulty = findViewById(difficultyRatingId);

        double tasteRating = Double.parseDouble(radioButtonTaste.getText().toString());
        double difficultyRating = Double.parseDouble(radioButtonDifficulty.getText().toString());

        String result;

        selectedRecipe.addTasteRating(tasteRating);
        selectedRecipe.addDifficultyRating(difficultyRating);
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

    private void setValues()
    {
        String difficultyRating =selectedRecipe.getDifficultyRating();
        String tasteRating = selectedRecipe.getTasteRating();

        //Fills values for header
        TextView estimatedTime = findViewById(R.id.estimated_time_rating_activity);
        TextView difficulty = findViewById(R.id.difficulty_rating_activity);
        TextView taste = findViewById(R.id.taste_rating_activity);
        TextView rating = findViewById(R.id.rating_value_rating_activity);


        estimatedTime.setText(selectedRecipe.getAverageCookingTime());
        difficulty.setText(difficultyRating);
        taste.setText(tasteRating);
        rating.setText(selectedRecipe.getRatingString());


    }
}