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
import android.app.Fragment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;

public class DetailedRecipeActivity extends AppCompatActivity
{
    private Recipe selectedRecipe;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_recipe_activity);

        listView = (ListView) findViewById(R.id.ingredients_list); //for ingredient list

        //INGREDIENT LIST SWITCH FOR REAL LIST LATER
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Ingredient 1");
        arrayList.add("Ingredient 2");
        arrayList.add("Ingredient 3");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(DetailedRecipeActivity.this, "clicked item: "+position+" "+arrayList.get(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });

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


    }
}
