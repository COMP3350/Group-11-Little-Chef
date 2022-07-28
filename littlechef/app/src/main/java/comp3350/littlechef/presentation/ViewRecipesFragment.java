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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;

// CLASS: SuggestRecipesActivity.java
// REMARKS: This class will  display all the recipes.
//-----------------------------------------
public class ViewRecipesFragment extends Fragment
{
    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private Recipe recipe;

    public ViewRecipesFragment()
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

        View view = inflater.inflate(R.layout.fragment_view, container, false);

        accessRecipes = new AccessRecipes();
        recipeList = new ArrayList<Recipe>();

        result = accessRecipes.getRecipes(recipeList);

        if(result != null)
        {
            Messages.fatalError(getActivity(), result);
        }
        else
        {
            final ListView listView = (ListView) view.findViewById(R.id.recipe_list_view);

            recipeArrayAdapter = new ArrayAdapter<Recipe>(getActivity(), android.R.layout.simple_list_item_1, recipeList)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    recipe = getItem(position);
                    if(convertView == null)
                    {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_card,parent, false);
                    }

                    setValues(convertView);

                    return convertView;
                }
            };

            listView.setAdapter(recipeArrayAdapter);

            listView.setOnItemClickListener((parent, view1, position, id) -> {
                Recipe selectedRecipe = (Recipe) listView.getItemAtPosition(position);

                Intent detailedRecipe = new Intent(getActivity(), DetailedRecipeActivity.class);
                detailedRecipe.putExtra("id", selectedRecipe); //pass the object reference to another activity
                startActivity(detailedRecipe);
            });
        }

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        String result = accessRecipes.getRecipes(recipeList);

        if(result != null)
        {
            Messages.fatalError(getActivity(), result);
        }
        else
        {
            //if the data has changed -> update
            recipeArrayAdapter.notifyDataSetChanged();
        }
    }

    private void setValues(View convertView)
    {
        String difficultyRating = recipe.getDifficultyRating();
        String tasteRating = recipe.getTasteRating();

        TextView name = (TextView) convertView.findViewById(R.id.recipe_name);
        TextView estimatedTime = (TextView) convertView.findViewById(R.id.estimated_time);
        TextView difficulty = (TextView) convertView.findViewById(R.id.difficulty);
        TextView taste = (TextView) convertView.findViewById(R.id.taste);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);

        SpannableString recipeNameFormatted = new SpannableString(recipe.getName());
        recipeNameFormatted.setSpan(new UnderlineSpan(), 0, recipeNameFormatted.length(), 0);
        recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);

        name.setText(recipeNameFormatted);
        estimatedTime.setText(recipe.getAverageCookingTime());
        difficulty.setText(difficultyRating);
        taste.setText(tasteRating);
        rating.setText(recipe.getRatingString());
    }
}