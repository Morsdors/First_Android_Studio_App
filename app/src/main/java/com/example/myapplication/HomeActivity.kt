package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import android.os.Looper

//import java.util.logging.Handler

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed(Runnable{
            val intent = Intent(this@HomeActivity, enterActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}