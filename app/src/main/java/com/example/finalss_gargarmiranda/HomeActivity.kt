package com.example.finalss_gargarmiranda

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the username passed from MainActivity
        val username = intent.getStringExtra("USER_NAME")
        val tvUsernameDisplay = findViewById<TextView>(R.id.tv_username_display)
        
        // Display the username if it's not null or empty
        if (!username.isNullOrEmpty()) {
            tvUsernameDisplay.text = username
        }

        val btnReserve: ImageButton = findViewById(R.id.btn_reserve)
        btnReserve.setOnClickListener {
            val intent = Intent(this, ReserveActivity::class.java)
            startActivity(intent)
        }

        val btnList: ImageButton = findViewById(R.id.btn_reservation_list)
        btnList.setOnClickListener {
            val intent = Intent(this, ReservationListActivity::class.java)
            startActivity(intent)
        }

        val btnReport: ImageButton = findViewById(R.id.btn_report_facility)
        btnReport.setOnClickListener {
            val intent = Intent(this, ReportFacilityActivity::class.java)
            startActivity(intent)
        }
    }
}