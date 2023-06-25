package com.example.hudlup.onboarding

import android.content.SharedPreferences
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.hudlup.util.SharedPreferenceManager
import java.lang.NumberFormatException

class SignUpViewModel : ViewModel() {
//check firstname or lastname field

    //check password matches
    fun checkPasswordMatches (password1:String, password2:String) : Boolean {
        return password1.equals(password2)
    }
    //check age
    fun checkAgeIsOver18 (age:String):Boolean{
        return try {
            age.toInt() >= 18
        }catch (anException:NumberFormatException){
            false
        }
    }

}