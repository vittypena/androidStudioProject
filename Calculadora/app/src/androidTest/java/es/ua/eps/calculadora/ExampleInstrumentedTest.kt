package es.ua.eps.calculadora


import androidx.test.espresso.*
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityRule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun textView(){
        assertNotNull(R.id.tvResult)
        assertNotNull(R.id.btCalcular)
        assertNotNull(R.id.etSumando1)
        assertNotNull(R.id.etSumando2)
    }

    @Test
    fun testViewsCreados(){
        onView(withId(R.id.etSumando1)).check(ViewAssertions.matches(ViewMatchers.withText("")))
        onView(withId(R.id.etSumando2)).check(ViewAssertions.matches(ViewMatchers.withText("")))
        onView(withId(R.id.tvResult)).check(ViewAssertions.matches(ViewMatchers.withText("0")))
    }

    @Test
    fun testSuma(){
        onView(withId(R.id.etSumando1)).perform(typeText("10"))
        onView(withId(R.id.etSumando2)).perform(typeText("22.3"))
        onView(withId(R.id.btCalcular)).perform(ViewActions.click())
        onView(withId(R.id.tvResult)).check(ViewAssertions.matches(ViewMatchers.withText("32.3")))
    }
}