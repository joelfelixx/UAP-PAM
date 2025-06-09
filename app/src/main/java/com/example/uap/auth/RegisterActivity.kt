package com.example.uap.auth

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.uap.R
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Referensi ke elemen input email, password, dan tombol register
        val emailInput = findViewById<EditText>(R.id.edtEmail)
        val passwordInput = findViewById<EditText>(R.id.edtPassword)
        val registerBtn = findViewById<Button>(R.id.btnRegister)

        // Ketika tombol register ditekan
        registerBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Registrasi akun dengan email dan password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Berhasil mendaftar!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Registrasi gagal", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
