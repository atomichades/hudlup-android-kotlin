package com.example.hudlup

import com.example.hudlup.util.TextValidator
import junit.framework.Assert.assertEquals
import org.junit.Test

class ValidatorTest {

    @Test
    fun testIfStringContainsSpecialCharacters(){
        assertEquals(true,TextValidator.hasSpecialCharacters("asdasd1234"))
        assertEquals(true,TextValidator.hasSpecialCharacters("@!*"))
        assertEquals(false,TextValidator.hasSpecialCharacters("James"))
    }

    @Test
    fun testIfEmailValidates(){
        assertEquals(true, TextValidator.isEmailAddress("abc@gmail.com"))
        assertEquals(false, TextValidator.isEmailAddress("abc@gmailcom"))
        assertEquals(false, TextValidator.isEmailAddress("abcgmail.com"))
        assertEquals(false, TextValidator.isEmailAddress("abcgmailcom"))
        assertEquals(false, TextValidator.isEmailAddress("abc123"))
    }
}