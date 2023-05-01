package com.example.money_meow;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {
    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp () {
    }

    @After
    public void tearDown() {

    }
    @Test
    public void iconDisplay() {
        onView(withId(R.id.wellcome_image)).check(matches(isDisplayed()));
    }

    @Test
    public void isStartBtnClicked() {
        Intents.init();
        onView(withId(R.id.start_button)).perform(click());
        Intents.release();
    }
}
