package com.example.hudlup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hudlup.onboarding.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //do some validation on authentication here
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}