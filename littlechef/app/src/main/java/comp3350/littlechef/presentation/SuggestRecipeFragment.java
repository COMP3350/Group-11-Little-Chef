package comp3350.littlechef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import comp3350.littlechef.R;

// CLASS: SuggestRecipeFragment.java
// REMARKS: This class will start an activity based on the type selected
//-----------------------------------------
public class SuggestRecipeFragment extends Fragment
{

    public SuggestRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //inflate view
        View view = inflater.inflate(R.layout.fragment_suggest_recipes, container, false);

        Button challengeButton = view.findViewById(R.id.challengeButton);
        Button easeButton = view.findViewById(R.id.easeButton);
        Button savingTimeButton = view.findViewById(R.id.savingTimeButton);
        Button tasteButton = view.findViewById(R.id.tasteButton);
        Button surpriseMeButton = view.findViewById(R.id.supriseMeButton);

        challengeButton.setOnClickListener(view1 -> {
            String type = "challenge";
            startActivity(type);
        });

        easeButton.setOnClickListener(view12 -> {
            String type = "ease";
            startActivity(type);
        });

        savingTimeButton.setOnClickListener(view13 -> {
            String type = "savingTime";
            startActivity(type);
        });

        tasteButton.setOnClickListener(view14 -> {
            String type = "taste";
            startActivity(type);
        });

        surpriseMeButton.setOnClickListener(view15 -> {
            String type = "surprise";
            startActivity(type);
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