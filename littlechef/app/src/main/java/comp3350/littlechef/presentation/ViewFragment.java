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
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;

//TODO get rid of static in  recipeArrayAdapter
public class ViewFragment extends Fragment
{
    private final String FAKE_RATING = "5/5";
    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;

    public ViewFragment()
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        accessRecipes = new AccessRecipes();

        recipeList = new ArrayList<Recipe>();
        String result = accessRecipes.getRecipes(recipeList);

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
                    Recipe recipe = getItem(position);
                    if(convertView == null)
                    {
                        convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_card,parent, false);
                    }
                    TextView name = (TextView) convertView.findViewById(R.id.recipeName);
                    TextView estimatedTime = (TextView) convertView.findViewById(R.id.estimatedTime);
                    TextView difficulty = (TextView) convertView.findViewById(R.id.difficulty);
                    TextView taste = (TextView) convertView.findViewById(R.id.taste);
                    TextView rating = (TextView) convertView.findViewById(R.id.rating);

                    SpannableString recipeNameFormatted = new SpannableString(recipe.getName());
                    recipeNameFormatted.setSpan(new UnderlineSpan(), 0, recipeNameFormatted.length(), 0);
                    recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);

                    name.setText(recipeNameFormatted);
                    estimatedTime.setText(recipe.getAverageCookingTime());
                    difficulty.setText(recipe.getDifficultyString());
                    taste.setText(recipe.getQualityString());
                    rating.setText(FAKE_RATING);

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
}