package com.example.money_meow.accountAction;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

import android.view.View;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.money_meow.R;
import com.example.money_meow.account.login.LoginAction;
import com.example.money_meow.account.signup.SignupAction;
import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignUpUITest {
    @Rule
    public final ActivityTestRule<SignupAction> activityTestRule =
            new ActivityTestRule<>(SignupAction.class);

    @Before
    public void setUp () {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void emptySignUpForm () {
        onView(withId(R.id.signup_btn)).perform(click());

        onView(withId(R.id.user_name)).check
                (matches(hasTextInputLayoutErrorText("This field mustn't be empty!")));

    }

    private Matcher<? super View> hasTextInputLayoutErrorText(String s) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return s.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    @Test
    public void signUpNotMatchesPasswordTest() {
        onView(allOf(
                isDescendantOfA(withId(R.id.password)),
                withClassName(endsWith("EditText"))
        )).perform(clearText()).perform(typeText("Rine@12"), ViewActions.closeSoftKeyboard());

        onView(allOf(
                isDescendantOfA(withId(R.id.cf_password)),
                withClassName(endsWith("EditText"))
        )).perform(clearText()).perform(typeText("Rine@123"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.signup_btn)).perform(click());

        onView(withText("Confirm Password doesn't match password.")).check(matches(isDisplayed()));
    }

    @Test
    public void signUpToLoginTest() {
        Intents.init();
        onView(withId(R.id.signup_btn)).perform(click());
        Intents.release();

    }
}

