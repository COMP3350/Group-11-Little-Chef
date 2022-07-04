package comp3350.littlechef.presentation;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.littlechef.R;
import comp3350.littlechef.objects.Recipe;

// CLASS: AddFragment.java
//
//
// REMARKS: This class creates the add recipe fragment
//
//-----------------------------------------
public class AddFragment extends Fragment
{
    String name;


    public AddFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }



    //TODO: add proper text that recipe was successfully added

    //TODO: insert this blank recipe into DB

    //TODO create list of steps to add

    //TODO add check for duplicates

    //User creates recipe name
    //then user
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        Recipe recipe;

        TextView bannerofIngredient = (TextView) v.findViewById(R.id.textViewIngred);
        EditText ingredName= (EditText) v.findViewById(R.id.ingredName);
        EditText ingredMeasurement= (EditText) v.findViewById(R.id.ingredMeasurement);
        EditText ingredAmount= (EditText) v.findViewById(R.id.ingredAmount);
        Button ingredButton= (Button) v.findViewById(R.id.ingredButton);
        Button startIngredients= (Button) v.findViewById(R.id.startIngredients);
        Button resetButton= (Button) v.findViewById(R.id.resetButton);

        //Initial all the add ingredient stuff should not be visible until recipe object created, ie button clicked
        bannerofIngredient.setVisibility(View.GONE);
        ingredName.setVisibility(View.GONE);
        ingredMeasurement.setVisibility(View.GONE);
        ingredAmount.setVisibility(View.GONE);
        ingredButton.setVisibility(View.GONE);

        //Get input from text fields
        EditText recipeInput = (EditText) v.findViewById(R.id.nameInput);

        //button listener for start Ingredients, will hide that button and show add ingredients stuff
        startIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                bannerofIngredient.setVisibility(View.VISIBLE);
                ingredName.setVisibility(View.VISIBLE);
                ingredMeasurement.setVisibility(View.VISIBLE);
                ingredAmount.setVisibility(View.VISIBLE);
                ingredButton.setVisibility(View.VISIBLE);
                startIngredients.setVisibility(View.GONE);



            }
        });














        //when reset button is clicked, wipe field boxes and hide again
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //TODO: create function to auto hide these
                bannerofIngredient.setVisibility(View.GONE);
                ingredName.setVisibility(View.GONE);
                ingredMeasurement.setVisibility(View.GONE);
                ingredAmount.setVisibility(View.GONE);
                ingredButton.setVisibility(View.GONE);
                startIngredients.setVisibility(View.VISIBLE);
                //clear text boxes
                nameField.getText().clear();

                ingredName.getText().clear();
                ingredMeasurement.getText().clear();
                ingredAmount.getText().clear();



            }
        });

        return v;
    }

}