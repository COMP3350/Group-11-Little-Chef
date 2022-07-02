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

    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    //when submit button clicked
    public void buttonAddRecipe(View v)
    {
        Recipe recipe = createRecipeFromEditText();
        //do error checking****

        System.out.println("RECIPE TEST FROM ADD: "+recipe.getName());

    }

    //MIGHT HAVE TO PUT THIS IN ONCREATE
    //grabs input from add Recipe screen
    private Recipe createRecipeFromEditText()
    {
        EditText nameField = (EditText) getActivity().findViewById(R.id.name_input);
        EditText secondField = (EditText) getActivity().findViewById(R.id.secondField);
        EditText thirdField= (EditText) getActivity().findViewById(R.id.thirdField);



        Button submitButton;
        submitButton = (Button) getActivity().findViewById(R.id.addButton);

        //setup listener for button clicking
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //set values from input boxes
                name = nameField.getText().toString();
                second = secondField.getText().toString();
                third = thirdField.getText().toString();
                System.out.println("PRINTING!!!!");
                Log.d("tag", "message");

            }
        }); //end listener


        //error checking on conversions??????        //recipe = new Recipe("Guacamole", 0, 30); //make a recipe
        Recipe recipe = new Recipe(name, Integer.parseInt(secondField.toString()), Integer.parseInt(thirdField.toString()) );

        System.out.println("PRINTING!!!!");

        return recipe;
    }

}