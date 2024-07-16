package com.example.gams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

class RegisterType : AppCompatActivity() {

    lateinit var next : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_type)

        // get reference to the string array that we just created
        val languages = resources.getStringArray(R.array.type)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter)

        val language = resources.getStringArray(R.array.state)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapte = ArrayAdapter(this, R.layout.dropdown_item, language)
        // get reference to the autocomplete text view
        val autocompleteT = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView2)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteT.setAdapter(arrayAdapte)

        val languag = resources.getStringArray(R.array.district)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapt = ArrayAdapter(this, R.layout.dropdown_item, languag)
        // get reference to the autocomplete text view
        val autocomplete = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView3)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocomplete.setAdapter(arrayAdapt)

        val langua = resources.getStringArray(R.array.organization)
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdap = ArrayAdapter(this, R.layout.dropdown_item, langua)
        // get reference to the autocomplete text view
        val autocomplet = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView4)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocomplet.setAdapter(arrayAdap)

        next = findViewById(R.id.next_registerType)

        next.setOnClickListener {
            var intent4 = Intent(this@RegisterType,ManagementOtpVerify::class.java)
            startActivity(intent4)
        }

    }
}