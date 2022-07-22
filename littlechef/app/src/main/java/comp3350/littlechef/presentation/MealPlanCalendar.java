package comp3350.littlechef.presentation;


import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


//TODO: (Currently working on): 

public class MealPlanCalendar extends AppCompatActivity
{
    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private Recipe recipe;

    private ArrayList<Recipe> sundayList;
    private ArrayList<Recipe> mondayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList);
        //check database
        if(result != null)
        {
            Messages.fatalError(this, result);
        }

        //adding some views to list
        sundayList = new ArrayList<Recipe>();
        sundayList.add(recipeList.get(0));
        sundayList.add(recipeList.get(1));

        mondayList = new ArrayList<Recipe>();
        mondayList.add(recipeList.get(2));
        mondayList.add(recipeList.get(3));

        makeListViews(R.id.sunday_list_view, sundayList);
        makeListViews(R.id.monday_list_view, mondayList);

        //Edit button
        Button editPlanButton= (Button) findViewById(R.id.editMealPlanButton);
        editPlanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                editPlanOnClick();
            }
        });

    }

    //this makes the list views for each day
    private void makeListViews(int id, ArrayList<Recipe> dayList)
    {
        final ListView listView = (ListView) findViewById(id);

        recipeArrayAdapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_1, dayList)
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
            }};
        listView.setAdapter(recipeArrayAdapter);
    }

    private void setValues(View convertView)
    {
        String difficultyRating = recipe.getDifficultyRating();
        String tasteRating = recipe.getTasteRating();

        TextView name = (TextView) convertView.findViewById(R.id.recipe_name);
        TextView estimatedTime = (TextView) convertView.findViewById(R.id.estimated_time);
        TextView difficulty = (TextView) convertView.findViewById(R.id.difficulty);
        TextView taste = (TextView) convertView.findViewById(R.id.taste);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);

        SpannableString recipeNameFormatted = new SpannableString(recipe.getName());
        recipeNameFormatted.setSpan(new UnderlineSpan(), 0, recipeNameFormatted.length(), 0);
        recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);

        name.setText(recipeNameFormatted);
        estimatedTime.setText(recipe.getAverageCookingTime());
        difficulty.setText(difficultyRating);
        taste.setText(tasteRating);
        rating.setText(recipe.getRatingString());
    }

    //to edit a meal plan
    public void editPlanOnClick()
    {
        Messages.warning(this, "Implement");
    }


}