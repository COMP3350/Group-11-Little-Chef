package comp3350.littlechef.acceptance;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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
import comp3350.littlechef.presentation.HomeActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AcceptanceTests {

    @Rule
    public ActivityScenarioRule<HomeActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void acceptanceTests() {
        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.recipe_list_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(1);
        relativeLayout.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.start_cooking), withText("Instructions"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.start_stop_timer), withText("START"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.start_stop_timer), withText("STOP"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.finish_cooking_button), withText("Finish"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.difficulty_radio_3), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.difficulty_radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                6)),
                                2),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction materialRadioButton2 = onView(
                allOf(withId(R.id.taste_radio_2), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.taste_radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                7)),
                                1),
                        isDisplayed()));
        materialRadioButton2.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.submit_rating), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.estimated_time), withText("Time: 00h 00m 01s"),
                        withParent(withParent(withId(R.id.recipe_list_view))),
                        isDisplayed()));
        textView.check(matches(withText("Time: 00h 00m 01s")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.difficulty), withText("Difficulty: 3.00"),
                        withParent(withParent(withId(R.id.recipe_list_view))),
                        isDisplayed()));
        textView2.check(matches(withText("Difficulty: 3.00")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.taste), withText("Taste: 2.00"),
                        withParent(withParent(withId(R.id.recipe_list_view))),
                        isDisplayed()));
        textView3.check(matches(withText("Taste: 2.00")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.taste), withText("Taste: 2.00"),
                        withParent(withParent(withId(R.id.recipe_list_view))),
                        isDisplayed()));
        textView4.check(matches(withText("Taste: 2.00")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
