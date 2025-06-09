package com.example.uap

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etHarga: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var btnUpdate: Button

    private lateinit var originalPlantName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_item)

        etNama = findViewById(R.id.etNama)
        etHarga = findViewById(R.id.etHarga)
        etDeskripsi = findViewById(R.id.etDeskripsi)
        btnUpdate = findViewById(R.id.btnSimpan)

        originalPlantName = intent.getStringExtra("nama") ?: ""
        val harga = intent.getStringExtra("harga") ?: ""
        val deskripsi = intent.getStringExtra("deskripsi") ?: ""

        etNama.setText(originalPlantName)
        etHarga.setText(harga)
        etDeskripsi.setText(deskripsi)

        btnUpdate.setOnClickListener {
            val namaBaru = etNama.text.toString()
            val hargaBaru = etHarga.text.toString()
            val deskripsiBaru = etDeskripsi.text.toString()

            val updatedTanaman = Tanaman(
                plant_name = namaBaru,
                price = hargaBaru,
                description = deskripsiBaru
            )

            updateTanaman(originalPlantName, updatedTanaman)
        }
    }

    private fun updateTanaman(originalName: String, tanaman: Tanaman) {
        RetrofitClient.instance.updateTanaman(originalName, tanaman)
            .enqueue(object : Callback<Tanaman> {
                override fun onResponse(call: Call<Tanaman>, response: Response<Tanaman>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@UpdateActivity, "Berhasil update tanaman", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@UpdateActivity, "Gagal update tanaman", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Tanaman>, t: Throwable) {
                    Toast.makeText(this@UpdateActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
