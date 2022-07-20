package comp3350.littlechef.presentation;

import android.graphics.Typeface;
import android.os.Bundle;

//import androidx.fragment.app.Fragment;
import android.app.Fragment;

import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.MealPlanDay;


public class MealsFragment extends Fragment {

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayList<Recipe> mealPlan;
    private ArrayAdapter<Recipe> recipeArrayAdapter;

    public MealsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //inflate view
        View view = inflater.inflate(R.layout.fragment_meals, container, false);

        //database
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        mealPlan = new ArrayList<MealPlanDay>();
        String result = accessRecipes.getRecipes(recipeList);

        //for now add starting recipe. IF THERE IS ONE IN DATABASE
        //mealPlan.add( recipeList.get(0) );//adds just a starting recipe

        ListView mealListView = (ListView) view.findViewById(R.id.plan_list_view);


        //check if received list of recipes
        if(result != null)
        {
            Messages.fatalError(getActivity(), result);
        }
        else
        {

            final ListView listView = (ListView) view.findViewById(R.id.plan_list_view);

            recipeArrayAdapter = new ArrayAdapter<Recipe>(getActivity(), android.R.layout.simple_list_item_1, mealPlan)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    //recipe = getItem(position);
                    if(convertView == null)
                    {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.meal_plan_layout,parent, false);
                    }

                    setValues(convertView);

                    return convertView;
                }
            };

            listView.setAdapter(recipeArrayAdapter);






        }

        //Edit button
        Button editPlanButton= (Button) view.findViewById(R.id.editMealPlanButton);
        editPlanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                editPlanOnClick();
            }
        });

        return view;

    }
    //sets values for meal plan day
    private void setValues(View convertView)
    {


        TextView name1 = (TextView) convertView.findViewById(R.id.textView1);
        TextView name2 = (TextView) convertView.findViewById(R.id.textView2);
        TextView name3 = (TextView) convertView.findViewById(R.id.textView3);

        name1.setText(mealPlan.get(0).getName());
        name2.setText("test2");
        name3.setText("test3");
    }

    //to edit a meal plan
    public void editPlanOnClick()
    {
        Messages.warning(getActivity(), "Implement");
    }




}