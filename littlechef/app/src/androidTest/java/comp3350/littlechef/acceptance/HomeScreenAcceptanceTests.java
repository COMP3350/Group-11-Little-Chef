package comp3350.littlechef.acceptance;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.littlechef.R;
import comp3350.littlechef.presentation.HomeActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeScreenAcceptanceTests
{
    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivity = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void testHomeScreen()
    {
        //check that all the parts of home screen are displayed
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()));
        onView(withId(R.id.recipes)).check(matches(isDisplayed()));
        onView(withId(R.id.add)).check(matches(isDisplayed()));
        onView(withId(R.id.meals)).check(matches(isDisplayed()));

        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.recipes)).perform(click());
        //TODO add a click on the meal plan when it is finished

        //TODO figure our how to check textviews under specific positions in the listview
    }

}
