package comp3350.littlechef.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import comp3350.littlechef.R;

import comp3350.littlechef.objects.Recipe;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.ArrayList;

import comp3350.littlechef.objects.Recipe;

public class MealPlanListAdapter extends ArrayAdapter<Recipe>
{
    private static final String TAG = "MealPlanListAdapter";
    private Context mContext;
    int mResource;

    /**
    * Default constructor for the PersonListAdapter
    * @param context
    * @param resource
     * @param objects
    */

    public MealPlanListAdapter(Context context, int resource,  ArrayList<Recipe> objects)
    {
        super(context, resource, objects);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String name1 = "test1";
        String name2 = "test2";
        String name3 = "test3";

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName1 = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvName2 = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvName3 = (TextView) convertView.findViewById(R.id.textView3);

        tvName1.setText(name1);
        tvName2.setText(name2);
        tvName3.setText(name3);

        return convertView;
    }
}
