package com.example.hudlup.util

import android.app.Application
import android.content.Context

import android.content.SharedPreferences
import kotlin.properties.Delegates


class SharedPreferenceManager {

    var sharedpreferences: SharedPreferences by Delegates.notNull()

    constructor(application: Application){
        this.sharedpreferences = application.getSharedPreferences("preference_key", Context.MODE_PRIVATE);
    }

    fun StoreUserDetailsOnSignUp(firstName:String, lastName:String, age:Int){
        var edit = sharedpreferences.edit()
        edit.putString("firstname", firstName)
        edit.putString("lastname", lastName)
        edit.putInt("age", age)
        edit.apply()
    }
}