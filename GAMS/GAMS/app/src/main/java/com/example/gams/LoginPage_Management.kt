package com.example.gams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginPage_Management : AppCompatActivity() {

    lateinit var register : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page_management)

        register = findViewById(R.id.register)

        register.setOnClickListener{
            var intent3=Intent(this@LoginPage_Management,RegisterType::class.java)
            startActivity(intent3)
        }

    }
}