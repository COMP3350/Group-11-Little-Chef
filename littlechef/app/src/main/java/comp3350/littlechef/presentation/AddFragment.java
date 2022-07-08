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
    String nameIngred;

    Recipe selectedRecipe;

    EditText recipeInput;
    EditText ingredientInputName;

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;


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
        //to make the input boxes shift up when keyboard activated
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        //for the spinner
        spinner = (Spinner) view.findViewById(R.id.spinnerUnit);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(getActivity().getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
















        Button addRecipeButton= (Button) view.findViewById(R.id.addRecipeButton);
        TextView workingRecipeName= (TextView) view.findViewById(R.id.workingRecipeName);
        workingRecipeName.setVisibility(View.GONE); //initially does not show selected recipe
        EditText ingredientName= (EditText) view.findViewById(R.id.ingredientName);
        ingredientName.setVisibility(View.GONE);
        Button addIngredientButton= (Button) view.findViewById(R.id.addIngredientButton);
        addIngredientButton.setVisibility(View.GONE);

        //Get input from text fields
        recipeInput = (EditText) view.findViewById(R.id.nameInput);
        ingredientInputName = (EditText) view.findViewById(R.id.ingredientName);

        //START LISTVIEW
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

            //on listview click
            listView.setAdapter(recipeArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //selected recipe and take to new activity
                   // Recipe selectedRecipe = (Recipe) listView.getItemAtPosition(position);
                    selectedRecipe = (Recipe) listView.getItemAtPosition(position);
                    workingRecipeName.setText("Add Ingredients to "+selectedRecipe.getName());
                    workingRecipeName.setVisibility(View.VISIBLE);
                    ingredientName.setVisibility(View.VISIBLE);
                    addIngredientButton.setVisibility(View.VISIBLE);

                }
            });

            //button listener for add recipe, will hide that button and show add ingredients stuff
            addRecipeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    //do add recipe button and add ingredients
                    //recipeArrayAdapter.notifyDataSetChanged();
                    addRecipeClick();
                    Toast.makeText(getContext(), "Added Recipe!", Toast.LENGTH_SHORT).show();
                    recipeArrayAdapter.notifyDataSetChanged();

                }
            });

            //button listener for adding an ingredient to a recipe
            addIngredientButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    //TODO implement
                    //Toast.makeText(getActivity(), "addIngredient button!"+selectedRecipe.getName(), Toast.LENGTH_SHORT).show();
                    addIngredients(view);

                }
            });

            //END LISTVIEW
        }
        return view;
    }//end onclickview

    //add ingredients
    private void addIngredients(View view)
    {
        //TODO finish implement
        //recipe.addIngredient(new Ingredient("Olive Oil", Unit.TBSP, 1));
        String result;
        nameIngred = ingredientInputName.getText().toString();
        //Toast.makeText(getActivity(), ""+nameIngred, Toast.LENGTH_SHORT).show();


        selectedRecipe.addIngredient(new Ingredient(nameIngred,Unit.TBSP, 1 ));
        result = accessRecipes.updateRecipe(selectedRecipe);
        if(result != null)
        {
            Messages.fatalError(getActivity(), result);
        }
        else
        {
            Toast.makeText(getActivity(), "Added "+nameIngred+" to ingredients!", Toast.LENGTH_SHORT).show();
        }

    }

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