package com.example.money_meow;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.money_meow.setting.Settings;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SettingUITest {
    @Rule
    public final ActivityTestRule<Settings> activityTestRule =
            new ActivityTestRule<>(Settings.class);

    /*
        Check open app information
     */
    @Test
    public void informationNavigation () {
        Intents.init();
        onView(withId(R.id.infor)).perform(click());
        Intents.release();
    }

    /*
     Check account information is display
     */
    @Test
    public void accInforDisplayed () {
        Intents.init();
        onView(withId(R.id.editAcc)).perform(click());
        Intents.release();
        onView(withId(R.id.username)).check(matches(isDisplayed()));
    }

    /*
        Check logout
     */
    public void logoutNavigation () {
        Intents.init();
        onView(withId(R.id.logout)).perform(click());
        Intents.release();
    }
}
