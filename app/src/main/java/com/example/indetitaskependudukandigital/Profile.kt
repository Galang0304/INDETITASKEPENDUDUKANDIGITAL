package com.example.indetitaskependudukandigital

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val home_button_profile = findViewById<ImageView>(R.id.button_home)

        home_button_profile.setOnClickListener{
            val intent = Intent (this,home::class.java)
            startActivity(intent)

        }
    }
}