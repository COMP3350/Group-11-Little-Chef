package comp3350.littlechef.presentation;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        //accessRecipes = new AccessRecipes();

        recipeList = new ArrayList<Recipe>();
//        String result = accessRecipes.getRecipes(recipeList);
        String result = null;

        Recipe recipe;

        recipe = new Recipe("Pizza", 0.5f);
        recipeList.add(recipe);
        recipe = new Recipe("Mushroom Ravioli", 2.5f);
        recipeList.add(recipe);
        recipe = new Recipe("Guacamole", 1.0f);
        recipeList.add(recipe);
        recipe = new Recipe("Pierogies", 1.5f);
        recipeList.add(recipe);

        if(result != null)
        {
            //TODO create messages class and add an error message here
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

                    //TODO set up the text views
                    name.setText(recipeList.get(position).getName());
//                    estimatedTime.setText(Float.toString(recipeList.get(position).getTimeToMake()));
//                    difficulty.setText();
//                    name.setText();
//                    name.setText();

                    return convertView;
                }
            };

            listView.setAdapter(recipeArrayAdapter);
        }

        return view;
    }


}