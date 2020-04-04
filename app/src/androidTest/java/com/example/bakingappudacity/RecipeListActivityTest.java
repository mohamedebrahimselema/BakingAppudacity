package com.example.bakingappudacity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.bakingappudacity.activities.RecipeListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by kehinde on 6/7/17.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    public static final String RECIPE_EXTRA = "RECIPE_EXTRA";
    private IdlingResource mIdlingResource;


    @Rule
    public ActivityTestRule<RecipeListActivity> activityTestRule= new ActivityTestRule<>(RecipeListActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        // Register Idling Resources
        Espresso.registerIdlingResources(mIdlingResource);

    }

    /**
     * Checks if the Recipe List RecyclerView is displayed
     * */
    @Test
    public void RecipeListRecyclerViewIsDisplayed(){

        // Check that the Recycler View is  displayed
        onView(withId(R.id.recipe_list_recycler)).check(matches(isDisplayed()));
    }

    @Test
    public void clickRecyclerViewItemOpensRecipeStepListActivity(){

        // Click on the RecyclerView item at position 1
        onView(withId(R.id.recipe_list_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


        onView(withId(R.id.step_recycler)).check(matches(isDisplayed()));


    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
