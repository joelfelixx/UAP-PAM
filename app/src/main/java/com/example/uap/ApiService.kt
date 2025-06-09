package com.example.uap.network

import com.example.uap.Tanaman
import com.example.uap.TanamanResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("plant/all")
    fun getAllTanaman(): Call<TanamanResponse>

    @POST("plant/new")
    fun addTanaman(@Body tanaman: Tanaman): Call<Tanaman>

    @PUT("plant/{plant_name}")
    fun updateTanaman(@Path("plant_name") nama: String, @Body tanaman: Tanaman): Call<Tanaman>

    @DELETE("plant/{plant_name}")
    fun deleteTanaman(@Path("plant_name") nama: String): Call<Void>

    @GET("plant/{plant_name}")
    fun getTanamanByNama(@Path("plant_name") nama: String): Call<Tanaman>

}