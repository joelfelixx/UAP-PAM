package com.example.uap

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahTanamanActivity : AppCompatActivity() {

    // Komponen input dan tombol simpan
    private lateinit var etNama: EditText
    private lateinit var etHarga: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_item)

        // Menghubungkan UI dengan ID di layout
        etNama = findViewById(R.id.etNama)
        etHarga = findViewById(R.id.etHarga)
        etDeskripsi = findViewById(R.id.etDeskripsi)
        btnSimpan = findViewById(R.id.btnSimpan)

        // Saat tombol simpan ditekan
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val harga = etHarga.text.toString()
            val deskripsi = etDeskripsi.text.toString()

            // Validasi input agar semua field terisi
            if (nama.isEmpty() || harga.isEmpty() || deskripsi.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Buat objek Tanaman dari input user
            val tanaman = Tanaman(id = null, nama, harga, deskripsi)

            // Kirim data ke server dengan Retrofit
            RetrofitClient.instance.addTanaman(tanaman).enqueue(object : Callback<Tanaman> {
                override fun onResponse(call: Call<Tanaman>, response: Response<Tanaman>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@TambahTanamanActivity, "Tanaman berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        finish() // Kembali ke activity sebelumnya
                    } else {
                        Toast.makeText(this@TambahTanamanActivity, "Gagal menambahkan tanaman", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Tanaman>, t: Throwable) {
                    Toast.makeText(this@TambahTanamanActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
