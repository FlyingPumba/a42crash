package com.a42crash.iarcuschin.a42crash
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityUnitTests {

    @get:Rule
    val acitivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testString40() {
        val editText = onView(withId(R.id.editText))
        editText.perform(replaceText("40"));
        editText.check(matches(withText("40")))

        val button = onView(withId(R.id.button))
        button.perform(click())
    }

    @Test
    fun testString42() {
        val editText = onView(withId(R.id.editText))
        editText.perform(replaceText("42"));
        editText.check(matches(withText("42")))

        val button = onView(withId(R.id.button))
        button.perform(click())
    }
}
