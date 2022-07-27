package comp3350.littlechef.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;


// CLASS: AddNewRecipeFragment.java
//
//
// REMARKS: This class creates the add recipe fragment, where you can add recipes
//          You can then click on the recipe to add individual ingredients and instructions.
//
//-----------------------------------------
public class AddNewRecipeFragment extends Fragment
{
    String name; //recipe name

    EditText recipeInput;

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;

    public AddNewRecipeFragment()
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
        View view = inflater.inflate(R.layout.fragment_add_new_recipe, container, false);

        Button addRecipeButton= (Button) view.findViewById(R.id.addRecipeButton);

        //Get input from text fields
        recipeInput = (EditText) view.findViewById(R.id.nameInput);

        //THIS ADDS A SMALL LIST VIEW TO ADD RECIPES
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList);
        if(result != null)
        {
            Messages.fatalError(getActivity(), result);
        }


        //button listener for add recipe
        addRecipeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addRecipeClick();
            }
        });//END LISTVIEW
        return view;

    }//end onclickview

    //when the add recipe button is clicked do this
    private void addRecipeClick()
    {
        //create recipe only with name
        name = recipeInput.getText().toString();
        String result;
        result = checkEmpty(recipeInput);
        if(result == null)
        {
            Recipe newRecipe = new Recipe(name );
            result = validateRecipeName(newRecipe);//checks if already exists
            if (result == null)
            {
                insertRecipe(newRecipe);

            }
            else
            {
                AlertDialog.Builder resetAlert = new AlertDialog.Builder(getActivity());
                resetAlert.setTitle("Duplicate Recipe Name");
                resetAlert.setMessage("Do you want to add anyway?");

                resetAlert.setPositiveButton("Continue", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        insertRecipe(newRecipe);
                        //then just clear the box once insert is called

                    }
                });

                resetAlert.setNeutralButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //do nothing
                    }
                });

                resetAlert.show();
            }
        }
        else
        {
            Messages.warning(getActivity(), result);
        }

    }
    private void insertRecipe(Recipe newRecipe)
    {
        String result;
        result = accessRecipes.insertRecipe(newRecipe);
        if (result == null)
        {

            //launch new activity to add instructions/ingredients
            launchAddActivity(newRecipe);
        }
        else
        {
            Messages.fatalError(getActivity(), result);
        }
    }

    private void launchAddActivity(Recipe newRecipe)
    {
        recipeInput.getText().clear();
        Intent addRecipeActivity = new Intent(getActivity(), AddRecipeActivity.class);
        addRecipeActivity.putExtra("id", newRecipe); //pass the object reference to another activity
        startActivity(addRecipeActivity);
    }


    private String checkEmpty(EditText editText)
    {
        if(editText.getText().toString().equals(""))
            return "Recipe name required";

        return null;
    }
    //Validate the recipe
    private String validateRecipeName(Recipe recipeAdd)
    {
        //check through all recipes for duplicats
        if (checkDuplicates(recipeAdd))
        {
            return recipeAdd.getName() + " already exists.";
        }

        return null;
    }

    //returns true if recipe already in list
    private boolean checkDuplicates(Recipe recipe)
    {
        boolean check = false;

        //iterate through entire list checking for recipe name
        for(int i = 0; i < recipeList.size(); i++)
        {
            if(recipe.getName().toLowerCase().equals( recipeList.get(i).getName().toLowerCase() ) )
                check = true;
        }

        return check;
    }

}