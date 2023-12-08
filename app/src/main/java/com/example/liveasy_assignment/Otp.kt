package com.example.liveasy_assignment

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.liveasy_assignment.databinding.ActivityOtpBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.mukeshsolanki.OnOtpCompletionListener
import java.util.concurrent.TimeUnit

class Otp : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val storedVerificationId = intent.getStringExtra("number").toString()

//        binding.otp1.setText("")
//        binding.otp1.setOtpCompletionListener(object : OnOtpCompletionListener
//        {
//            override fun onOtpCompleted(otp: String?) {
////                val inn1 = getSystemService(Activity.INPUT_METHOD_SERVICE)as InputMethodManager
//////
////            currentFocus?.let{
////                inn1.hideSoftInputFromWindow(it.windowToken,0)
////            }
//            }
//
//        })
//        {
//            val inn1 = getSystemService(Activity.INPUT_METHOD_SERVICE)as InputMethodManager
//
//            currentFocus?.let{
//                inn1.hideSoftInputFromWindow(it.windowToken,0)
//            }
//        }
        binding.verify.setOnClickListener {
//            var otp=otpGiven.text.toString().trim()
            var otp = binding.otp1.text.toString().trim()
            if(!otp.isEmpty()){
                val credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp)
                signInWithPhoneAuthCredential(credential)
            }else{
                Toast.makeText(this,"Enter OTP",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
// ...
                } else {
// Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
// The verification code entered was invalid
                        Toast.makeText(this,"Invalid OTP",Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}