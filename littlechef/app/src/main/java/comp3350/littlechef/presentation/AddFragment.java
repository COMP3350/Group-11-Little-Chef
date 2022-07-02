package comp3350.littlechef.presentation;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


import comp3350.littlechef.R;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Ingredient;

// CLASS: AddFragment.java
//
//
// REMARKS: This class creates the add recipe fragment
//
//-----------------------------------------
public class AddFragment extends Fragment
{
    //this is for adding new recipes
    String name, second, third;

    public AddFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        //Get input from text fields
        EditText nameField = (EditText) v.findViewById(R.id.name_input);

        //button listener for adding name
        Button myButton = v.findViewById(R.id.addButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name = nameField.getText().toString();

                //error checking on conversions??????
                //recipe = new Recipe("Guacamole", 0, 30); //make a recipe
                Recipe recipe = new Recipe(name);

                Toast.makeText(getContext(), "Created recipe!", Toast.LENGTH_SHORT).show();
                //TODO: add proper text that recipe was successfully added

                //TODO: insert this blank recipe into DB

                //TODO: create list of ingredients to add

                //TODO create list of steps to add
            }
        });


        //button listener for adding ingredients
        Button ingredButton = v.findViewById(R.id.ingredButton);
        ingredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //TODO: implement add ingredient
                Toast.makeText(getContext(), "Ingredient button", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}