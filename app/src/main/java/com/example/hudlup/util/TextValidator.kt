package com.example.hudlup.util

class TextValidator {

    fun hasSpecialCharacters (aString: String): Boolean {
        return aString.contains("[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
    }

    fun isEmailAddress (aString: String) :Boolean {
        return aString.contains("[A-Za-z]+@[A-Za-z]+\\.[A-Za-z]+".toRegex())
    }
}