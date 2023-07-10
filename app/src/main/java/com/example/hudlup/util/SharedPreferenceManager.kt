package com.example.hudlup.util
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import javax.annotation.Nullable


object SharedPreferenceManager  {
    private lateinit var mSharedPref: SharedPreferences
    private lateinit var editor:Editor
    fun init(context: Context) {
        if (!::mSharedPref.isInitialized) {
            mSharedPref = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
            editor = mSharedPref.edit()
        }
    }

    fun StoreUserDetailsOnSignUp(firstName:String, lastName:String, age:Int, photoURL:String?){
        editor.putString("firstname", TextValidator.MakeStringAllLowerCaseAndFirstLetterUppercase(firstName))
        editor.putString("lastname", TextValidator.MakeStringAllLowerCaseAndFirstLetterUppercase(lastName))
        editor.putInt("age", age)
        editor.putString("Photo_URL", photoURL)
        editor.apply()
    }

    fun getFirstName(): String {
        return mSharedPref.getString("firstname", "") ?: ""
    }

    fun getLastName(): String {
        return mSharedPref.getString("lastname", "") ?: ""
    }

    fun getAge(): Int {
        return mSharedPref.getInt("age", 0)
    }
    @Synchronized
    fun clearPreferences() {
        editor.clear().apply()
    }

    @Synchronized
    fun removePreference(key: String) {
        editor.remove(key).apply()
    }

}