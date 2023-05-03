package com.example.money_meow;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.money_meow.home.Home;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomeUITest {
    private final int FINAL_INDEX = 9;

    @Rule
    public final ActivityTestRule<Home> activityTestRule =
            new ActivityTestRule<>(Home.class);

    /*
        Recycle View Test
    */
    //Test scroll to final item in list
    @Test
    public void scrollTofinalItem () {
        onView(ViewMatchers.withId(R.id.historylist))
                .perform(RecyclerViewActions.actionOnItemAtPosition(FINAL_INDEX, click()));
    }



}
