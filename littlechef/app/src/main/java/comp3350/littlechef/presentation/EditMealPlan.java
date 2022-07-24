package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.objects.Recipe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMealPlan extends AppCompatActivity
{
    //private ArrayList sundayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal_plan);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Recipe> sundayList = (ArrayList<Recipe>) args.getSerializable("ARRAYLIST");

        Toast.makeText(this, sundayList.get(0).getName(), Toast.LENGTH_SHORT).show();

        sundayList.remove(0);
        finish();
    }
}