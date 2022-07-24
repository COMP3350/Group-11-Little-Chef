package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.littlechef.R;
import comp3350.littlechef.objects.Recipe;
import comp3350.littlechef.objects.MealPlan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMealPlan extends AppCompatActivity
{
    private MealPlan mealPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal_plan);

        Intent previousIntent = getIntent();
        mealPlan = (MealPlan) previousIntent.getSerializableExtra("id");


        finish();
    }
}