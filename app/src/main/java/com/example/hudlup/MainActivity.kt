package com.example.hudlup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.example.hudlup.onboarding.LoginFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_content) as NavHostFragment
        navController = navHostFragment.navController
        FirebaseApp.initializeApp(this)
        //check if user is signed in:
        val user = Firebase.auth.currentUser
        if (user != null) {
            // User is signed in
            Log.d("Auth", "User is signed in")
        } else {
            // No user is signed in
            Log.d("Auth", "User is not signed in")
        }
        //setup supportToolBar
        val supportToolac = supportActionBar
        supportToolac?.title = "Sign Up"
        supportToolac?.setDisplayHomeAsUpEnabled(true)

    }
}