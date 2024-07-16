package com.example.gams

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class buildingDetails : AppCompatActivity() {
    private lateinit var UDISE : EditText
    private lateinit var yearOfestablishment : EditText
    private lateinit var area : EditText
    private lateinit var labs : EditText

    private lateinit var faculties : EditText
    private lateinit var numberOfClassRooms: EditText
    private lateinit var noOftoiletsgirls : EditText
    private lateinit var noOftoiletsboys : EditText
    private lateinit var noOfresidentstudent : EditText
    private lateinit var noOfresidentstaff : EditText
    private lateinit var playground : EditText
    private lateinit var save : Button
    private lateinit var rec : Button
    lateinit var noOfFacult : EditText
    lateinit var textv2 : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building_details)
        UDISE=findViewById(R.id.udise)
        yearOfestablishment = findViewById(R.id.year)
        area=findViewById(R.id.area_acres)
        labs = findViewById(R.id.labs)
        faculties = findViewById(R.id.noOfFaculties)
        numberOfClassRooms=findViewById(R.id.class_rooms)
        noOftoiletsgirls= findViewById(R.id.toilet_girls)
        noOftoiletsboys= findViewById(R.id.toilet_boys)
        noOfresidentstudent= findViewById(R.id.resident_stud)
        noOfresidentstaff= findViewById(R.id.resident_staff)
        playground= findViewById(R.id.playground_area)
        save = findViewById(R.id.upload)
        rec = findViewById(R.id.reci)

        save.setOnClickListener {
            val UDISE = UDISE.text.toString()
            if(UDISE.toString().length==13)
            {
                var building = Intent(this@buildingDetails,Images_upload::class.java)
                startActivity(building)
                Toast.makeText(applicationContext,"VAlid " , Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(applicationContext,"InVAlid " , Toast.LENGTH_SHORT).show()
            }

            val yearOfestablishment = yearOfestablishment.text.toString()
            val area = area.text.toString()
            val labs = labs.text.toString()
            val faculties = faculties.text.toString()
            val  numberofClassrooms=  numberOfClassRooms.text.toString()

            val noOftoiletgirls= noOftoiletsgirls.text.toString()
            val noOftoiletboys= noOftoiletsboys.text.toString()
            val noOfresidentstudent= noOfresidentstudent.text.toString()
            val noOfresidentstaff= noOfresidentstaff.text.toString()
            val playground= playground.text.toString()
            // Add a new document with a generated id.
            val data = hashMapOf(
                "area" to area
            )
//            val db = FirebaseFirestore.getInstance()
//            db.collection("cities")
//                .add(data)
//                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
//                }
            saveFireStore(UDISE , yearOfestablishment,area,labs,faculties,numberofClassrooms,noOftoiletgirls,noOftoiletboys,noOfresidentstudent,noOfresidentstaff,playground)


        }

        rec.setOnClickListener {

            val UDISE1 = UDISE.text.toString()

            readFireStoreData(UDISE1)

        }

    }



    fun saveFireStore(firstname2: String, lastname7: String ,lastname2: String, lastname3: String , lastname4: String , lastname5: String ,lastname6: String ,lastname9: String,lastname: String ,lastname10: String,lastname11: String) {
        val db = FirebaseFirestore.getInstance()
        val users: MutableMap<String , Any> = HashMap()
        users["UDISE"] = firstname2
        users["Established"] = lastname7
        users["AreaTo"] = lastname2
        users["Labs"] = lastname3
        users["Faculties"] = lastname4
        users["ClassTo"] = lastname5
        users["GToilets"] = lastname6
        users["BToilets"] = lastname9
        users["AreaStu"] = lastname
        users["AreaSta"] = lastname10
        users["AreaPg"] = lastname11

        noOfFacult = findViewById(R.id.udise)
        var g= noOfFacult.text.toString()
        db.collection("School").document(g).set(users)
            .addOnSuccessListener {
                Toast.makeText(this@buildingDetails ," Added successfully" , Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this@buildingDetails ," Failed to add" , Toast.LENGTH_SHORT).show()
            }
    }

    fun readFireStoreData(name: String){
        textv2 = findViewById(R.id.textv)

        val db = FirebaseFirestore.getInstance()
        Log.d("Testing", name);

        db.collection("School").document(name).get()
            .addOnSuccessListener{
                val result: StringBuffer = StringBuffer()
//                Log.d("Testing", it.isEmpty.toString());
//                if(!it.isEmpty){
//                    for (document in it){
//                        Log.d("Testing", document.data.toString());
//                    }
//                }



                        result.append(it.data?.getValue("Area")).append(" ")
                            .append(it.data?.getValue("ClassTo")).append(" ")
                        textv2.setText(result)
                    }
            }
    }
