package com.example.gams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class public_entrance : AppCompatActivity() {
    private lateinit var searchp : Button
    lateinit var locate : Button
    lateinit var tc : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_entrance)
        tc = findViewById(R.id.editTextTextPersonName)
        searchp = findViewById(R.id.searchBuilding)
        locate = findViewById(R.id.location)
        //var searcht = searchp.text.toString()

            searchp.setOnClickListener {
                var searcht = tc.text.toString()
                var intent =Intent(this@public_entrance,publicBuildingDetails::class.java)
                intent.putExtra("Aman",searcht)
                startActivity(intent)


        }

        locate.setOnClickListener {
            var locates = Intent(this@public_entrance,GpsLocation::class.java)
            startActivity(locates)

        }


    }



}