package com.example.liveasy_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.liveasy_assignment.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btn.setOnClickListener{
            auth.signOut()

        }
    }
}