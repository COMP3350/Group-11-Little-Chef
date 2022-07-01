package comp3350.littlechef.presentation;

import comp3350.littlechef.R;
import comp3350.littlechef.application.Main;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
// CLASS: HomeActivity.java
//
//
// REMARKS: This class creates the home screen.
//
//-----------------------------------------

public class HomeActivity extends AppCompatActivity
{
    //initialize the fragments
    private BottomNavigationView bottomNavigationView;
    private ViewFragment viewFragment;
    private AddFragment addFragment;
    private MealsFragment mealsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Main.startUp();

        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //create all the fragments
        viewFragment = new ViewFragment();
        addFragment = new AddFragment();
        mealsFragment = new MealsFragment();
        replaceFragment(viewFragment); //set view fragment on start

        bottomNavigationView.getMenu().findItem(R.id.meals).setEnabled(false); //set meal plans button in the menu to inactive until implementation

        //set up bottom navigation listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.recipes:
                        viewFragment = new ViewFragment();
                        replaceFragment(viewFragment);
                        return true;

                    case R.id.add:
                        addFragment = new AddFragment();
                        replaceFragment(addFragment);
                        return true;

                    case R.id.meals:
                        mealsFragment = new MealsFragment();
                        replaceFragment(mealsFragment);
                        return true;
                }
                return false;
            }
        });


    }

    @Override
    protected void onDestroy()
    {
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
