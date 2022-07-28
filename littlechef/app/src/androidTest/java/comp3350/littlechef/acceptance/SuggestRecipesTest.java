package comp3350.littlechef.acceptance;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
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
public class SuggestRecipesTest
{
    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivity = new ActivityScenarioRule<>(HomeActivity.class);

    //typical cases that average user would follow, typical cooking, rating, edit, delete and add activities
    @Test
    public void testMealsSuggestion() {
        //check selection shown in Suggest Recipes
        onView(withId(R.id.meals)).perform(click());
        onView(withId(R.id.textView4)).check(matches(withText("Can't decide what to cook? Let us help.")));
        onView(withId(R.id.textView5)).check(matches(withText("Select what matters to you most today.")));

        onView(withId(R.id.challengeButton)).check(matches(isDisplayed()));
        onView(withId(R.id.savingTimeButton)).check(matches(isDisplayed()));
        onView(withId(R.id.tasteButton)).check(matches(isDisplayed()));
        onView(withId(R.id.supriseMeButton)).check(matches(isDisplayed()));

        onView(withId(R.id.challengeButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.weekOfTV)).check(matches(isDisplayed()));
        onView(withId(R.id.recipe_meal_suggestion)).check((matches(isDisplayed())));
        Espresso.pressBack();

        onView(withId(R.id.easeButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.weekOfTV)).check(matches(isDisplayed()));
        onView(withId(R.id.recipe_meal_suggestion)).check((matches(isDisplayed())));
        Espresso.pressBack();

        onView(withId(R.id.savingTimeButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.weekOfTV)).check(matches(isDisplayed()));
        onView(withId(R.id.recipe_meal_suggestion)).check((matches(isDisplayed())));
        Espresso.pressBack();

        onView(withId(R.id.tasteButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.weekOfTV)).check(matches(isDisplayed()));
        onView(withId(R.id.recipe_meal_suggestion)).check((matches(isDisplayed())));
        Espresso.pressBack();

        onView(withId(R.id.supriseMeButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.weekOfTV)).check(matches(isDisplayed()));
        onView(withId(R.id.recipe_meal_suggestion)).check((matches(isDisplayed())));
        Espresso.pressBack();
    }
}



























