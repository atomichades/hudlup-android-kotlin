package com.example.hudlup.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hudlup.R
import com.example.hudlup.databinding.ActivityForgottenPasswordBinding
import com.example.hudlup.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setup ViewBinding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setup supportToolBar
        val supportToolac = supportActionBar
        supportToolac?.title = "Sign Up"
        supportToolac?.setDisplayHomeAsUpEnabled(true)
    }
}