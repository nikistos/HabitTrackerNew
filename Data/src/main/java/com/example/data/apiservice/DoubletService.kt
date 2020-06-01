package com.example.data.apiservice

import com.example.data.models.*
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