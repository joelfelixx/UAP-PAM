package com.example.uap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter untuk RecyclerView yang menampilkan daftar tanaman
class TanamanAdapter(
    private val list: List<Tanaman>,                     // Data tanaman
    private val onDelete: (Int) -> Unit,                 // Fungsi callback untuk hapus item
    private val onDetail: (Tanaman) -> Unit              // Fungsi callback untuk tampilkan detail item
) : RecyclerView.Adapter<TanamanAdapter.ViewHolder>() {

    // Kelas ViewHolder untuk menyimpan referensi view tiap item
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama: TextView = view.findViewById(R.id.nama_tanaman)
        val harga: TextView = view.findViewById(R.id.harga_tanaman)
        val deleteBtn: Button = view.findViewById(R.id.btn_delete)
        val detailBtn: Button = view.findViewById(R.id.btn_detail)
    }

    // Membuat tampilan item baru saat diperlukan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plant, parent, false)
        return ViewHolder(view)
    }

    // Mengisi tampilan item dengan data dari list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tanaman = list[position]
        holder.nama.text = tanaman.plant_name
        holder.harga.text = tanaman.price

        // Aksi tombol hapus dan detail
        holder.deleteBtn.setOnClickListener { onDelete(position) }
        holder.detailBtn.setOnClickListener { onDetail(tanaman) }
    }

    // Mengembalikan jumlah item dalam list
    override fun getItemCount(): Int = list.size
}
