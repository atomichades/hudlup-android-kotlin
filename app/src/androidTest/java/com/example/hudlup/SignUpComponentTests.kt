package com.example.hudlup

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hudlup.onboarding.LoginFragment
import com.example.hudlup.onboarding.SignUpFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpComponentTests {
    @Before
    fun setUp(){
        launchFragmentInContainer<SignUpFragment>(themeResId = R.style.Theme_Hudlup)
    }

    @Test
    fun testAllTextFieldWhenEmptyErrorMessages(){
        Espresso.onView(ViewMatchers.withId(R.id.signUp_btn)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.firstnameEditTxt)).check(
            ViewAssertions
            .matches(ViewMatchers.hasErrorText("You must enter a valid firstname")))
        Espresso.onView(ViewMatchers.withId(R.id.lastnameEditTxt)).check(
            ViewAssertions
                .matches(ViewMatchers.hasErrorText("You must enter a valid lastname")))
        Espresso.onView(ViewMatchers.withId(R.id.emailEditTxt)).check(
            ViewAssertions
                .matches(ViewMatchers.hasErrorText("You must enter your email address")))
        Espresso.onView(ViewMatchers.withId(R.id.ageEditTxt)).check(
            ViewAssertions
                .matches(ViewMatchers.hasErrorText("You must enter your age")))
        Espresso.onView(ViewMatchers.withId(R.id.password1EditTxt)).check(
            ViewAssertions
                .matches(ViewMatchers.hasErrorText("You must enter a password")))

    }

    @Test
    fun testPasswordDoesntMatchError(){
        Espresso.onView(ViewMatchers.withId(R.id.password1EditTxt))
            .perform(ViewActions.typeText("asda123"))
        Espresso.onView(ViewMatchers.withId(R.id.password2EditTxt))
            .perform(ViewActions.typeText("asda12345"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.signUp_btn)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.password1EditTxt)).check(
            ViewAssertions
                .matches(ViewMatchers.hasErrorText("Your passwords do not match")))
    }

}