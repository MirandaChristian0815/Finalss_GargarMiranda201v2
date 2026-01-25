package com.example.finalss_gargarmiranda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val FirstName = findViewById<EditText>(R.id.ET_FirstName)
        val LastName = findViewById<EditText>(R.id.ET_LastName)
        val Email = findViewById<EditText>(R.id.ET_EmailSignup)
        val Password = findViewById<EditText>(R.id.ET_PassowordSignup)
        val Register = findViewById<Button>(R.id.BTN_Register)
        val BackLogin = findViewById<TextView>(R.id.TV_BackLogin)
        val Username = findViewById<EditText>(R.id.ET_UsernameSignup)

        Register.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

        BackLogin.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }
}