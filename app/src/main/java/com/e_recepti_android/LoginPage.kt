package com.e_recepti_android

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameText = findViewById<EditText>(R.id.username)
        val passwordText = findViewById<EditText>(R.id.password)
        val registracijaGumb = findViewById<TextView>(R.id.registracija)

        registracijaGumb.setOnClickListener {
            val intent = Intent(this, RegistrationPage::class.java)
            startActivity(intent)

        }

    }
}