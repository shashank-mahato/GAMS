package com.example.gams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class ManagementOtpVerify : AppCompatActivity() {
    var TAG: String = "Testing"
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    lateinit var loginButton: Button
    lateinit var phoneNumber: EditText
    lateinit var phone:String
    lateinit var enterOTP: EditText
    lateinit var OTP:String
    lateinit var verifyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management_otp_verify)
        auth = FirebaseAuth.getInstance()
        loginButton=findViewById(R.id.login)
        phoneNumber=findViewById(R.id.phoneNumber)
        enterOTP=findViewById(R.id.code)
        verifyButton=findViewById(R.id.verify)
        Toast.makeText(applicationContext,"Below Login Button", Toast.LENGTH_SHORT).show()
        loginButton.setOnClickListener ( object: View.OnClickListener {
            override fun onClick(p0: View?) {
                Toast.makeText(applicationContext,"Clicked Login Button", Toast.LENGTH_SHORT).show()
                phone=phoneNumber.text.toString()
                startWithPhoneVerification(phone)
                if(enterOTP.visibility== View.INVISIBLE) {
                    enterOTP.visibility = View.VISIBLE
                    verifyButton.visibility = View.VISIBLE
                }
            }
        })
        verifyButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                OTP=enterOTP.text.toString()
                verifyPhoneNumberWithCode(storedVerificationId, code=OTP)
            }
        })
        callbacks=object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d(TAG,"Verification Completed : $p0")
                signInWithPhoneAuthCredential(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.w(TAG,"Verification failed",p0)

                Toast.makeText(applicationContext,"Verification failed. Try Again", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                Log.d(TAG, "onCodeSent:$p0")
                storedVerificationId=p0
                resendToken=p1

            }
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
//        updateUI(currentUser)
    }

    private fun startWithPhoneVerification(phone: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }
    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
        // [END verify_with_code]
    }



    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(applicationContext,"Number Verified", Toast.LENGTH_LONG).show()
                    var intent = Intent(this@ManagementOtpVerify,buildingDetails::class.java)
                    startActivity(intent)

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}