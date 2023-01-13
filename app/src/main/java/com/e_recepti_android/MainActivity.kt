package com.e_recepti_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.profile)
        val searchButton = findViewById<Button>(R.id.btnsearch)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

        searchButton.setOnClickListener {
            val intent = Intent(this, RecipesList::class.java)
            startActivity(intent)
        }




    }
}