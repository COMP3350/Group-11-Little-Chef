package comp3350.littlechef.presentation;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.littlechef.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReadRateRecipeAcceptanceTest
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

    @Test
    public void testReadRateRecipe()
    {
        onView(withText("Guacamole")).check(matches(isDisplayed())).perform(click());

        //check the recipe info
        onView(withId(R.id.recipe_name)).check(matches(withText("Guacamole")));
        onView(withId(R.id.estimated_time)).check(matches(withText("Time: Not cooked")));
        onView(withId(R.id.difficulty)).check(matches(withText("Difficulty: -")));
        onView(withId(R.id.taste)).check(matches(withText("Taste: -")));
        onView(withId(R.id.rating)).check(matches(withText("-/5")));

        //check that buttons and textViews exist
        onView(withId(R.id.edit_recipe)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_recipe)).check(matches(isDisplayed()));
        onView(withId(R.id.serving_num)).check(matches(isDisplayed()));
        onView(withText("Ingredients")).check(matches(isDisplayed()));
        //TODO check all the ingredients


        onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).perform(click());
        onView(withText("Instructions")).check(matches(isDisplayed()));
        //todo check all the instructions

        onView(withId(R.id.timer_text)).check(matches(withText("00:00:00")));
        onView(withId(R.id.reset_timer)).check(matches(isDisplayed()));
        onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));

        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));
        try {Thread.sleep(5000);}
        catch (Exception e) {}
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

        //WAIT FOR A THANK YOU WINDOW TO PASS
        try {Thread.sleep(1500);}
        catch (Exception e) {}

        //TODO CHECK FOR ALL THE GUI ELEMENTS
        onView(withId(R.id.difficulty_radio_3)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.taste_radio_5)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.submit_rating)).check(matches(isDisplayed())).perform(click());




    }
}
