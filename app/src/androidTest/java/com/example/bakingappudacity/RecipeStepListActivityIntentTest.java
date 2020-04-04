package com.example.bakingappudacity;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.bakingappudacity.activities.RecipeListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class RecipeStepListActivityIntentTest {

    private static final String STEP_EXTRA = "STEP_EXTRA";
    private IdlingResource mIdlingResource;


    @Rule
    public IntentsTestRule<RecipeListActivity> intentsTestRule = new IntentsTestRule<>(
            RecipeListActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = intentsTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }


    @Test
    public void clickRecyclerViewItemHasIntentWithAKey() {

        onView(withId(R.id.recipe_list_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        if (onView(withId(R.id.detail_container)) == null) {
            onView(withId(R.id.step_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            intended(hasExtraWithKey(STEP_EXTRA));
        }

    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
