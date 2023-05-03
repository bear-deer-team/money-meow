package com.example.money_meow;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.money_meow.transaction.TransactionAction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TransactionActionUITest {
    @Rule
    public final ActivityTestRule<TransactionAction> activityTestRule =
            new ActivityTestRule<>(TransactionAction.class);

    /*
        Check when type in date and amount box
     */
    @Test
    public void typeInInputBox () {
        onView(withId(R.id.edit_text_date)).perform(clearText()).perform(typeText("14/03/2023"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edit_text_amount)).perform(clearText()).perform(typeText("100"), ViewActions.closeSoftKeyboard());
    }

    /*
        Check if type wrong date format
     */
    @Test
    public void setErrorWhenWrongDateFormat () {
        final String WRONG_DATE_FORMAT = "14/2023";
        onView(withId(R.id.edit_text_date)).perform(clearText()).perform(typeText(WRONG_DATE_FORMAT), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.AcptTransBtn)).perform(click());

        onView(withId(R.id.edit_text_date)).check(matches(hasErrorText("Invalid datetime!")));

    }

    /*
        Check accept button activity
     */
    @Test
    public void acceptButtonActive () {
        onView(withId(R.id.AcptTransBtn)).perform(click());
    }

    /*
    * Check when type in note field
    */
    @Test
    public void checkNoteInput () {
        final String TYPE_TEXT = "Type something silly";
        onView(withId(R.id.note)).perform(click());
        onView(withId(R.id.note)).perform(clearText()).perform(typeText(TYPE_TEXT), closeSoftKeyboard());
        onView(withId(R.id.showBtn)).perform(click());
        onView(withId(R.id.note)).check(matches(withText(TYPE_TEXT)));
    }

    /*
        Check category list
     */
    @Test
    public void catagoryShowTest () {
        onView(withId(R.id.categoryBtn)).perform(click());
        onView(withId(R.id.categoryLayout)).check(matches(isDisplayed()));
    }

}
