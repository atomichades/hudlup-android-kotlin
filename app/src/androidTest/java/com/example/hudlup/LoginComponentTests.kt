package com.example.hudlup

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.hudlup.onboarding.LoginFragment
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.*

@RunWith(AndroidJUnit4::class)
class LoginComponentTests {
    @Before
    fun setUp(){
        launchFragmentInContainer<LoginFragment>(themeResId = R.style.Theme_Hudlup)
    }
    @Test
    fun testEmailandPasswordEmptyError(){
        onView(withId(R.id.login_btn)).perform(click())
        onView(withId(R.id.emailEditTxt)).check(ViewAssertions
            .matches(hasErrorText("Must not be empty")))
        onView(withId(R.id.emailEditTxt)).check(ViewAssertions
            .matches(hasErrorText("Must not be empty")))
    }

    @Test
    fun testEmailValidationError(){
        onView(withId(R.id.emailEditTxt)).perform(typeText("asda123"))
        onView(isRoot()).perform(closeSoftKeyboard())
        onView(withId(R.id.login_btn)).perform(click())
        onView(withId(R.id.emailEditTxt)).check(ViewAssertions
            .matches(hasErrorText("Must be a valid email address")))
    }

}