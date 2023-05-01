package com.example.money_meow.accountAction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.money_meow.R;
import com.example.money_meow.account.login.LoginAction;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class LoginUITest {
    @Rule
    public final ActivityTestRule<LoginAction> activityTestRule =
            new ActivityTestRule<>(LoginAction.class);

    @Before
    public void setUp () {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loginTest() {
        onView(allOf(
                isDescendantOfA(withId(R.id.user_name)),
                withClassName(endsWith("EditText"))
        )).perform(clearText()).perform(typeText("rine03"));
        onView(allOf(
                isDescendantOfA(withId(R.id.password)),
                withClassName(endsWith("EditText"))
        )).perform(clearText()).perform(typeText("Rine@123"));

        //onView(withId(R.id.login_btn)).perform(click());
    }

    @Test
    public void loginToSignupTest() {
        Intents.init();
        onView(withId(R.id.signup_btn)).perform(click());
        Intents.release();

    }
}
