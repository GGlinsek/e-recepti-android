package com.e_recepti_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.profile)
        val imeRecepta = findViewById<EditText>(R.id.etrecepti)
        val searchButton = findViewById<Button>(R.id.btnsearch)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

        searchButton.setOnClickListener {
            val recept = imeRecepta.text.toString()
            val intent = Intent(this, RecepiesList::class.java)
            startActivity(intent)
        }



        if (2==3){
            loginButton.visibility = View.INVISIBLE
        }


    }
}