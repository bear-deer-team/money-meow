package com.example.money_meow;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import com.example.money_meow.manageEngine.searchEngine.SearchEngine;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SearchingUITest {
    @Rule
    public final ActivityTestRule<SearchEngine> activityTestRule =
            new ActivityTestRule<>(SearchEngine.class);

    /*
        Check filter activity
     */
    @Test
    public void filterButtonActive () {
        onView(withId(R.id.FilterBtn)).perform(click());
        onView(withId(R.id.linearLayout0)).check(matches(isDisplayed()));

    }

    /*
        Check search box
     */
    @Test
    public void typeInSearchBox () {
        onView(withId(R.id.edit_text_search)).perform(typeText("buying"));
    }
}
