package com.e_recepti_android

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegistrationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val imeUporabnika = findViewById<EditText>(R.id.etIme)
        val priimekUporabnika = findViewById<EditText>(R.id.etPriimek)
        val mailUporabnika = findViewById<EditText>(R.id.etMail)
        val gesloUporabnika = findViewById<EditText>(R.id.etGeslo)
        val ponoviGeslo = findViewById<EditText>(R.id.etPonoviGeslo)
        val registrationButton = findViewById<Button>(R.id.btnRegister)

    }
}