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


                result.append("Established year: ").append(it.data?.getValue("Established")).append(" \n")
                result.append("Total Area: ").append(it.data?.getValue("AreaTo")).append(" \n")
                result.append("Total Laboratries: ").append(it.data?.getValue("Labs")).append(" \n")
                result.append("Total Faculties: ").append(it.data?.getValue("Faculties")).append(" \n")
                result.append("Total Classes: ").append(it.data?.getValue("ClassTo")).append(" \n")
                result.append("Total Girls Toilets: ").append(it.data?.getValue("GToilets")).append(" \n")
                result.append("Total Boys Toilets: ").append(it.data?.getValue("BToilets")).append(" \n")
                result.append("Total Area for Students: ").append(it.data?.getValue("AreaStu")).append(" \n")
                result.append("Total Area for Staff: ").append(it.data?.getValue("AreaSta")).append(" \n")
                result.append("Total Area for Playing Ground: ").append(it.data?.getValue("AreaPg")).append(" \n")
                textVie.setText(result)
            }
   }

}