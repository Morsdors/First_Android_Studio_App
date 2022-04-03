package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.example.myapplication.ui.login.LoginActivity

class enterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
        val log_in = findViewById<Button>(R.id.login_butt)
        val register = findViewById<Button>(R.id.register_butt)

        log_in.setOnClickListener {
            val intent = Intent(this@enterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}