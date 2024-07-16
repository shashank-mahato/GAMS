package com.example.gams

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GpsLocation : AppCompatActivity() {
    private lateinit var buttongps: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps_location)
        buttongps = findViewById(R.id.gpsButton)
        buttongps.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=14.796071,75.399066")
            )
            startActivity(intent)
        }

    }
}