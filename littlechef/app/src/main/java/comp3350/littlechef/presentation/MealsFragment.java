package comp3350.littlechef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import comp3350.littlechef.R;

// CLASS: MealsFragment.java
//
//
// REMARKS: This class will start an activity based on the type selected
//
//-----------------------------------------
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

        Button challengeButton = (Button) view.findViewById(R.id.challengeButton);
        Button easeButton = (Button) view.findViewById(R.id.easeButton);
        Button savingTimeButton = (Button) view.findViewById(R.id.savingTimeButton);
        Button tasteButton = (Button) view.findViewById(R.id.tasteButton);
        Button surpriseMeButton = (Button) view.findViewById(R.id.supriseMeButton);

        challengeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String type = "challenge";
                startActivity(type);
            }
        });
        easeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String type = "ease";
                startActivity(type);
            }
        });
        savingTimeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String type = "savingTime";
                startActivity(type);
            }
        });
        tasteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String type = "taste";
                startActivity(type);
            }
        });
        surpriseMeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String type = "surprise";
                startActivity(type);
            }
        });

        return view;

    }
    private void startActivity(String type)
    {
        Intent intent = new Intent(getActivity(), SuggestRecipesActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}