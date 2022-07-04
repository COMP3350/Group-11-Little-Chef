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

        //Initial all the add ingredient stuff should not be visible until recipe object created, ie button clicked
        bannerofIngredient.setVisibility(View.GONE);
        ingredName.setVisibility(View.GONE);
        ingredMeasurement.setVisibility(View.GONE);
        ingredAmount.setVisibility(View.GONE);
        ingredButton.setVisibility(View.GONE);

        //Get input from text fields
        EditText nameField = (EditText) v.findViewById(R.id.name_input);

        //button listener for adding name
        ingredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameField.getText().toString();
                //recipe = new Recipe("Guacamole"); //make a recipe

                //if the recipe exists then just add ingredient to it, if not then create new recipe
                Recipe recipe = new Recipe(name);

                bannerofIngredient.setVisibility(View.VISIBLE);





                Toast.makeText(getContext(), "Created recipe!", Toast.LENGTH_SHORT).show();




                //TODO: add proper text that recipe was successfully added

                //TODO: insert this blank recipe into DB

                //TODO create list of steps to add

                //TODO add check for duplicates
            }
        });

        return v;
    }

}