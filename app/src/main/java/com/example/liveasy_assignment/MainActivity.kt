package com.example.liveasy_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.liveasy_assignment.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity() : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.signOut.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,phone_number::class.java))
            finish()
        }

        binding.btn.setOnClickListener{
            var id = binding.recyclerView.checkedRadioButtonId
            if(id!=-1){
                val radio: RadioButton = findViewById(id)
                Toast.makeText(applicationContext,"On button click :" +
                        " ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"On button click :" +
                        " nothing selected",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}