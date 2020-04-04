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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class RecipeStepListActivityTest {

    private static final String INTRODUCTION = "Recipe Introduction";
    private IdlingResource mIdlingResource;


    @Rule
    public ActivityTestRule<RecipeListActivity> activityTestRule= new ActivityTestRule<>(RecipeListActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        // Register Idling Resources
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void clickRecyclerViewItemDisplaysTheRecipeDescription(){

        // Click on the Recipe List RecyclerView item at position 1
        onView(withId(R.id.recipe_list_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.step_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.txt_instruction)).check(matches(withText(INTRODUCTION)));


    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
