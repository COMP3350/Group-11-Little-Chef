package comp3350.littlechef.acceptance;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
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
public class ReadRateEditAddRecipeAcceptanceTest
{

    @Rule
    public ActivityScenarioRule<HomeActivity> homeActivity = new ActivityScenarioRule<>(HomeActivity.class);

    //typical cases that average user would follow, typical cooking, rating, edit, delete and add activities
    @Test
    public void testTypicalCases()
    {
        onData(hasToString(startsWith("Hard-cooked eggs"))).perform(click());

        //check the recipe info
        onView(withId(R.id.recipe_name)).check(matches(withText("Hard-cooked eggs")));
        onView(withId(R.id.estimated_time)).check(matches(withText("Time: Not cooked")));
        onView(withId(R.id.difficulty)).check(matches(withText("Difficulty: -")));
        onView(withId(R.id.taste)).check(matches(withText("Taste: -")));
        onView(withId(R.id.rating)).check(matches(withText("-/5")));

        //check that buttons and textViews exist
        onView(withId(R.id.edit_recipe)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_recipe)).check(matches(isDisplayed()));
        onView(withId(R.id.serving_num)).check(matches(isDisplayed()));
        onView(withText("Ingredients")).check(matches(isDisplayed()));


        onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).perform(click());
        onView(withText("Instructions")).check(matches(isDisplayed()));

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
        //check that time value changed
        onView(withId(R.id.estimated_time_rating_activity)).check(matches(withText("Time: 00h 00m 05s")));
        onView(withId(R.id.difficulty_radio_3)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.taste_radio_5)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.submit_rating)).check(matches(isDisplayed())).perform(click());


        onData(hasToString(startsWith("Hard-cooked eggs"))).perform(click());

        //check updated recipe info
        onView(withId(R.id.estimated_time)).check(matches(withText("Time: 00h 00m 05s")));
        onView(withId(R.id.difficulty)).check(matches(withText("Difficulty: 3.00")));
        onView(withId(R.id.taste)).check(matches(withText("Taste: 5.00")));
        onView(withId(R.id.rating)).check(matches(withText("4.00/5")));

        onView(withId(R.id.edit_recipe)).check(matches(isDisplayed())).perform(click());

        //check updated recipe info in the edit activity
        onView(withId(R.id.estimated_time_edit)).check(matches(withText("Time: 00h 00m 05s")));
        onView(withId(R.id.difficulty_edit)).check(matches(withText("Difficulty: 3.00")));
        onView(withId(R.id.taste_edit)).check(matches(withText("Taste: 5.00")));
        onView(withId(R.id.rating_edit)).check(matches(withText("4.00/5")));
        onView(withId(R.id.recipe_name_edit)).check(matches(withText("Hard-cooked eggs")));

        //Change Hard-cooked eggs name "Mum's favourite Hard-cooked eggs"
        onView(withId(R.id.recipe_name_edit)).perform(clearText(), typeText("Mum's favourite Hard-cooked eggs"));

        //change the first ingredient which is "Large eggs" to "very small eggs", amount to 400 and Unit to grams
        onView(withText("Large eggs")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.editIngredient)).perform(clearText());

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing

        onView(withId(R.id.editAmount)).perform(clearText());


        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing

        onView(withId(R.id.editAmount)).perform(typeText("400"));
        onView(withId(R.id.editIngredient)).perform(typeText("Very small eggs"));
        onView(withId(R.id.editIngredientUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("G"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Very small eggs")).check(matches(isDisplayed())); //check the edit

        //delete ingredient "Kosher salt"
        onView(withText("Kosher salt")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.delete_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Kosher salt")).check(doesNotExist()); //check that it is not displayed

        //add new ingredient
        onView(withId(R.id.add_ingredient_button)).check(matches(isDisplayed())).perform(click());


        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.editIngredient)).perform(typeText("Avocado Milk"));
        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())); //should be enabled now
        onView(withId(R.id.editAmount)).perform(clearText(), typeText("150"));

        onView(withId(R.id.editIngredientUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("ML"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Avocado Milk")).check(matches(isDisplayed())); //check the addition

        Espresso.closeSoftKeyboard();

        //follow to edit instructions now
        onView(withId(R.id.next_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

        //check the UI elements are present
        onView(withId(R.id.add_button)).check(matches(isDisplayed()));
        onView(withId(R.id.cancel_button)).check(matches(isDisplayed()));
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));

        //edit instruction
        onView(withText("Place eggs in a saucepan or pot and cover with cold water.")).check(matches(isDisplayed())).perform(click());
            //check the UI of the edit_window
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_dialog_button)).check(matches(isDisplayed()));
        onView(withId(R.id.cancel_dialog_button)).check(matches(isDisplayed()));

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(clearText());
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(clearText());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(typeText("A changed sub instruction"));
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after changing sun instruction
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("A changed main instruction"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());//check that save button is enabled after changing main instruction and click it
        onView(withText("A changed main instruction")).check(matches(isDisplayed())); //check the edits
        onView(withText("A changed sub instruction")).check(matches(isDisplayed()));


        //delete instruction
        onView(withText("Put pan over high heat and bring water to a rolling boil. Remove pan from heat and cover.")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.delete_dialog_button)).check(matches(isDisplayed())).perform(click());
        onView(withText("Put pan over high heat and bring water to a rolling boil. Remove pan from heat and cover.")).check(doesNotExist()); //check there is no more instruction

        //add instruction
        onView(withId(R.id.add_button)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//should be disabled because both instruction fields are empty
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("A new main instruction"));
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled()));//now should be enabled because only the main instruction is required
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(typeText("A new sub instruction"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());//check that save button is enabled after changing main instruction and click it
        onView(withText("A new main instruction")).check(matches(isDisplayed())); //check that the edits are there
        onView(withText("A new sub instruction")).check(matches(isDisplayed()));

        onView(withId(R.id.save_button)).check(matches(isDisplayed())).perform(click());

        //Gotta check that edit vere successful
        onData(hasToString(startsWith("Mum's favourite Hard-cooked eggs"))).perform(click());
        onView(withId(R.id.recipe_name)).check(matches(withText("Mum's favourite Hard-cooked eggs")));
        onView(withText("Very small eggs")).check(matches(isDisplayed())); //check the edit
        onView(withText("Kosher salt")).check(doesNotExist()); //check delete
        onView(withText("Avocado Milk")).check(matches(isDisplayed())); //check the addition
        onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("A changed main instruction")).check(matches(isDisplayed())); //check the edits
        onView(withText("A changed sub instruction")).check(matches(isDisplayed()));
        onView(withText("Put pan over high heat and bring water to a rolling boil. Remove pan from heat and cover.")).check(doesNotExist()); //check deletion
        onView(withText("A new main instruction")).check(matches(isDisplayed())); //check that the edits are there
        onView(withText("A new sub instruction")).check(matches(isDisplayed()));
        Espresso.pressBack();


        //Chicken Wrap start cooking 2 times, rate 2 times
        onView(withText("Chicken Wrap")).check(matches(isDisplayed())).perform(click());

        //check the recipe info
        onView(withId(R.id.recipe_name)).check(matches(withText("Chicken Wrap")));
        onView(withId(R.id.estimated_time)).check(matches(withText("Time: Not cooked")));
        onView(withId(R.id.difficulty)).check(matches(withText("Difficulty: -")));
        onView(withId(R.id.taste)).check(matches(withText("Taste: -")));
        onView(withId(R.id.rating)).check(matches(withText("-/5")));

        //check that buttons and textViews exist
        onView(withId(R.id.edit_recipe)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_recipe)).check(matches(isDisplayed()));
        onView(withId(R.id.serving_num)).check(matches(isDisplayed()));
        onView(withText("Ingredients")).check(matches(isDisplayed()));

        onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).perform(click());
        onView(withText("Instructions")).check(matches(isDisplayed()));

        onView(withId(R.id.timer_text)).check(matches(withText("00:00:00")));
        onView(withId(R.id.reset_timer)).check(matches(isDisplayed()));
        onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));

        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

        //WAIT FOR A THANK YOU WINDOW TO PASS
        try {
            Thread.sleep(1500);
        } catch (Exception e) {}
        //check that time value changed
        onView(withId(R.id.estimated_time_rating_activity)).check(matches(withText("Time: 00h 00m 05s")));
        onView(withId(R.id.difficulty_radio_5)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.taste_radio_4)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.submit_rating)).check(matches(isDisplayed())).perform(click());

        //check that difficulty, test and overall rating changed
        //onView(within)
        onView(withText("Difficulty: 5.00")).check(matches(isDisplayed()));
        onView(withText("Taste: 4.00")).check(matches(isDisplayed()));
        onView(withText("4.50/5")).check(matches(isDisplayed()));

        //Second time cook with different time and rate value
        onView(withText("Chicken Wrap")).check(matches(isDisplayed())).perform(click());
        onView(withText("Ingredients")).check(matches(isDisplayed()));
        onView(withText("Instructions")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.reset_timer)).check(matches(isDisplayed()));
        onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));

        //test the reset button on pop-up window
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.reset_timer)).check(matches(withText("RESET"))).perform(click());
        onView(withText("Are you sure you want to reset the timer?")).check(matches(isDisplayed()));
        onView(withText("RESET")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.timer_text)).check(matches(withText("00:00:00")));

        //test the cancel button on pop-up window
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.reset_timer)).check(matches(withText("RESET"))).perform(click());
        onView(withText("Are you sure you want to reset the timer?")).check(matches(isDisplayed()));
        onView(withText("CANCEL")).check(matches(isDisplayed())).perform(click());

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

        //WAIT FOR A THANK YOU WINDOW TO PASS
        try {
            Thread.sleep(1500);
        } catch (Exception e) {}
        //check that time value changed
        onView(withId(R.id.estimated_time_rating_activity)).check(matches(withText("Time: 00h 00m 04s")));
        onView(withId(R.id.difficulty_radio_2)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.taste_radio_4)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.submit_rating)).check(matches(isDisplayed())).perform(click());

        //check that difficulty, test and overall rating changed
        onView(withText("Difficulty: 3.50")).check(matches(isDisplayed()));
        onView(withText("Taste: 4.00")).check(matches(isDisplayed()));
        onView(withText("3.75/5")).check(matches(isDisplayed()));

        //change Chicken Wrap name "A New Chicken Wrap"
        onView(withText("Chicken Wrap")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.edit_recipe)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.recipe_name_edit)).perform(clearText(), typeText("A New Chicken Wrap"));

        //change the second ingredient which is "Ranch Dressing" to "Sweet Dressing", amount to 0.3 and Unit to milligram
        onView(withText("Ranch Dressing")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.editIngredient)).perform(clearText());

        //check that save button is disabled after clearing
        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));

        onView(withId(R.id.editAmount)).perform(clearText());


        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing

        onView(withId(R.id.editAmount)).perform(typeText("0.3"));
        onView(withId(R.id.editIngredient)).perform(typeText("Sweet Dressing"));
        onView(withId(R.id.editIngredientUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("MG"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Sweet Dressing")).check(matches(isDisplayed())); //check the edit

        //delete ingredient "Mozzarella Cheese"
        onView(withText("Mozzarella Cheese")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.delete_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Mozzarella Cheese")).check(doesNotExist()); //check that it is not displayed

        //add new ingredient
        onView(withId(R.id.add_ingredient_button)).check(matches(isDisplayed())).perform(click());


        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.editIngredient)).perform(typeText("Sugar"));
        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())); //should be enabled now
        onView(withId(R.id.editAmount)).perform(clearText(), typeText("150"));

        onView(withId(R.id.editIngredientUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("ML"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Sugar")).check(matches(isDisplayed())); //check the addition

        Espresso.closeSoftKeyboard();

        //follow to edit instructions now
        onView(withId(R.id.next_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

        //check the UI elements are present
        onView(withId(R.id.add_button)).check(matches(isDisplayed()));
        onView(withId(R.id.cancel_button)).check(matches(isDisplayed()));
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));

        //edit instruction
        onView(withText("Lay tortillas on a clean flat surface. Place about 1/2 cup chicken, 1 tablespoon ranch, 2 tablespoons of cheese, and 1 tablespoon of minced cilantro on each tortilla. Fold tightly to form a burrito shape.")).check(matches(isDisplayed())).perform(click());
        //check the UI of the edit_window
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_dialog_button)).check(matches(isDisplayed()));
        onView(withId(R.id.cancel_dialog_button)).check(matches(isDisplayed()));

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(clearText());
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(clearText());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(typeText("A changed sub instruction"));
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after changing sun instruction
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("A changed main instruction"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());//check that save button is enabled after changing main instruction and click it
        onView(withText("A changed main instruction")).check(matches(isDisplayed())); //check the edits
        onView(withText("A changed sub instruction")).check(matches(isDisplayed()));


        //delete instruction
        onView(withText("Heat a heavy-duty pan or grill to medium heat. Coat with a light layer or oil or cooking spray and cook wraps for 1-2 minutes on each side or until the tortilla is crispy and golden. Remove from heat, slice in half and serve immediately.")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.delete_dialog_button)).check(matches(isDisplayed())).perform(click());
        onView(withText("Heat a heavy-duty pan or grill to medium heat. Coat with a light layer or oil or cooking spray and cook wraps for 1-2 minutes on each side or until the tortilla is crispy and golden. Remove from heat, slice in half and serve immediately.")).check(doesNotExist()); //check there is no more instruction

        //add instruction
        onView(withId(R.id.add_button)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//should be disabled because both instruction fields are empty
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("A new main instruction"));
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled()));//now should be enabled because only the main instruction is required
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(typeText("A new sub instruction"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());//check that save button is enabled after changing main instruction and click it
        onView(withText("A new main instruction")).check(matches(isDisplayed())); //check that the edits are there
        onView(withText("A new sub instruction")).check(matches(isDisplayed()));

        onView(withId(R.id.save_button)).check(matches(isDisplayed())).perform(click());

        //Gotta check that edit were successful
        onView(withText("A New Chicken Wrap")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.recipe_name)).check(matches(withText("A New Chicken Wrap")));
        onView(withText("Sweet Dressing")).check(matches(isDisplayed())); //check the edit
        onView(withText("Mozzarella Cheese")).check(doesNotExist()); //check delete
        onView(withText("Sugar")).check(matches(isDisplayed())); //check the addition
        onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("A changed main instruction")).check(matches(isDisplayed())); //check the edits
        onView(withText("A changed sub instruction")).check(matches(isDisplayed()));
        onView(withText("Heat a heavy-duty pan or grill to medium heat. Coat with a light layer or oil or cooking spray and cook wraps for 1-2 minutes on each side or until the tortilla is crispy and golden. Remove from heat, slice in half and serve immediately.")).check(doesNotExist()); //check deletion
        onView(withText("A new main instruction")).check(matches(isDisplayed())); //check that the edits are there
        onView(withText("A new sub instruction")).check(matches(isDisplayed()));
        Espresso.pressBack();


         //test scroll down then test another recipe "Grilled Halloumi Salad"
         onView(ViewMatchers.withId(R.id.recipe_list_view)).perform(ViewActions.swipeUp());
         onView(withText("Grilled Halloumi Salad")).check(matches(isDisplayed())).perform(click());

         //check the recipe info
         onView(withId(R.id.recipe_name)).check(matches(withText("Grilled Halloumi Salad")));
         onView(withId(R.id.estimated_time)).check(matches(withText("Time: Not cooked")));
         onView(withId(R.id.difficulty)).check(matches(withText("Difficulty: -")));
         onView(withId(R.id.taste)).check(matches(withText("Taste: -")));
         onView(withId(R.id.rating)).check(matches(withText("-/5")));

         //check that buttons and textViews exist
         onView(withId(R.id.edit_recipe)).check(matches(isDisplayed()));
         onView(withId(R.id.delete_recipe)).check(matches(isDisplayed()));
         onView(withId(R.id.serving_num)).check(matches(isDisplayed()));
         onView(withText("Ingredients")).check(matches(isDisplayed()));

         onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).perform(click());
         onView(withText("Instructions")).check(matches(isDisplayed()));

         onView(withId(R.id.timer_text)).check(matches(withText("00:00:00")));
         onView(withId(R.id.reset_timer)).check(matches(isDisplayed()));
         onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));

         onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
         onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));
         try {
         Thread.sleep(5000);
         } catch (Exception e) {
         }
         onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
         onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

         //WAIT FOR A THANK YOU WINDOW TO PASS
         try {
         Thread.sleep(1500);
         } catch (Exception e) {}
         //check that time value changed
         onView(withId(R.id.estimated_time_rating_activity)).check(matches(withText("Time: 00h 00m 05s")));
         onView(withId(R.id.difficulty_radio_2)).check(matches(isDisplayed())).perform(click());
         onView(withId(R.id.taste_radio_1)).check(matches(isDisplayed())).perform(click());
         onView(withId(R.id.submit_rating)).check(matches(isDisplayed())).perform(click());

         //check that difficulty, test and overall rating changed
         onView(withText("Difficulty: 2.00")).check(matches(isDisplayed()));
         onView(withText("Taste: 1.00")).check(matches(isDisplayed()));
         onView(withText("1.50/5")).check(matches(isDisplayed()));



         //Second time cook and rate with different value
         onView(ViewMatchers.withId(R.id.recipe_list_view)).perform(ViewActions.swipeUp());
         onView(withText("Grilled Halloumi Salad")).check(matches(isDisplayed())).perform(click());
         onView(withText("Ingredients")).check(matches(isDisplayed()));
         onView(withText("Instructions")).check(matches(isDisplayed())).perform(click());

         onView(withId(R.id.reset_timer)).check(matches(isDisplayed()));
         onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));

         //test the reset button on pop-up window
         onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
         onView(withId(R.id.reset_timer)).check(matches(withText("RESET"))).perform(click());
         onView(withText("Are you sure you want to reset the timer?")).check(matches(isDisplayed()));
         onView(withText("RESET")).check(matches(isDisplayed())).perform(click());
         onView(withId(R.id.timer_text)).check(matches(withText("00:00:00")));

         //test the cancel button on pop-up window
         onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
         onView(withId(R.id.reset_timer)).check(matches(withText("RESET"))).perform(click());
         onView(withText("Are you sure you want to reset the timer?")).check(matches(isDisplayed()));
         onView(withText("CANCEL")).check(matches(isDisplayed())).perform(click());

         try {
         Thread.sleep(3000);
         } catch (Exception e) {
         }
         onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
         onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

         //WAIT FOR A THANK YOU WINDOW TO PASS
         try {
         Thread.sleep(1500);
         } catch (Exception e) {}
         //check that time value changed
         onView(withId(R.id.estimated_time_rating_activity)).check(matches(withText("Time: 00h 00m 04s")));
         onView(withId(R.id.difficulty_radio_1)).check(matches(isDisplayed())).perform(click());
         onView(withId(R.id.taste_radio_5)).check(matches(isDisplayed())).perform(click());
         onView(withId(R.id.submit_rating)).check(matches(isDisplayed())).perform(click());

         //check that difficulty, test and overall rating changed
         onView(withText("Difficulty: 1.50")).check(matches(isDisplayed()));
         onView(withText("Taste: 3.00")).check(matches(isDisplayed()));
         onView(withText("2.25/5")).check(matches(isDisplayed()));

         //third  time cook and rate with different value
         onView(ViewMatchers.withId(R.id.recipe_list_view)).perform(ViewActions.swipeUp());
         onView(withText("Grilled Halloumi Salad")).check(matches(isDisplayed())).perform(click());
         onView(withText("Ingredients")).check(matches(isDisplayed()));
         onView(withText("Instructions")).check(matches(isDisplayed())).perform(click());

         onView(withId(R.id.reset_timer)).check(matches(isDisplayed()));
         onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));

         //test the reset button on pop-up window
         onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
         onView(withId(R.id.reset_timer)).check(matches(withText("RESET"))).perform(click());
         onView(withText("Are you sure you want to reset the timer?")).check(matches(isDisplayed()));
         onView(withText("RESET")).check(matches(isDisplayed())).perform(click());
         onView(withId(R.id.timer_text)).check(matches(withText("00:00:00")));

         //test the cancel button on pop-up window
         onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
         onView(withId(R.id.reset_timer)).check(matches(withText("RESET"))).perform(click());
         onView(withText("Are you sure you want to reset the timer?")).check(matches(isDisplayed()));
         onView(withText("CANCEL")).check(matches(isDisplayed())).perform(click());

         try {
         Thread.sleep(3000);
         } catch (Exception e) {
         }
         onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
         onView(withId(R.id.finish_cooking_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

         //WAIT FOR A THANK YOU WINDOW TO PASS
         try {
         Thread.sleep(1500);
         } catch (Exception e) {}
         //check that time value changed
         onView(withId(R.id.estimated_time_rating_activity)).check(matches(withText("Time: 00h 00m 04s")));
         onView(withId(R.id.difficulty_radio_1)).check(matches(isDisplayed())).perform(click());
         onView(withId(R.id.taste_radio_5)).check(matches(isDisplayed())).perform(click());
         onView(withId(R.id.submit_rating)).check(matches(isDisplayed())).perform(click());

         //check that difficulty, test and overall rating changed
         onView(withText("Difficulty: 1.33")).check(matches(isDisplayed()));
         onView(withText("Taste: 3.67")).check(matches(isDisplayed()));
         onView(withText("2.50/5")).check(matches(isDisplayed()));



        //change Grilled Halloumi Salad name "A New Grilled Halloumi Salad"
        onView(ViewMatchers.withId(R.id.recipe_list_view)).perform(ViewActions.swipeUp());
        onView(withText("Grilled Halloumi Salad")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.edit_recipe)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.recipe_name_edit)).perform(clearText(), typeText("A New Grilled Halloumi Salad"));

        //change the third ingredient which is "Chopped Cucumber" to "Cucumber", amount to 0.3 and Unit to milligram
        onView(withText("Chopped Cucumber")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.editIngredient)).perform(clearText());

        //check that save button is disabled after clearing
        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));

        onView(withId(R.id.editAmount)).perform(clearText());


        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing

        onView(withId(R.id.editAmount)).perform(typeText("0.3"));
        onView(withId(R.id.editIngredient)).perform(typeText("Cucumber"));
        onView(withId(R.id.editIngredientUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("MG"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Cucumber")).check(matches(isDisplayed())); //check the edit
        Espresso.closeSoftKeyboard();

        //delete ingredient "Salt"
        onView(ViewMatchers.withId(R.id.ingredients_list_edit)).perform(ViewActions.swipeUp());
        onView(withText("Salt")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.delete_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Salt")).check(doesNotExist()); //check that it is not displayed

        //add new ingredient
        onView(withId(R.id.add_ingredient_button)).check(matches(isDisplayed())).perform(click());


        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.editIngredient)).perform(typeText("Sugar"));
        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())); //should be enabled now
        onView(withId(R.id.editAmount)).perform(clearText(), typeText("150"));

        onView(withId(R.id.editIngredientUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("ML"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(ViewMatchers.withId(R.id.ingredients_list_edit)).perform(ViewActions.swipeDown());
        onView(withText("Sugar")).check(matches(isDisplayed())); //check the addition

        Espresso.closeSoftKeyboard();

        //follow to edit instructions now
        onView(withId(R.id.next_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());

        //check the UI elements are present
        onView(withId(R.id.add_button)).check(matches(isDisplayed()));
        onView(withId(R.id.cancel_button)).check(matches(isDisplayed()));
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));

        //edit instruction
        onView(withText("Cook the farro.")).check(matches(isDisplayed())).perform(click());
        //check the UI of the edit_window
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_dialog_button)).check(matches(isDisplayed()));
        onView(withId(R.id.cancel_dialog_button)).check(matches(isDisplayed()));

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(clearText());
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(clearText());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(typeText("A changed sub instruction"));
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after changing sun instruction
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("A changed main instruction"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());//check that save button is enabled after changing main instruction and click it
        onView(withText("A changed main instruction")).check(matches(isDisplayed())); //check the edits
        onView(withText("A changed sub instruction")).check(matches(isDisplayed()));


        //delete instruction
        onView(withText("Season.")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.delete_dialog_button)).check(matches(isDisplayed())).perform(click());
        onView(withText("Season.")).check(doesNotExist()); //check there is no more instruction

        //add instruction
        onView(withId(R.id.add_button)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//should be disabled because both instruction fields are empty
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("A new main instruction"));
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled()));//now should be enabled because only the main instruction is required
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sub_instruction)).check(matches(isDisplayed())).perform(typeText("A new sub instruction"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.save_dialog_button)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());//check that save button is enabled after changing main instruction and click it
        onView(withText("A new main instruction")).check(matches(isDisplayed())); //check that the edits are there
        onView(withText("A new sub instruction")).check(matches(isDisplayed()));

        onView(withId(R.id.save_button)).check(matches(isDisplayed())).perform(click());

        //Gotta check that edit were successful
        onView(withText("A New Grilled Halloumi Salad")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.recipe_name)).check(matches(withText("A New Grilled Halloumi Salad")));
        onView(withText("Cucumber")).check(matches(isDisplayed())); //check the edit
        onView(ViewMatchers.withId(R.id.ingredients_list)).perform(ViewActions.swipeUp());
        onView(withText("Salt")).check(doesNotExist()); //check delete
        onView(ViewMatchers.withId(R.id.ingredients_list)).perform(ViewActions.swipeDown());
        onView(withText("Sugar")).check(matches(isDisplayed())); //check the addition
        onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("A changed main instruction")).check(matches(isDisplayed())); //check the edits
        onView(withText("A changed sub instruction")).check(matches(isDisplayed()));
        onView(withText("Season.")).check(doesNotExist()); //check deletion
        onView(withText("A new main instruction")).check(matches(isDisplayed())); //check that the edits are there
        onView(withText("A new sub instruction")).check(matches(isDisplayed()));
        Espresso.pressBack();


        //delete the changed recipes
        onView(ViewMatchers.withId(R.id.recipe_list_view)).perform(ViewActions.swipeDown());
        onData(hasToString(startsWith("Mum's favourite Hard-cooked eggs"))).perform(click());
        onView(withId(R.id.delete_recipe)).check(matches(isDisplayed())).perform(click());
        onView(withText("YES")).check(matches(isDisplayed())).perform(click());

        onView(withText("Mum's favourite Hard-cooked eggs")).check(doesNotExist()); //check recipes are not there


        //delete the changed Chicken Wrap
        onView(ViewMatchers.withId(R.id.recipe_list_view)).perform(ViewActions.swipeDown());
        onData(hasToString(startsWith("A New Chicken Wrap"))).perform(click());
        onView(withId(R.id.delete_recipe)).check(matches(isDisplayed())).perform(click());
        onView(withText("YES")).check(matches(isDisplayed())).perform(click());

        onView(withText("A New Chicken Wrap")).check(doesNotExist()); //check recipes are not there

        //delete the changed Grilled Halloumi Salad
        onView(ViewMatchers.withId(R.id.recipe_list_view)).perform(ViewActions.swipeUp());
        onData(hasToString(startsWith("A New Grilled Halloumi Salad"))).perform(click());
        onView(withId(R.id.delete_recipe)).check(matches(isDisplayed())).perform(click());
        onView(withText("YES")).check(matches(isDisplayed())).perform(click());

        onView(withText("A New Grilled Halloumi Salad")).check(doesNotExist()); //check recipes are not there


        //add the default recipes
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText("Hard-cooked eggs"));
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());

        //add ingredients
        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Large eggs"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("2.0"));
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Kosher salt"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.25"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("TSP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Fresh Lime or Lemon Juice"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("1.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("TBSP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Minced Red Onion"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("4.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("TBSP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Jalapeno chillis"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("2.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("QUANTITY"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Cilantro"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("2.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("TBSP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Black Pepper"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("1.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("PINCH"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Ripe Tomato"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.5"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("QUANTITY"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());


        onView(withId(R.id.save_all_ingredients)).check(matches(isDisplayed())).perform(click());

        //add instructions
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Place eggs in a saucepan or pot and cover with cold water."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Eggs first, then water. Why? Because if you put the eggs in afterward, they might crack as they fall to the bottom of the pan. It''s no fun to learn this the hard way."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());
        
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Put pan over high heat and bring water to a rolling boil. Remove pan from heat and cover."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("How long does it take to boil an egg? Well, actually, you want the water to come just to a boil but not stay there. Eggs exposed to high heat for a long time go through a chemical reaction that turns the yolks green. So the answer to \"How long do you boil hard boiled eggs?\" is: pretty much not at all. Because the eggs cook in water that''s not actually boiling, some people use the term \"hard-cooked\" instead of \"hard-boiled\" eggs."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Drain eggs immediately and put in a bowl filled with water and ice cubes."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Why ice water? It cools the eggs down and prevents the green yolk problem. (Chilled water isn''t cold enough - you want cold water with lots of ice cubes floating in it.) If you''re planning to peel the eggs, crack them slightly before putting them in the ice water and let them sit for an hour for maximum ease of peeling."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        //save the recipe
        onView(withId(R.id.finishAdding)).check(matches(isDisplayed())).perform(click());

        //add the default recipes Chicken Wrap
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText("Chicken Wrap"));
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());

        //add ingredients
        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Grilled Chicken Breasts copped"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("2.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Ranch Dressing"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.25"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Mozzarella Cheese"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.5"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Cilantro"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.25"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("8 inch tortillas"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("4.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("ML"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());


        onView(withId(R.id.save_all_ingredients)).check(matches(isDisplayed())).perform(click());

        //add instructions
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Lay tortillas on a clean flat surface. Place about 1/2 cup chicken, 1 tablespoon ranch, 2 tablespoons of cheese, and 1 tablespoon of minced cilantro on each tortilla. Fold tightly to form a burrito shape."));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Heat a heavy-duty pan or grill to medium heat. Coat with a light layer or oil or cooking spray and cook wraps for 1-2 minutes on each side or until the tortilla is crispy and golden. Remove from heat, slice in half and serve immediately."));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        //save the recipe
        onView(withId(R.id.finishAdding)).check(matches(isDisplayed())).perform(click());

        //add the default recipes Grilled Halloumi Salad
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText("Grilled Halloumi Salad"));
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());

        //add ingredients
        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Halloumi Cheese, sliced into 1/4 inch thick slices"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("250"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("G"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Packed Spring Greens"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("2.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Chopped Cucumber"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("1.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Chopped Pineapple"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("1.50"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Red Onion, thinly sliced"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.2"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("QUANTITY"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Toasted Almonds"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.25"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Olive Oil"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("3.0"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("TBSP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Lemon Juice"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.25"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("CUP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Cayenne Pepper"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.25"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("TSP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Salt"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("0.5"));
        onView(withId(R.id.spinnerUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("TSP"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());


        onView(withId(R.id.save_all_ingredients)).check(matches(isDisplayed())).perform(click());

        //add instructions
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Cook the farro."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Cook the farro in 3 cups of water or vegetable stock according to these instructions until it is chewy and tender. (Cooking time will vary depending on what type of farro you use.) Drain the farro in a fine-mesh strainer and add it to a large mixing bowl."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Make the dressing."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Meanwhile, whisk all ingredients together in a small bowl (or shake in a covered mason jar) until completely combined. Set aside until ready to use."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Pan-grill the halloumi."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Meanwhile, heat the olive oil in a large grill pan or saute pan over high heat. Add however many halloumi strips that you can fit in an even layer, then cook for about 30 seconds per side or until the cheese is lightly browned. Transfer the halloumi to the large mixing bowl with the farro, then repeat with the remaining strips until all of the halloumi has been cooked."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Toss the salad."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Add the remaining ingredients (arugula, cucumber, red onion, mint, Kalamata olives and pine nuts) to the large mixing bowl. Drizzle evenly with the dressing, and then toss until the salad is completely combined."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Season."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Taste and season the salad with extra salt, black pepper and/or lemon juice, if needed."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("Serve."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Serve immediately and enjoy!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());

        //save the recipe
        onView(withId(R.id.finishAdding)).check(matches(isDisplayed())).perform(click());


    }

    @Test
    public void testInvalidCases()
    {
        //test adding empty string recipe
        onView(withId(R.id.add)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText("")); //empty
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());

        onView(withText("Recipe name required")).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText(" "));// single space
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Recipe name required")).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText("                    ")); //many spaces
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Recipe name required")).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText("1111111"));
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());

        //add an ingredient
        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Large eggs"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("2.0"));
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());

        //try to add the same ingredient
        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Large eggs"));
        onView(withId(R.id.ingredientAmount)).check(matches(isDisplayed())).perform(typeText("2.0"));
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Ingredient already exists!")).check(matches(isDisplayed()));
        Espresso.pressBack();

        //try to add empty ingredient
        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText(""));//empty
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Ingredient name required")).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText(" "));//one space
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Ingredient name required")).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("           "));//many spaces
        onView(withId(R.id.addIngredientButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Ingredient name required")).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.save_all_ingredients)).check(matches(isDisplayed())).perform(click());

        //add empty instruction
        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("")); //empty
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.instructionsSteps)).check(matches(isDisplayed())).perform(typeText("Cook the farro in 3 cups of water or vegetable stock according to these instructions until it is chewy and tender. (Cooking time will vary depending on what type of farro you use.) Drain the farro in a fine-mesh strainer and add it to a large mixing bowl."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Instructions cannot be empty")).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText(" ")); //one space
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Instructions cannot be empty")).check(matches(isDisplayed()));
        Espresso.pressBack();

        onView(withId(R.id.instruction)).check(matches(isDisplayed())).perform(typeText("         ")); //many space
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.addInstructionButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Instructions cannot be empty")).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

    @Test
    public void testEdgeCases()
    {
        // test adding same name recipes
        onView(withId(R.id.add)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText("Pancakes")); //already exists by default
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());

        onView(withText("Duplicate Recipe Name")).check(matches(isDisplayed()));
        onView(withText("CONTINUE")).check(matches(isDisplayed())).perform(click());
        Espresso.closeSoftKeyboard();

        Espresso.pressBack();

        //pressing and starting start stop
        onView(withId(R.id.recipes)).check(matches(isDisplayed())).perform(click());
        onData(hasToString(startsWith("Hard-cooked eggs"))).perform(click());
        onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).perform(click());

        //should not increment the timer
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("START"))).perform(click());
        onView(withId(R.id.start_stop_timer)).check(matches(withText("STOP"))).perform(click());

        onView(withText("00:00:00")).check(matches(isDisplayed())); //timer should be zeroed
    }
}
