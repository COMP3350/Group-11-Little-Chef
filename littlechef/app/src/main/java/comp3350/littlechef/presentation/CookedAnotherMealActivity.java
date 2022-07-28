package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.objects.Recipe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// CLASS: CookedAnotherMealActivity.java
// REMARKS: Shows a transition screen after the user finished cooking a recipe.
//-----------------------------------------------------------------------
public class CookedAnotherMealActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooked_another_meal);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent rateRecipyActivity = new Intent(CookedAnotherMealActivity.this, RateRecipeActivity.class);
            Intent previousIntent = getIntent();

            Recipe selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity

            rateRecipyActivity.putExtra("id", selectedRecipe); //pass the object reference to another activity
            startActivity(rateRecipyActivity);
            finish();
        }, 1500);

    }
}