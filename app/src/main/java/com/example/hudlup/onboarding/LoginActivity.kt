package com.example.hudlup.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hudlup.R
import com.example.hudlup.databinding.ActivityForgottenPasswordBinding
import com.example.hudlup.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setup ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupButtons()
    }

    private fun setupButtons(){
        binding.forgotMyPasswordBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity,ForgottenPassword::class.java))
        }

        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }
}