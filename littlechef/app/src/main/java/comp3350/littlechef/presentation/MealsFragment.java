package comp3350.littlechef.presentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;

public class MealsFragment extends Fragment
{

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

        Button supriseMeButton = (Button) view.findViewById(R.id.supriseMeButton);

        //button listener for add recipe
        supriseMeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                supriseMeClicked();
            }
        });

        return view;

    }

    private void supriseMeClicked()
    {
        String type = "suprise";
        Intent intent = new Intent(getActivity(), MealPlanCalendar.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    //to edit a meal plan
    public void editPlanOnClick()
    {
        Messages.warning(getActivity(), "Implement");
    }

}