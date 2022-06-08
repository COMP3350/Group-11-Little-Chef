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
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import comp3350.littlechef.R;
import comp3350.littlechef.business.AccessRecipes;
import comp3350.littlechef.objects.Recipe;


public class ViewFragment extends Fragment
{

    private AccessRecipes accessRecipes;
    private ArrayList<Recipe> recipeList;
    private ArrayAdapter<Recipe> recipeArrayAdapter;
    private int selectedRecipePosition = -1;

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

                    SpannableString recipeNameFormatted = new SpannableString(recipeList.get(position).getName());
                    recipeNameFormatted.setSpan(new UnderlineSpan(), 0, recipeNameFormatted.length(), 0);
                    recipeNameFormatted.setSpan(new StyleSpan(Typeface.BOLD), 0, recipeNameFormatted.length(), 0);

                    name.setText(recipeNameFormatted);
                    estimatedTime.setText(recipeList.get(position).getTimeToMakeString());
                    difficulty.setText(recipeList.get(position).getDifficultyString());
                    taste.setText(recipeList.get(position).getQualityString());
                    rating.setText(recipeList.get(position).getRatingString());

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
                    detailedRecipe.putExtra("id", Integer.toString(selectedRecipe.getId()));
                    startActivity(detailedRecipe);
                }
            });
        }

        return view;
    }

    public ArrayList<Recipe> getRecipeList()
    {
        return recipeList;
    }
}