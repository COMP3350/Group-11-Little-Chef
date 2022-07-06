package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.objects.Recipe;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

public class RateRecipyActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_recipy);

        //get the selected recipe that was clicked from previous activity
        Intent previousIntent = getIntent();
        selectedRecipe = (Recipe) previousIntent.getSerializableExtra("id"); // will never return null, since some recipe was clicked in prev activity
    }


    public void submitClicked(View view)
    {
       //TODO save the ratings
        finish();
    }

    private void setValues()
    {
        String ratingString = "Rating: 5/5";

        //Fills values for header
        TextView estimatedTime = (TextView) findViewById(R.id.estimated_time_rating_activity);
        TextView difficulty = (TextView) findViewById(R.id.difficulty_rating_activity);
        TextView taste = (TextView) findViewById(R.id.taste_rating_activity);
        TextView rating = (TextView) findViewById(R.id.rating_value_rating_activity);


        estimatedTime.setText(selectedRecipe.getAverageCookingTime());
        difficulty.setText(selectedRecipe.getDifficultyString());
        taste.setText(selectedRecipe.getQualityString());
        rating.setText(selectedRecipe.getRatingString());


    }
}