package com.example.gams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class publicBuildingDetails : AppCompatActivity() {
    lateinit var textVie : TextView
    lateinit var gps : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_building_details)
//
        gps = findViewById(R.id.gps)
        gps.setOnClickListener {
            var gpsLocation = Intent(this@publicBuildingDetails,GpsLocation::class.java)
            startActivity(gpsLocation)
        }

        var UDISE5 : String = intent.getStringExtra("Aman").toString()
//        if (!UDISE5.isEmpty()){
//            Log.d("Testing", UDISE5)
//        }

        readFireStoreData(UDISE5)
    }

    fun readFireStoreData(udise7: String) {
        textVie = findViewById(R.id.textView5)

        val db = FirebaseFirestore.getInstance()


        db.collection("School").document(udise7).get()
            .addOnSuccessListener{
                val result: StringBuffer = StringBuffer()


                result.append(it.data?.getValue("Area")).append(" ")
                    .append(it.data?.getValue("ClassTo")).append(" ")
                textVie.setText(result)
            }
   }

}