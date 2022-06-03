package comp3350.littlechef.presentation;

import comp3350.littlechef.R;
import comp3350.littlechef.application.Main;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity
{
    //initialize the fragments
    BottomNavigationView bottomNavigationView;
    ViewFragment viewFragment = new ViewFragment();
    AddFragment addFragment = new AddFragment();
    MealsFragment mealsFragment = new MealsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Main.startUp();

        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        replaceFragment(new ViewFragment()); //set view fragment on start

        //set up bottom navigation listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.recipes:
                        replaceFragment(new ViewFragment());
                        return true;

                    case R.id.add:
                        replaceFragment(new AddFragment());
                        return true;

                    case R.id.meals:
                        replaceFragment(new MealsFragment());
                        return true;
                }
                return false;
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }


    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}
