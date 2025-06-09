package com.example.uap.auth

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.uap.MainActivity
import com.example.uap.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    // Deklarasi objek FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Mengambil referensi dari komponen tampilan
        val emailInput = findViewById<EditText>(R.id.edtEmail)
        val passwordInput = findViewById<EditText>(R.id.edtPassword)
        val loginBtn = findViewById<Button>(R.id.btnLogin)

        // Menangani aksi saat tombol login ditekan
        loginBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Proses masuk dengan email dan password melalui Firebase
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Jika berhasil, pindah ke MainActivity dan akhiri aktivitas ini
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        // Jika gagal, tampilkan pesan kesalahan
                        Toast.makeText(this, "Login gagal", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}
