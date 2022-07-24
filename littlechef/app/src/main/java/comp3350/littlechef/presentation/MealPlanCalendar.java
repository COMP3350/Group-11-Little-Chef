package comp3350.littlechef.presentation;


import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;

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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//TODO: (Currently working on): edit button

public class MealPlanCalendar extends AppCompatActivity
{
    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter recipeArrayAdapter;
    private Recipe recipe;

    private ArrayList allDaysList;
    private ArrayList<Recipe> sundayList;
    private ArrayList<Recipe> mondayList;
    private ArrayList tuesdayList;
    private ArrayList wednesdayList;
    private ArrayList thursdayList;
    private ArrayList fridayList;
    private ArrayList saturdayList;

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
        createInitialList();
        addMealsToDays(); //test case
        combineLists();

        ListView listView = (ListView) findViewById(R.id.all_list_view);

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

                    return convertView;
                }
                else
                {
                    if (convertView == null)
                    {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.day_card, parent, false);
                    }
                    setValuesDay(convertView, getItem(position).toString());
                    return convertView;
                }

            }};//adapater end
        listView.setAdapter(recipeArrayAdapter);

        //Edit button
        Button editPlanButton= (Button) findViewById(R.id.editMealPlanButton);
        editPlanButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
            public void onClick(View view)
            {
                //editPlanOnClick();
                sundayList.remove(0);
                allDaysList.clear();
                //allDaysList.add("TEST1");
                allDaysList.add("Sunday");
                allDaysList.addAll(sundayList);
                recipeArrayAdapter.notifyDataSetChanged();
            }
        });

    }

    //JUST TESTING REMOVE LATER
    private void addMealsToDays()
    {
        sundayList.add(recipeList.get(0));
        sundayList.add(recipeList.get(1));
        mondayList.add(recipeList.get(1));
        tuesdayList.add(recipeList.get(2));
        wednesdayList.add(recipeList.get(0));
        thursdayList.add(recipeList.get(1));
        fridayList.add(recipeList.get(2));
        saturdayList.add(recipeList.get(0));
    }

    private void combineLists()
    {
        allDaysList.add("Sunday");
        allDaysList.addAll(sundayList);
        allDaysList.add("Monday");
        allDaysList.addAll(mondayList);
        //allDaysList.add("Tuesday");
        //allDaysList.addAll(tuesdayList);
        //allDaysList.add(tuesdayList.size(),"Wednesday");
        //allDaysList.addAll(wednesdayList);
        //allDaysList.add(wednesdayList.size(),"Thursday");
        //allDaysList.addAll(thursdayList);
        //allDaysList.add(thursdayList.size(),"Friday");
        //allDaysList.addAll(fridayList);
        //allDaysList.add(fridayList.size(),"Saturday");
        //allDaysList.addAll(saturdayList);
    }

    private void createInitialList()
    {
        allDaysList = new ArrayList();
        sundayList = new ArrayList();
        mondayList = new ArrayList();
        tuesdayList = new ArrayList();
        wednesdayList = new ArrayList();
        thursdayList = new ArrayList();
        fridayList = new ArrayList();
        saturdayList = new ArrayList();
    }

    private void setTime()
    {
        String pattern = "MMMM dd yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        TextView textView = findViewById(R.id.weekOfTV);
        textView.setText("Week of "+date);
    }
    /*
    //this makes the list views for each day
    private void makeListViews(int id, ArrayList dayList)
    {
        final ListView listView = (ListView) findViewById(id);

        recipeArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dayList)
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

                    return convertView;
                }
                else
                {
                    if (convertView == null)
                    {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.day_card, parent, false);
                    }
                    setValuesDay(convertView, getItem(position).toString());
                    return convertView;
                }
            }};
        listView.setAdapter(recipeArrayAdapter);
    }

    */

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
        Intent intent = new Intent(this, EditMealPlan.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)sundayList);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);

    }
}