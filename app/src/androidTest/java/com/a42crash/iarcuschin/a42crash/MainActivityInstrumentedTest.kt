package com.a42crash.iarcuschin.a42crash

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testString40() {
        val editText = Espresso.onView(ViewMatchers.withId(R.id.editText))
        editText.perform(ViewActions.replaceText("40"));
        editText.check(ViewAssertions.matches(ViewMatchers.withText("40")))

        val button = Espresso.onView(ViewMatchers.withId(R.id.button))
        button.perform(ViewActions.click())
    }

    @Test
    fun testString42() {
        val editText = Espresso.onView(ViewMatchers.withId(R.id.editText))
        editText.perform(ViewActions.replaceText("42"));
        editText.check(ViewAssertions.matches(ViewMatchers.withText("42")))

        val button = Espresso.onView(ViewMatchers.withId(R.id.button))
        button.perform(ViewActions.click())
    }

}
