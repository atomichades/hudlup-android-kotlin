package com.example.hudlup.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.hudlup.R
import com.example.hudlup.databinding.ActivityForgottenPasswordBinding

class ForgottenPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgottenPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setup ViewBinding
        binding = ActivityForgottenPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setup supportToolBar
        val supportToolac = supportActionBar
        supportToolac?.title = "Password Reset"
        supportToolac?.setDisplayHomeAsUpEnabled(true)
    }


}