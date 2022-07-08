package comp3350.littlechef.presentation;

import android.content.Intent;
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
import android.widget.Spinner;

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
    String nameIngred,amountIngred;

    Recipe selectedRecipe;

    EditText recipeInput;
    EditText ingredientInputName;
    EditText ingredientInputAmount;

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    String unitString;

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

    //TODO: make it so boxes shift up when keyboard active******

    //User creates recipe name
    //then user
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        Button addRecipeButton= (Button) view.findViewById(R.id.addRecipeButton);

        //Get input from text fields
        recipeInput = (EditText) view.findViewById(R.id.nameInput);

        //THIS ADDS A SMALL LIST VIEW TO ADD RECIPES
        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList);
        if( result != null)
        {
            Messages.fatalError(getActivity(), result);
        }
        else
        {
            final ListView listView = (ListView) view.findViewById(R.id.existingRecipes);

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

            //on recipe click, start activity to add
            listView.setAdapter(recipeArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    selectedRecipe = (Recipe) listView.getItemAtPosition(position);

                    Intent addRecipeActivity = new Intent(getActivity(), AddRecipeActivity.class);
                    addRecipeActivity.putExtra("id", selectedRecipe); //pass the object reference to another activity
                    startActivity(addRecipeActivity);

                }
            });

            //button listener for add recipe
            addRecipeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    //do add recipe button
                    addRecipeClick();
                    recipeArrayAdapter.notifyDataSetChanged();
                }
            });//END LISTVIEW

        }
        return view;
    }//end onclickview

    //when the add recipe button is clicked do this
    private void addRecipeClick()
    {
        //create recipe only with name
        name = recipeInput.getText().toString();
        String result;
        Recipe newRecipe = new Recipe( name );

        result = validateRecipeName(newRecipe, true);

        if(result == null)
        {
            result = accessRecipes.insertRecipe(newRecipe);
            if(result == null)
            {
                accessRecipes.getRecipes(recipeList);

                recipeArrayAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), newRecipe.getName()+" added to recipes!", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Messages.fatalError(getActivity(), result);
            }
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