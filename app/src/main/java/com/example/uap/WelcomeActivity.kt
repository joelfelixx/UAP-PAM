package com.example.uap

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uap.auth.LoginActivity
import com.example.uap.auth.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    // Deklarasi tombol login dan teks untuk registrasi
    private lateinit var btnLogin: Button
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome) // Layout tampilan awal aplikasi

        // Menghubungkan variabel dengan komponen di layout
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        // Aksi saat tombol login ditekan: pindah ke LoginActivity
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Aksi saat tulisan register ditekan: pindah ke RegisterActivity
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
