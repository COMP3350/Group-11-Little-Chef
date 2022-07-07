package comp3350.littlechef.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import comp3350.littlechef.R;


//this comes from add fragment to show the ingredients and add more if necessary
public class ingredientsView extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients_view);
    }
}