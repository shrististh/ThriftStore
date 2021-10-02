package com.example.mainthriftstoreandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class splashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activities)
        getSupportActionBar()?.hide();

        // Main , IO
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            startActivity(
                Intent(this@splashActivity, loginActivity::class.java)
            )
            finish()
        }
    }
}