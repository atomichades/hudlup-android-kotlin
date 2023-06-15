package com.example.hudlup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.example.hudlup.onboarding.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_content) as NavHostFragment
        navController = navHostFragment.navController
//        if (savedInstanceState == null) {
//            val fragment = LoginFragment()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.main_content, fragment, LoginFragment.TAG)
//                .commit()
//        }

        //setup supportToolBar
        val supportToolac = supportActionBar
        supportToolac?.title = "Sign Up"
        supportToolac?.setDisplayHomeAsUpEnabled(true)

    }
}