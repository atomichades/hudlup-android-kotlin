package com.example.hudlup.util

import java.util.Locale

object TextValidator {

    fun hasSpecialCharacters (aString: String): Boolean {
        return aString.contains("[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }

     fun isEmailAddress (aString: String) :Boolean {
         val emailRegex = Regex("^([A-Za-z0-9]+[_-])*[A-Za-z0-9]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
         return emailRegex.matches(aString)
    }

    fun signUpEditTextFiftyOrLess (aString:String): Boolean{
        return aString.length < 25
    }

    fun MakeStringAllLowerCaseAndFirstLetterUppercase(string:String) :String{
        val lowerCaseString = string.toLowerCase(Locale.ROOT).trim()
        return lowerCaseString.replaceFirstChar { it.uppercase() }
    }
}