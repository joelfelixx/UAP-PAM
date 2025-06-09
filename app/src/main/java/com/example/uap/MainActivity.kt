package com.example.uap

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    // Komponen UI dan adapter
    private lateinit var rvTanaman: RecyclerView
    private lateinit var btnTambah: Button
    private lateinit var adapter: TanamanAdapter
    private val tanamanList = mutableListOf<Tanaman>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi elemen tampilan
        rvTanaman = findViewById(R.id.rv_tanaman)
        btnTambah = findViewById(R.id.btn_tambah)

        // Set up adapter dengan aksi hapus dan detail
        adapter = TanamanAdapter(
            tanamanList,
            onDelete = { position ->
                val nama = tanamanList[position].plant_name
                deleteTanaman(nama, position)
            },
            onDetail = { tanaman ->
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("nama", tanaman.plant_name)
                    putExtra("harga", tanaman.price)
                    putExtra("deskripsi", tanaman.description)
                }
                startActivity(intent)
            }
        )

        // Atur RecyclerView
        rvTanaman.layoutManager = LinearLayoutManager(this)
        rvTanaman.adapter = adapter

        // Navigasi ke halaman tambah tanaman
        btnTambah.setOnClickListener {
            val intent = Intent(this, TambahTanamanActivity::class.java)
            startActivity(intent)
        }
    }

    // Panggil kembali data setiap kali activity di-resume
    override fun onResume() {
        super.onResume()
        getAllTanaman()
    }

    // Mengambil semua data tanaman dari server
    private fun getAllTanaman() {
        RetrofitClient.instance.getAllTanaman().enqueue(object : Callback<TanamanResponse> {
            override fun onResponse(
                call: Call<TanamanResponse>,
                response: Response<TanamanResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val tanamanData = response.body()!!.data
                    tanamanList.clear()
                    tanamanList.addAll(tanamanData)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TanamanResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Menghapus tanaman berdasarkan nama
    private fun deleteTanaman(nama: String, position: Int) {
        val encodedName = URLEncoder.encode(nama, "UTF-8") // Encode nama untuk menghindari karakter khusus

        RetrofitClient.instance.deleteTanaman(encodedName).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Pastikan posisi masih valid sebelum menghapus
                    if (position in tanamanList.indices) {
                        tanamanList.removeAt(position)
                        adapter.notifyItemRemoved(position)
                        adapter.notifyItemRangeChanged(position, tanamanList.size)
                        Toast.makeText(this@MainActivity, "Tanaman dihapus", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal hapus tanaman", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
