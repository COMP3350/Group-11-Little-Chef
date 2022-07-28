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
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
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

    //typical cases that average user would follow, typical cooking, rating, edit, delete and add activities
    @Test
    public void testTypicalCases()
    {
        onData(hasToString(startsWith("Guacamole"))).perform(click());

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


        onData(hasToString(startsWith("Guacamole"))).perform(click());

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
        onView(withId(R.id.recipe_name_edit)).check(matches(withText("Guacamole")));

        //Change Guacamole name "Mum's favourite Guacamole"
        onView(withId(R.id.recipe_name_edit)).perform(clearText(), typeText("Mum's favourite Guacamole"));

        //change the first ingredient which is "Ripe Avocados" to "Big Sweet Ripe Avocados", amount to 300 and Unit to grams
        onView(withText("Ripe avocados")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.editIngredient)).perform(clearText());

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing

        onView(withId(R.id.editAmount)).perform(clearText());


        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(not(isEnabled())));//check that save button is disabled after clearing

        onView(withId(R.id.editAmount)).perform(typeText("4"));
        onView(withId(R.id.editIngredient)).perform(typeText("Big Sweet Ripe Avocados"));
        onView(withId(R.id.editIngredientUnit)).check(matches(isDisplayed())).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("G"))).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.save_dialog_box)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("Big Sweet Ripe Avocados")).check(matches(isDisplayed())); //check the edit

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
        onData(hasToString(startsWith("Mum's favourite Guacamole"))).perform(click());
        onView(withId(R.id.recipe_name)).check(matches(withText("Mum's favourite Guacamole")));
        onView(withText("Big Sweet Ripe Avocados")).check(matches(isDisplayed())); //check the edit
        onView(withText("Kosher salt")).check(doesNotExist()); //check delete
        onView(withText("Avocado Milk")).check(matches(isDisplayed())); //check the addition
        onView(withId(R.id.start_cooking)).check(matches(isDisplayed())).check(matches(isEnabled())).perform(click());
        onView(withText("A changed main instruction")).check(matches(isDisplayed())); //check the edits
        onView(withText("A changed sub instruction")).check(matches(isDisplayed()));
        onView(withText("Put pan over high heat and bring water to a rolling boil. Remove pan from heat and cover.")).check(doesNotExist()); //check deletion
        onView(withText("A new main instruction")).check(matches(isDisplayed())); //check that the edits are there
        onView(withText("A new sub instruction")).check(matches(isDisplayed()));
        Espresso.pressBack();


        //TODO WEN add another two (Chicken Wrap(start cooking 2 times, rate 2 times), Grilled Halloumi Salad(start cooking 3 times, rate 3 times))

        //delete the changed recipes
        onView(withText("Mum's favourite Guacamole")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.delete_recipe)).check(matches(isDisplayed())).perform(click());
        onView(withText("YES")).check(matches(isDisplayed())).perform(click());

        onView(withText("Mum's favourite Guacamole")).check(doesNotExist()); //check recipes are not there

        //add the default recipes
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.nameInput)).check(matches(isDisplayed())).perform(typeText("Guacamole"));
        onView(withId(R.id.addRecipeButton)).check(matches(isDisplayed())).perform(click());

        //add ingredients
        onView(withId(R.id.ingredientName)).check(matches(isDisplayed())).perform(typeText("Ripe avocados"));
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



    }

    @Test
    public void testInvalidCases() //Trying to add empty string recipe name, trying to same ingredients
    {

    }

    @Test
    public void testEdgeCases() //adding same named recipe, adding long recipe names, adding long ingredient names
    {

    }

}
