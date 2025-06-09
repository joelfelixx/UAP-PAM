package com.example.uap

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    // Deklarasi komponen tampilan
    private lateinit var tvNamaTanaman: TextView
    private lateinit var tvHarga: TextView
    private lateinit var tvDeskripsi: TextView
    private lateinit var btnUpdate: Button
    private lateinit var imgTanaman: ImageView // ImageView dideklarasikan tapi tidak digunakan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item)

        // Menghubungkan komponen tampilan dengan ID-nya
        tvNamaTanaman = findViewById(R.id.tvNamaTanaman)
        tvHarga = findViewById(R.id.tvHarga)
        tvDeskripsi = findViewById(R.id.tvDeskripsi)
        btnUpdate = findViewById(R.id.btnUpdate)

        // Mengambil data dari intent
        val nama = intent.getStringExtra("nama") ?: "-"
        val harga = intent.getStringExtra("harga")
        val deskripsi = intent.getStringExtra("deskripsi") ?: "-"

        // Menampilkan data pada tampilan
        tvNamaTanaman.text = nama
        tvHarga.text = "Rp $harga"
        tvDeskripsi.text = deskripsi

        // Aksi saat tombol Update ditekan
        btnUpdate.setOnClickListener {
            // Mengirim data ke UpdateActivity melalui intent
            val intent = Intent(this, UpdateActivity::class.java).apply {
                putExtra("nama", nama)
                putExtra("harga", harga)
                putExtra("deskripsi", deskripsi)
            }
            startActivity(intent)
        }
    }
}
