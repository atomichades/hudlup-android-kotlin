package com.example.hudlup

import com.example.hudlup.util.TextValidator
import junit.framework.Assert.assertEquals
import org.junit.Test

class ValidatorTest {
    val textValidator= TextValidator()
    @Test
    fun testIfStringContainsSpecialCharacters(){
        assertEquals(true,textValidator.hasSpecialCharacters("asdasd1234"))
        assertEquals(true,textValidator.hasSpecialCharacters("@!*"))
        assertEquals(false,textValidator.hasSpecialCharacters("James"))
    }

    @Test
    fun testIfEmailValidates(){
        assertEquals(true, textValidator.isEmailAddress("abc@gmail.com"))
        assertEquals(false, textValidator.isEmailAddress("abc@gmailcom"))
        assertEquals(false, textValidator.isEmailAddress("abcgmail.com"))
        assertEquals(false, textValidator.isEmailAddress("abcgmailcom"))
    }
}