package com.example.hudlup

import com.example.hudlup.onboarding.SignUpViewModel
import junit.framework.Assert.assertEquals
import org.junit.Test

class SignUpViewModelTest {
    private val viewModel = SignUpViewModel()
    @Test
    fun testIfPasswordsAreTheSame(){
        assertEquals(true, viewModel.checkPasswordMatches("mypassword", "mypassword"))
        assertEquals(false, viewModel.checkPasswordMatches("mypassword", "mypassword23"))
    }

    @Test
    fun testIfAgeIsOver18(){
        assertEquals(false, viewModel.checkAgeIsOver18("mypassword"))
        assertEquals(true, viewModel.checkAgeIsOver18("18"))
        assertEquals(false, viewModel.checkAgeIsOver18("17"))
    }
}