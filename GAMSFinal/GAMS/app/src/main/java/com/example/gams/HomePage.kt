package com.example.gams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {

    lateinit var management : Button
    lateinit var public : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        management = findViewById(R.id.management)
        public = findViewById(R.id.public_button)

        management.setOnClickListener{
            var intent2 = Intent(this@HomePage,LoginPage_Management::class.java)
            startActivity(intent2)
        }

        public.setOnClickListener {
            var ipub = Intent(this@HomePage,public_entrance::class.java)
            startActivity(ipub)

        }

    }
}