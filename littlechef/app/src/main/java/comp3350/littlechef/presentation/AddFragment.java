package comp3350.littlechef.presentation;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Ingredient;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.Unit;



// CLASS: AddFragment.java
//
//
// REMARKS: This class creates the add recipe fragment
//
//-----------------------------------------
public class AddFragment extends Fragment
{
    String name;
    Recipe currentRecipe;

    EditText recipeInput;

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;


    public AddFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    //TODO create list of steps to add

    //TODO add check for duplicates
        //current ISSUES

    //TODO: fix amount entry

    //TODO: Refresh listview when recipe added (not necessary) CURRENT***

    //TODO: make it so boxes shift up when keyboard active

    //TODO: print listview alphabetically

    //User creates recipe name
    //then user
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        Button addRecipeButton= (Button) v.findViewById(R.id.addRecipeButton);
        //Get input from text fields
        recipeInput = (EditText) v.findViewById(R.id.nameInput);

        //START LISTVIEW
        //THIS ADDS A SMALL LIST VIEW TO ADD RECIPES
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList); // for testing if working correctly
        final ListView listView = (ListView) v.findViewById(R.id.existingRecipes);


        //check if null
        if( result != null)
        {
            Messages.fatalError(getActivity(), result);
        }
        else
        {
            recipeArrayAdapter = new ArrayAdapter<Recipe>(getActivity(), android.R.layout.simple_list_item_1, recipeList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    Recipe recipe = getItem(position);
                    if (convertView == null)
                    {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_card_small, parent, false);
                    }
                    TextView name = (TextView) convertView.findViewById(R.id.recipeNameSmall);

                    SpannableString recipeNameFormatted = new SpannableString(recipe.getName());
                    recipeNameFormatted.setSpan(new UnderlineSpan(), 0, recipeNameFormatted.length(), 0);
                    recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);

                    name.setText(recipeNameFormatted);


                    return convertView;
                }
            };

            //on listview click
            listView.setAdapter(recipeArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //TODO: ADD CLICKED ON RECIPE STUFF
                    Log.i("listview selected", "listview selected!");
                    recipeArrayAdapter.notifyDataSetChanged();
                }
            });

            //button listener for add recipe, will hide that button and show add ingredients stuff
            addRecipeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    //do add recipe button and add ingredients
                    recipeArrayAdapter.notifyDataSetChanged();
                    addRecipeClick();
                    Toast.makeText(getContext(), "Added Recipe!", Toast.LENGTH_SHORT).show();

                }
            });

            //END LISTVIEW
        }

        recipeArrayAdapter.notifyDataSetChanged();
        return v;
    }//end onclickview

    //this updates the working recipe
    private void updateWorkingRecipe()
    {

    }

    //when the add recipe button is clicked do this
    private void addRecipeClick()
    {
        //create recipe only with name
        name = recipeInput.getText().toString();
        currentRecipe = new Recipe( name );

        //add to db
        String result;
        result = validateRecipeName(currentRecipe, true);

        if(result == null)
        {
            result = accessRecipes.insertRecipe(currentRecipe);
        }

    }

    //COURSE TESTING
    private String validateRecipeName(Recipe recipeAdd, boolean isNewRecipe)
    {
        if (recipeAdd.getName().length() == 0)
        {
            return "Recipe name required";
        }

        if (isNewRecipe && accessRecipes.getRandom(recipeAdd.getId() ) != null)
        {
            //TODO: THIS DOES NOT WORK
            //Log.i("Hit found recipe", "Recipe ID found");
            return "Recipe ID " + recipeAdd.getId() + " already exists.";
        }

        return null;
    }

}