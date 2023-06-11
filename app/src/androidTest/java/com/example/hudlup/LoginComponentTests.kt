package com.example.hudlup

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.hudlup.onboarding.LoginActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.*

@RunWith(AndroidJUnit4::class)
class LoginComponentTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.hudlup", appContext.packageName)
    }

    //TODO: check edit texts for selectable item change colour
//    fun testEditTextChangesColourOnSelect(){
//        onView(withId(R.id.emailEditTxt)).perform(click())
//        onView(withId(R.id.emailEditTxt)).check(matches())
//    }

}