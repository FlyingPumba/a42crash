package com.a42crash.iarcuschin.a42crash

import android.widget.Button
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        (activity.findViewById(R.id.button) as Button).performClick()

//        val expectedIntent = Intent(activity, LoginActivity::class.java)
//        val actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity()
//        assertEquals(expectedIntent.getComponent(), actual.getComponent())
    }
}
