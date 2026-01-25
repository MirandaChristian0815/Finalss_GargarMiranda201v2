package com.example.finalss_gargarmiranda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Username = findViewById<EditText>(R.id.ET_Username)
        val login = findViewById<Button>(R.id.BTN_Login)
        val Signup = findViewById<Button>(R.id.BTN_Signup)

        login.setOnClickListener {
            val username = Username.text.toString()
            val i = Intent(this, HomeActivity::class.java)
            i.putExtra("USER_NAME", username)
            startActivity(i)
        }

        Signup.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i)
        }
    }
}