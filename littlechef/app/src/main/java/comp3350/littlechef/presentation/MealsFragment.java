package comp3350.littlechef.presentation;

import android.os.Bundle;

//import androidx.fragment.app.Fragment;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;


public class MealsFragment extends Fragment {

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayList<Recipe> mealPlan;

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
        mealPlan = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList);

        ListView mealListView = (ListView) view.findViewById(R.id.plan_list_view);


        //check if received list of recipes
        if(result != null)
        {
            Messages.fatalError(getActivity(), result);
        }
        else
        {
            //for now just add 1 recipe to a meal plan, ASSUMING YOU HAVE ONE IN STORAGE
            mealPlan.add( recipeList.get(0) );
            //do listview stuff
            //possible issues with getActivity() -try-> getContext or something
            MealPlanListAdapter adapter = new MealPlanListAdapter(getActivity(), R.layout.meal_plan_layout, mealPlan);
            mealListView.setAdapter(adapter);
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


        ListView listView = (ListView) view.findViewById(R.id.plan_list_view);


        return view;

    }

    //to edit a meal plan
    public void editPlanOnClick()
    {
        Messages.warning(getActivity(), "Implement");
    }


}