package com.example.hudlup.util

object TextValidator {

    fun hasSpecialCharacters (aString: String): Boolean {
        return aString.contains("[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }

    public fun isEmailAddress (aString: String) :Boolean {
        return aString.contains("[A-Za-z]+@[A-Za-z]+\\.[A-Za-z]+".toRegex())
    }

    fun signUpEditTextFiftyOrLess (aString:String): Boolean{
        return aString.length < 25
    }
}