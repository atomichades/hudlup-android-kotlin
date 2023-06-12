package com.example.hudlup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hudlup.onboarding.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// Logic to load the starting destination
        //  when the Activity is first created
        if (savedInstanceState == null) {
            val fragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_content, fragment, LoginFragment.TAG)
                .commit()
        }
    }
}