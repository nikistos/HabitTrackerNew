package com.example.habittrackernew.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface DoubletService {

    @GET("api/habit")
    suspend fun getHabits(@Header("Authorization") authorization: String): List<NetworkHabit>

    @PUT("api/habit")
    suspend fun updateHabit(
        @Header("Authorization") authorization: String,
        @Body habit: NetworkHabit
    ): HabitUid

    @PUT("api/habit")
    suspend fun insertHabit(
        @Header("Authorization") authorization: String,
        @Body habit: NetworkHabit
    ): HabitUid
}

private const val BASE_URL = "https://droid-test-server.doubletapp.ru/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

object DoubletNetwork {
    val service: DoubletService by lazy {
        retrofit.create(DoubletService::class.java)
    }
}