package comp3350.littlechef.presentation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
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
    Recipe recipe;

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



    //TODO: add proper text that recipe was successfully added

    //TODO: insert this blank recipe into DB

    //TODO create list of steps to add

    //TODO add check for duplicates

    //TODO: fix amount entry

    //User creates recipe name
    //then user
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        //just to get ride of name
        //Recipe recipe = new Recipe("Blank");

        TextView bannerofIngredient = (TextView) v.findViewById(R.id.textViewIngred);
        EditText ingredName= (EditText) v.findViewById(R.id.ingredName);
        EditText ingredMeasurement= (EditText) v.findViewById(R.id.ingredMeasurement);
        EditText ingredAmount= (EditText) v.findViewById(R.id.ingredAmount);
        Button ingredButton= (Button) v.findViewById(R.id.ingredButton);
        Button startIngredients= (Button) v.findViewById(R.id.startIngredients);
        Button resetButton= (Button) v.findViewById(R.id.resetButton);

        //Initial all the add ingredient stuff should not be visible until recipe object created, ie button clicked
        bannerofIngredient.setVisibility(View.GONE);
        ingredName.setVisibility(View.GONE);
        ingredMeasurement.setVisibility(View.GONE);
        ingredAmount.setVisibility(View.GONE);
        ingredButton.setVisibility(View.GONE);

        //Get input from text fields
        EditText recipeInput = (EditText) v.findViewById(R.id.nameInput);




        //THIS ADDS A SMALL LIST VIEW TO ADD RECIPES


        accessRecipes = new AccessRecipes();

        recipeList = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList); // for testing


        final ListView listView = (ListView) v.findViewById(R.id.existingRecipes);

        recipeArrayAdapter = new ArrayAdapter<Recipe>(getActivity(), android.R.layout.simple_list_item_1, recipeList)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                Recipe recipe = getItem(position);
                if(convertView == null)
                {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_card_small,parent, false);
                }
                TextView name = (TextView) convertView.findViewById(R.id.recipeNameSmall);

                SpannableString recipeNameFormatted = new SpannableString(recipe.getName());
                recipeNameFormatted.setSpan(new UnderlineSpan(), 0, recipeNameFormatted.length(), 0);
                recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);

                name.setText(recipeNameFormatted);


                return convertView;
            }
        };

        listView.setAdapter(recipeArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Recipe selectedRecipe = (Recipe) listView.getItemAtPosition(position);

                Intent detailedRecipe = new Intent(getActivity(), DetailedRecipeActivity.class);
                detailedRecipe.putExtra("id", selectedRecipe); //pass the object reference to another activity
                startActivity(detailedRecipe);
            }
        });






        //button listener for start Ingredients, will hide that button and show add ingredients stuff
        startIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                bannerofIngredient.setVisibility(View.VISIBLE);
                ingredName.setVisibility(View.VISIBLE);
                ingredMeasurement.setVisibility(View.VISIBLE);
                ingredAmount.setVisibility(View.VISIBLE);
                ingredButton.setVisibility(View.VISIBLE);
                startIngredients.setVisibility(View.GONE);

                recipe = new Recipe( recipeInput.toString() );
                Toast.makeText(getContext(), "Add ingredients to the recipe!", Toast.LENGTH_SHORT).show();

            }
        });




        //button listener for start Ingredients, will hide that button and show add ingredients stuff
        ingredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //recipe.addIngredient(new Ingredient("Ranch Dressing", Unit.CUP, 0.25));


                //TODO: fix units witj  ingredMeasurement
                recipe.addIngredient(new Ingredient(ingredName.toString(), Unit.CUP, 0.25 ));

                String recipeString = recipe.getName();

                Toast.makeText(getContext(), recipeString, Toast.LENGTH_SHORT).show();
            }
        });









        //INSERT TO DB BUTTON
        //when reset button is clicked, wipe field boxes and hide again
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //TODO: create function to auto hide these
                bannerofIngredient.setVisibility(View.GONE);
                ingredName.setVisibility(View.GONE);
                ingredMeasurement.setVisibility(View.GONE);
                ingredAmount.setVisibility(View.GONE);
                ingredButton.setVisibility(View.GONE);
                startIngredients.setVisibility(View.VISIBLE);
                //clear text boxes
                recipeInput.getText().clear();

                ingredName.getText().clear();
                ingredMeasurement.getText().clear();
                ingredAmount.getText().clear();

                //just for testing remove later!!!!
                Toast.makeText(getContext(), "Added to db...wiping fields", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

}