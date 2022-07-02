package comp3350.littlechef.presentation;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
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
        EditText secondField = (EditText) v.findViewById(R.id.secondField);
        EditText thirdField= (EditText) v.findViewById(R.id.thirdField);

        //button listener
        Button myButton = v.findViewById(R.id.addButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name = nameField.getText().toString();
                second = secondField.getText().toString();
                third = thirdField.getText().toString();

                //error checking on conversions??????
                //recipe = new Recipe("Guacamole", 0, 30); //make a recipe
                Recipe recipe = new Recipe(name, Integer.parseInt(second), Integer.parseInt(third));

                Toast.makeText(getContext(), recipe.getName(), Toast.LENGTH_SHORT).show();
                //TODO: add proper text that recipe was successfully added

                //TODO: insert this blank recipe into DB

                //TODO: create list of ingredients to add

                //TODO create list of steps to add
            }
        });

        return v;
    }
}