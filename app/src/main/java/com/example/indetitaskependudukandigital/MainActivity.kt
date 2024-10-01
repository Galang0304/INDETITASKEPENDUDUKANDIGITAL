package com.example.indetitaskependudukandigital

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val pin = findViewById<EditText>(R.id.number)
        val login = findViewById<Button>(R.id.button_login)

        login.setOnClickListener {
            val str_number = pin.text.toString()

            if (str_number == "1234") {
                val intent = Intent(this, home::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }


        }






    }

}