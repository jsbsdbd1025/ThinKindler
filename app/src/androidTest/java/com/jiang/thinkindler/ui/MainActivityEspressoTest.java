package com.jiang.thinkindler.ui;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jiang.thinkindler.R;
import com.jiang.thinkindler.matcher.TextMatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by jiang on 2017/6/19.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityEspressoTest {

    public static final String STRING_TO_BE_TYPED = "Android";

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityRule.getActivity().getDoubanIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void validateEdittextHasHint() {
        // Type text and then press the button.
        onView(withId(R.id.edt_search))
                .perform(typeText(""));

        onView(withId(R.id.edt_search))
                .check(matches(TextMatcher.withHint()));
    }

    @Test
    public void testLoadDataAndClickItem() {
        // Type text and then press the button.
        onView(withId(R.id.edt_search))
                .perform(typeText(STRING_TO_BE_TYPED), pressImeActionButton());

        onView(withId(R.id.rv_book))
                .perform(RecyclerViewActions.scrollToPosition(10));

        onView(withId(R.id.rv_book))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
