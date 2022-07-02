package comp3350.littlechef.presentation;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;




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

    //grabs input from add Recipe screen
    private Recipe createCourseFromEditText()
    {
        EditText name = (EditText) getActivity().findViewById(R.id.name_input);
        EditText secondField = (EditText) getActivity().findViewById(R.id.secondField);
        EditText thirdField= (EditText) getActivity().findViewById(R.id.thirdField);
        //recipe = new Recipe("Guacamole", 0, 30); //make a recipe


        //error checking on conversions??????
        Recipe recipe = new Recipe(name.toString(), Integer.parseInt(secondField.toString()), Integer.parseInt(thirdField.toString()) );


        return recipe;
    }
}