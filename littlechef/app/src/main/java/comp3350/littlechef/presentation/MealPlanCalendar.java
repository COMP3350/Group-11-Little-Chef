package comp3350.littlechef.presentation;


import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.MealPlan;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//TODO: (Currently working on): remove an object

public class MealPlanCalendar extends AppCompatActivity
{
    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter recipeArrayAdapter;
    private Recipe recipe;
    private MealPlan mealPlan;

    private ArrayList allDaysList; //list printing to listview

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        setTime();

        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList);
        //check database
        if(result != null)
        {
            Messages.fatalError(this, result);
        }

        //initialize the meal plan
        mealPlan = new MealPlan();
        allDaysList = mealPlan.combineLists();
        Toast.makeText(MealPlanCalendar.this, " "+mealPlan.sizeSunday(), Toast.LENGTH_SHORT).show();

        final ListView listView = (ListView) findViewById(R.id.all_list_view);

        recipeArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allDaysList)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                //if its a recipe
                if(getItem(position) instanceof Recipe)
                {
                    recipe = (Recipe) getItem(position);
                    if (convertView == null)
                    {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_card, parent, false);
                    }

                    setValues(convertView);

                    //return convertView;
                }
                else
                {
                    if (convertView == null)
                    {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.day_card, parent, false);
                    }
                    setValuesDay(convertView, getItem(position).toString());
                    //return convertView;
                }
                return convertView;

            }};//adapter end
        listView.setAdapter(recipeArrayAdapter);

        //list view clicker
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(MealPlanCalendar.this, " ", Toast.LENGTH_SHORT).show();
            }
        });

        //Edit button
        Button editPlanButton= (Button) findViewById(R.id.editMealPlanButton);
        editPlanButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
            public void onClick(View view)
            {
                allDaysList.remove(2);
                recipeArrayAdapter.notifyDataSetChanged();

            }
        });

    }

    //sets above bar week time
    private void setTime()
    {
        String pattern = "MMMM dd yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        TextView textView = findViewById(R.id.weekOfTV);
        textView.setText("Week of "+date);
    }

    //set days value
    private void setValuesDay(View convertView, String day)
    {

        TextView name = (TextView) convertView.findViewById(R.id.day_name);
        name.setText(day);

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
        //Intent intent = new Intent(this, EditMealPlan.class);
        //intent.putExtra("id", mealPlan); //pass the object reference to another activity
        //startActivity(intent);
        //mealPlan.removeFrom("Sunday", 0);
        //allDaysList = mealPlan.combineLists();
        //recipeArrayAdapter.notifyDataSetChanged();
    }
}