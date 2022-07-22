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

    //to edit a meal plan
    public void editPlanOnClick()
    {
        Messages.warning(getActivity(), "Implement");
    }

}