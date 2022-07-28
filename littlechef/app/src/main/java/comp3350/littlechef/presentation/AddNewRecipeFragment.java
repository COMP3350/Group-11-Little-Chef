package comp3350.littlechef.presentation;

import android.app.AlertDialog;
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
// REMARKS: This class creates the add recipe fragment, where you can add recipes
//          You can then click on the recipe to add individual ingredients and instructions.
//-----------------------------------------------------------------------
public class AddNewRecipeFragment extends Fragment
{
    private String recipeName;
    private EditText recipeInput;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String result;
        View view = inflater.inflate(R.layout.fragment_add_new_recipe, container, false);

        Button addRecipeButton= view.findViewById(R.id.add_new_recipe_button);

        // Get input from text fields
        recipeInput = view.findViewById(R.id.new_recipe_name_input);

        // This adds a small list view to add recipes
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        result = accessRecipes.getRecipes(recipeList);

        if(result != null)
        {
            Messages.fatalError(getActivity(), result);
        }

        addRecipeButton.setOnClickListener(view1 -> addRecipeClick());

        return view;

    }

    private void addRecipeClick()
    {
        recipeName = recipeInput.getText().toString();
        String result;

        result = checkEmpty(recipeInput);

        if(result == null)
        {
            Recipe newRecipe = new Recipe(recipeName);
            result = validateRecipeName(newRecipe);

            if (result == null)
            {
                insertRecipe(newRecipe);

            }
            else
            {
                AlertDialog.Builder resetAlert = new AlertDialog.Builder(getActivity());
                resetAlert.setTitle("Duplicate Recipe Name");
                resetAlert.setMessage("Do you want to add anyway?");

                resetAlert.setPositiveButton("Continue", (dialog, which) -> {
                    insertRecipe(newRecipe);
                });

                resetAlert.setNeutralButton("Cancel", (dialog, which) -> {
                    //do nothing
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
        Intent addRecipeActivity = new Intent(getActivity(), addIngredientActivity.class);
        addRecipeActivity.putExtra("id", newRecipe);
        startActivity(addRecipeActivity);
    }


    private String checkEmpty(EditText editText)
    {
        if(editText.getText().toString().equals(""))
        {
            return "Recipe name required";
        }

        return null;
    }

    private String validateRecipeName(Recipe recipeAdd)
    {
        String result = null;

        if (checkDuplicates(recipeAdd))
        {
            result = recipeAdd.getName() + " already exists.";
        }

        return result;
    }

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